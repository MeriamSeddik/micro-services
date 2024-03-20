package com.bnp.cardiff.policies.controller;


import com.bnp.cardiff.policies.dto.response.ProtectionPoliciesResponse;
import com.bnp.cardiff.policies.models.ProtectionPolicies;
import com.bnp.cardiff.policies.services.impl.PoliciesServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/protection-policies")
@RequiredArgsConstructor
public class ProtectionPoliciesController {

    private final PoliciesServiceImpl policiesService;
    @Autowired
    private ModelMapper mapper;

    @PostMapping("/save")
    public ProtectionPoliciesResponse addPolicy(@RequestBody ProtectionPolicies protectionPolicies) {
        return mapper.map(policiesService.saveOrUpdate(protectionPolicies), ProtectionPoliciesResponse.class);
    }
    @GetMapping("/findBy/{id}")
    public ProtectionPoliciesResponse getOnePolicy(@PathVariable Integer id) {
        Optional<ProtectionPolicies> protectionPolicies = policiesService.findById(id);
        if (!protectionPolicies.isPresent())
            System.out.println("Policy with ID " + id + " not found.");
        return mapper.map(protectionPolicies.get(), ProtectionPoliciesResponse.class);
    }

    @GetMapping("/findAll")
    public List<ProtectionPolicies> allPolicies() {
        return (List<ProtectionPolicies>) policiesService.findAll();
    }
    @DeleteMapping("/remove-policies/{policy-id}")
    public void removeClaimRequest(@PathVariable("policy-id") Integer policyid) {
        policiesService.deleteById(policyid);
    }

    @GetMapping("/find")
    public ResponseEntity<List<ProtectionPolicies>> getProtectionPoliciesWithVariousParams(
            @RequestParam String id,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(defaultValue = "false") boolean history) {
        List<ProtectionPolicies> protectionPolicies = policiesService.getProtectionPolicies(id, date, history);
        return ResponseEntity.ok(protectionPolicies);
    }

    @GetMapping("/findByContractId/{idPolicies}")
    public ProtectionPolicies getFindByContractId(@PathVariable("idPolicies") String contractId) {
        return policiesService.findProtectionPoliciesByContractId(contractId);
    }

}
