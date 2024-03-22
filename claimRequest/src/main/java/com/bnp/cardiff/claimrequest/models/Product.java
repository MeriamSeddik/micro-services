package com.bnp.cardiff.claimrequest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private int label;
    private int familyType;
    private int type;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "policy_id")
    private Policy policy;
}
