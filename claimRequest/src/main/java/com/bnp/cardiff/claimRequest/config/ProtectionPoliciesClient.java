package com.bnp.cardiff.claimRequest.config;


import com.bnp.cardiff.claimRequest.config.models.ProtectionPolicies;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "policies-service", url = "${application.config.policies-url}")
public interface ProtectionPoliciesClient {

    @GetMapping("/findByContractId/{idPolicies}")
    ProtectionPolicies fundPolicyById(@PathVariable("idPolicies") String idPolicies);

}
