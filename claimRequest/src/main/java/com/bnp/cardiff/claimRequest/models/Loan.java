package com.bnp.cardiff.claimRequest.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private int amount;
    private int remainingAmount;
    private Date endedAt;
    @ManyToOne
    @JoinColumn(name = "policy_id")
    private Policy policy;
}
