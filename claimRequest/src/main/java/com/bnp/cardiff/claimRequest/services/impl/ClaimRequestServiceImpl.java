package com.bnp.cardiff.claimRequest.services.impl;

import com.bnp.cardiff.claimRequest.config.ProtectionPoliciesClient;
import com.bnp.cardiff.claimRequest.config.models.ProtectionPolicies;
import com.bnp.cardiff.claimRequest.dto.request.ClaimRequestStatusRequest;
import com.bnp.cardiff.claimRequest.dto.response.ClaimRequestResponse;
import com.bnp.cardiff.claimRequest.models.*;
import com.bnp.cardiff.claimRequest.repository.*;
import com.bnp.cardiff.claimRequest.services.IService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ClaimRequestServiceImpl implements IService<ClaimRequest> {

    private final ClaimRequestRepository claimRequestRepository;
    private  final ProductRepository productRepository;
    private  final GuaranteeRepository guaranteeRepository;
    private final PolicyRepository policyRepository;
    private final ProtectionPoliciesClient protectionPoliciesConfig;
    private final StatusRepository statusRepository;

    @Override
    public Collection<ClaimRequest> findAll() {
        return claimRequestRepository.findAll();
    }

    @Override
    public Optional<ClaimRequest> findById(Long id) {
        return claimRequestRepository.findById(id);
    }

    @Override
    public ClaimRequest saveOrUpdate(ClaimRequest claimRequest) {
        return claimRequestRepository.save(claimRequest);
    }

    @Override
    public void deleteById(Long id) {
        claimRequestRepository.deleteById(id);
    }

    public ClaimRequest updateClaimRequestStatus(Long idClaim,ClaimRequestStatusRequest statusRequest){

        Optional<ClaimRequest> claim=claimRequestRepository.findById(idClaim);
        if(claim.isPresent()){
            var status = Status.builder()
                    .statusAt(statusRequest.getStatusAt())
                    .step(statusRequest.getStep())
                    .reason(statusRequest.getReason())
                    .status(statusRequest.getStatus())
                    .claimRequests(claim.get()).build();
            claim.get().getStatus().add(status);

            claimRequestRepository.save(claim.get());
        }
        return claim.get();
    }

    public ClaimRequest findPolicies(String idPolicies) {
        ProtectionPolicies policy = protectionPoliciesConfig.fundPolicyById(idPolicies);
        System.out.println(policy);
        return null;
    }

    public ClaimRequest linkPoliciesToClaimRequest(Long requestId,String idPolicies){
        Optional<ClaimRequest> claim=claimRequestRepository.findById(requestId);
        ProtectionPolicies policy = protectionPoliciesConfig.fundPolicyById(idPolicies);
        if(claim.isPresent()){
            Policy requestpolicy = Policy.builder()
                    .status(policy.getIdPolicies())
                    .claimRequests(new HashSet<>(Arrays.asList(claim.get())))
                    .build();
            Product requestProduct = Product.builder()
                    .code(policy.getProductCode())
                    .policy(requestpolicy)
                    .build();
            requestpolicy.setProducts(new HashSet<>(Arrays.asList(requestProduct)));
            policyRepository.save(requestpolicy);
            claim.get().setPolicys(new HashSet<>(Arrays.asList(requestpolicy)));
            claimRequestRepository.save(claim.get());
        }
        return  claim.get();

    }

}
