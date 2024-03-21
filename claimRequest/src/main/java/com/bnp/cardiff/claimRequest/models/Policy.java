package com.bnp.cardiff.claimRequest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private java.util.Date signedAt;
    private java.util.Date joinedAt;
    private java.util.Date effectiveAt;
    private java.util.Date terminatedAt;
    private int premiumFrequency;
    private int premiumAmount;
    private int loansAmount;
    private boolean isUnpaidInvoices;
    private int waitingPeriod;
    private int excess;
    @ManyToMany
    @JsonIgnore
    private Set<ClaimRequest> claimRequests;

    @OneToMany(mappedBy ="policy")
    private Set<Loan> loans;

    @OneToMany(mappedBy = "policy",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Product> products;

    @ManyToMany(mappedBy = "policies",cascade = CascadeType.MERGE)
    private List<Guarantee> guarantees;

    @ManyToMany(mappedBy = "policies")
    private List<PolicyRole> policyRoles;
}
