package com.bnp.cardiff.claimrequest.dto.response;

import com.bnp.cardiff.claimrequest.models.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClaimRequestResponse {
    private Long id;
    private String externalId;
    private String channel;
    private Timestamp emittedAt;
    private Timestamp receivedAt;
    private Timestamp createdAt;
    private int occuredAt;
    private Timestamp processStartedAt;
    private Date processLimitDate;
    private Timestamp lastModifiedAt;
    private String lastModifiedReason;
    private boolean hasMedicalData;
    private String businessLine;
    private boolean isDematerialised;
    private String guarantee;
    private Boolean hasAcceptedGDPRt;
    private Date hasAcceptedGDPRat;
    private Boolean hasAcceptedUGC;
    private Date hasAcceptedUGCat;
    private boolean isDematerialisedat;
    private boolean isDigitalised;
    private int claimAge;
    private List<Status> status;
}
