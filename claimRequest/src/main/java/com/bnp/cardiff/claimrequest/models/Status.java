package com.bnp.cardiff.claimrequest.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Status implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp statusAt;
    private String status;
    private String reason;
    private String step;
    @ManyToOne
    @JoinColumn(name = "claim_id", referencedColumnName = "id", updatable = true, insertable = true)
    @JsonBackReference
    private ClaimRequest claimRequests;
}
