package com.bnp.cardiff.claimrequest.config.models;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Covers {

    private Integer id;
    private String idCovers;
    private String risk;
    private String guaranteeStatus;
    private String guaranteeTypeCode;
    private String currentStatusCode;
    private java.util.Date effectiveStartDate;
    private java.util.Date creationDate;
    private java.util.Date contributionStartDate;
    private java.util.Date startDates;
    private java.util.Date originExtractionDate;
    private java.util.Date contributionEndDate;
    private java.util.Date endDate;
    private java.util.Date effectiveEndDate;
    private java.util.Date updateDate;
    private java.util.Date statusDate;
    private List<ProtectionPolicies> protectionPolicies;
}
