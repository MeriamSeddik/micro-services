package com.bnp.cardiff.claimRequest.controller;


import com.bnp.cardiff.claimRequest.config.models.ProtectionPolicies;
import com.bnp.cardiff.claimRequest.dto.request.ClaimRequestStatusRequest;
import com.bnp.cardiff.claimRequest.dto.response.ClaimRequestResponse;
import com.bnp.cardiff.claimRequest.event.ClaimRequestEvent;
import com.bnp.cardiff.claimRequest.models.ClaimRequest;
import com.bnp.cardiff.claimRequest.services.impl.ClaimRequestServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.time.LocalDateTime.now;

@RestController
@RequestMapping("/api/claimRequest")
@RequiredArgsConstructor
public class ClaimRequestController {

    private final ClaimRequestServiceImpl claimRequestServiceImpl;
    private final KafkaTemplate<String, ClaimRequestEvent> kafkaTemplate;
    @Autowired
    private ModelMapper mapper;

    @PostMapping("/save")
    public ClaimRequestResponse addClaim(@RequestBody ClaimRequest claimRequest) {
        var claimEvent = ClaimRequestEvent.builder()
                .message("Saving Claim request")
                .uri("/api/claimRequest/save")
                .timeStamp(now())
                .data(Map.of("Request Body",claimRequest))
                .build();
        kafkaTemplate.send("notificationTopic", claimEvent);
        return mapper.map(claimRequestServiceImpl.saveOrUpdate(claimRequest),ClaimRequestResponse.class);
    }
    @GetMapping("/findBy/{id}")
    public ClaimRequestResponse getOneRequest(@PathVariable Long id) {
        Optional<ClaimRequest> claimRequest = claimRequestServiceImpl.findById(id);
        if (!claimRequest.isPresent())
            System.out.println("ClaimRequest with ID " + id + " not found.");
        return mapper.map(claimRequest.get(),ClaimRequestResponse.class);
    }

    @GetMapping("/findAll")
    public List<ClaimRequest> allClaims() {
        return (List<ClaimRequest>) claimRequestServiceImpl.findAll();
    }
    @DeleteMapping("/remove-claimRequest/{claimRequest-id}")
    public void removeClaimRequest(@PathVariable("claimRequest-id") Long claimRequestId) {
        claimRequestServiceImpl.deleteById(claimRequestId);
    }
    @PutMapping("/{id}/status")
    public ClaimRequestResponse modifyClaimRequestStatus(@PathVariable Long id, @RequestBody ClaimRequestStatusRequest updatedClaimRequestStatus) {

        return mapper.map(claimRequestServiceImpl.updateClaimRequestStatus(id,updatedClaimRequestStatus),ClaimRequestResponse.class);
    }
    @GetMapping("policy/{idPolicies}")
    public ResponseEntity<ClaimRequest> findPolicy(@PathVariable("idPolicies") String idPolicies){
        return ResponseEntity.ok(claimRequestServiceImpl.findPolicies(idPolicies));
    }

    @PatchMapping("/{requestId}/policy/{objectId}")
    public ClaimRequest linkPoliciesToClaimRequest(
            @PathVariable Long requestId,
            @PathVariable String objectId) {

    return claimRequestServiceImpl.linkPoliciesToClaimRequest(requestId, objectId);
    }


}
