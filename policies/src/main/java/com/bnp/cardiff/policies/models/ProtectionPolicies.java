package com.bnp.cardiff.policies.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProtectionPolicies implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id", unique = true, nullable = false)
    private Integer contractId;
    private String idPolicies;
    private LocalDate date;
    private boolean history;
    private String productCode;
    private java.util.Date effectiveStartDate;
    private java.util.Date startDate;
    private java.util.Date effectiveEndDate;
    private java.util.Date currentStatusEffectiveDate;
    private java.util.Date sourceExtractionDate;
    private java.util.Date lastEndorsementEffectiveDate;
    private String distribAgencyCode;
    private String subscriptionChannelCode;
    private String coverCategoryCode;
    private String currencyCode;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Covers> covers;

}
