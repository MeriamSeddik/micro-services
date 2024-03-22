package com.bnp.cardiff.claimrequest.config.models;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
@Setter
public class ProtectionPolicies {

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
    private List<Covers> covers;
}
