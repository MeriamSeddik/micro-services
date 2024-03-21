package com.bnp.cardiff.claimRequest.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ClaimRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy="claimRequests", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Status> status;
    @ManyToMany(mappedBy="claimRequests")
    private Set<Policy> policys;





}
