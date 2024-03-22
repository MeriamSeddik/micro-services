package com.bnp.cardiff.claimrequest.controller;


import com.bnp.cardiff.claimrequest.dto.request.ClaimRequestStatusRequest;
import com.bnp.cardiff.claimrequest.dto.response.ClaimRequestResponse;
import com.bnp.cardiff.claimrequest.event.ClaimRequestEvent;
import com.bnp.cardiff.claimrequest.models.ClaimRequest;
import com.bnp.cardiff.claimrequest.services.impl.ClaimRequestServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ClaimRequestController {

    private final ClaimRequestServiceImpl claimRequestServiceImpl;
    private final KafkaTemplate<String, ClaimRequestEvent> kafkaTemplate;
    @Autowired
    private ModelMapper mapper;
    private static final String REQUEST_BODY="Request Body";
    private static final String NOTIFICATION_TOPIC="notificationTopic";

    @Tag(name="Post",description = "Enregistrement des Sinistre")
    @Operation(summary = "Save" ,description = "Save a new record")
    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",schema = @Schema(implementation = ClaimRequest.class)) })
    @ApiResponse(responseCode = "500", description = "Cant execute", content = @Content)
    @PostMapping("/save")
    public ClaimRequestResponse addClaim(@RequestBody ClaimRequest claimRequest) {
        var claimEvent = ClaimRequestEvent.builder()
                .message("Saving Claim request")
                .uri("/api/claimRequest/save")
                .timeStamp(now())
                .data(Map.of(REQUEST_BODY,claimRequest))
                .build();
        kafkaTemplate.send(NOTIFICATION_TOPIC, claimEvent);
        return mapper.map(claimRequestServiceImpl.saveOrUpdate(claimRequest),ClaimRequestResponse.class);
    }
    @Tag(name="Get",description = "Chercher par id")
    @GetMapping("/findBy/{id}")
    public ClaimRequestResponse getOneRequest(@PathVariable Long id) {
        Optional<ClaimRequest> claimRequest = claimRequestServiceImpl.findById(id);
        if (!claimRequest.isPresent())
            log.info("ClaimRequest with ID " + id + " not found.");

        var claimEvent = ClaimRequestEvent.builder()
                .message("Fetching Claim request by id "+ id)
                .uri("/findBy/{id}")
                .timeStamp(now())
                .data(Map.of(REQUEST_BODY,claimRequest))
                .build();
        kafkaTemplate.send(NOTIFICATION_TOPIC, claimEvent);
        return mapper.map(claimRequest.get(),ClaimRequestResponse.class);
    }
    @Tag(name="Get",description = "Lister tous les sinistre")
    @GetMapping("/findAll")
    public List<ClaimRequest> allClaims() {
        var claimEvent = ClaimRequestEvent.builder()
                .message("Fetching All claim request")
                .uri("/findAll")
                .timeStamp(now())
                .data(Map.of(REQUEST_BODY,(List<ClaimRequest>) claimRequestServiceImpl.findAll()))
                .build();
        kafkaTemplate.send(NOTIFICATION_TOPIC, claimEvent);
        return (List<ClaimRequest>) claimRequestServiceImpl.findAll();
    }
    @Tag(name="Delete",description = "Supprimer un contrat")
    @DeleteMapping("/remove-claimRequest/{claimRequest-id}")
    public void removeClaimRequest(@PathVariable("claimRequest-id") Long claimRequestId) {
        var claimEvent = ClaimRequestEvent.builder()
                .message("Deleting claim request")
                .uri("/remove-claimRequest/{claimRequest-id")
                .timeStamp(now())
                .data(Map.of(REQUEST_BODY,claimRequestId))
                .build();
        kafkaTemplate.send(NOTIFICATION_TOPIC, claimEvent);
        claimRequestServiceImpl.deleteById(claimRequestId);
    }
    @Tag(name="Put",description = "Modification des Sinistre")
    @PutMapping("/{id}/status")
    public ClaimRequestResponse modifyClaimRequestStatus(@PathVariable Long id, @RequestBody ClaimRequestStatusRequest updatedClaimRequestStatus) {
        var claimEvent = ClaimRequestEvent.builder()
                .message("update status")
                .uri("/{id}/status")
                .timeStamp(now())
                .data(Map.of(REQUEST_BODY,updatedClaimRequestStatus))
                .build();
        kafkaTemplate.send(NOTIFICATION_TOPIC, claimEvent);
        return mapper.map(claimRequestServiceImpl.updateClaimRequestStatus(id,updatedClaimRequestStatus),ClaimRequestResponse.class);
    }
    @Tag(name="Get",description = "Chercher un contrat")
    @GetMapping("policy/{idPolicies}")
    public ResponseEntity<ClaimRequest> findPolicy(@PathVariable("idPolicies") String idPolicies){
        return ResponseEntity.ok(claimRequestServiceImpl.findPolicies(idPolicies));
    }
    @Tag(name="Patch",description = "Lier snistre -> policy")

    @PatchMapping("/{requestId}/policy/{objectId}")
    public ClaimRequest linkPoliciesToClaimRequest(
            @PathVariable Long requestId,
            @PathVariable String objectId) {

        var claimEvent = ClaimRequestEvent.builder()
                .message("Linking claim request with policies")
                .uri("/{requestId}/policy/{objectId}")
                .timeStamp(now())
                .data(Map.of(REQUEST_BODY,requestId + "/policy/" +objectId))
                .build();
        kafkaTemplate.send(NOTIFICATION_TOPIC, claimEvent);
    return claimRequestServiceImpl.linkPoliciesToClaimRequest(requestId, objectId);
    }


}
