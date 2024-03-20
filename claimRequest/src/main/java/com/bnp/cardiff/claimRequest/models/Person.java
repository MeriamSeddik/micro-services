package com.bnp.cardiff.claimRequest.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {
    @Id
    private Integer id;
    private String legalPersonalityCode;
}

