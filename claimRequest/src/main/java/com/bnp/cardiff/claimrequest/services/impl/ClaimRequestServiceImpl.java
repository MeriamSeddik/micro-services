package com.bnp.cardiff.claimrequest.services.impl;

import com.bnp.cardiff.claimrequest.config.ProtectionPoliciesClient;
import com.bnp.cardiff.claimrequest.config.models.Covers;
import com.bnp.cardiff.claimrequest.config.models.ProtectionPolicies;
import com.bnp.cardiff.claimrequest.dto.request.ClaimRequestStatusRequest;
import com.bnp.cardiff.claimrequest.models.*;
import com.bnp.cardiff.claimrequest.repository.*;
import com.bnp.cardiff.claimrequest.services.IService;
import lombok.RequiredArgsConstructor;
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
            List<Covers> covers = policy.getCovers();
            List<Guarantee> guarantees = new ArrayList<>();

            Policy requestpolicy = Policy.builder()
                    .effectiveAt(policy.getEffectiveStartDate())
                    .joinedAt(policy.getStartDate())
                    .effectiveAt(policy.getEffectiveEndDate())
                    .claimRequests(new HashSet<>(Arrays.asList(claim.get())))
                    .build();
            Product requestProduct = Product.builder()
                    .code(policy.getProductCode())
                    .policy(requestpolicy)
                    .build();
            for(Covers c:covers){
                Guarantee guarantee= Guarantee.builder()
                        .code(c.getGuaranteeTypeCode())
                        .label(c.getRisk())
                        .build();
                guaranteeRepository.save(guarantee);
                guarantees.add(guarantee);
            }
            requestpolicy.setProducts(new HashSet<>(Arrays.asList(requestProduct)));
            policyRepository.save(requestpolicy);
            for(Guarantee guarantee:guarantees){
                guarantee.setPolicies(new HashSet<>(Arrays.asList(requestpolicy)));
                guaranteeRepository.save(guarantee);
            }
            requestpolicy.setGuarantees(guarantees);
            policyRepository.save(requestpolicy);
            claim.get().setPolicys(new HashSet<>(Arrays.asList(requestpolicy)));
            claimRequestRepository.save(claim.get());
        }
        return  claim.get();

    }

}
