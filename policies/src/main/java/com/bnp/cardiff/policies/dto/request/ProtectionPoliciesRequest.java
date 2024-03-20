package com.bnp.cardiff.policies.dto.request;

import com.bnp.cardiff.policies.models.Covers;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProtectionPoliciesRequest {
    private String idPolicies;
    private LocalDate date;
    private boolean history;
    private String productCode;
    private Date effectiveStartDate;
    private Date startDate;
    private Date effectiveEndDate;
    private Date currentStatusEffectiveDate;
    private Date sourceExtractionDate;
    private Date lastEndorsementEffectiveDate;
    private String distribAgencyCode;
    private String subscriptionChannelCode;
    private String coverCategoryCode;
    private String currencyCode;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Covers> covers;
}
