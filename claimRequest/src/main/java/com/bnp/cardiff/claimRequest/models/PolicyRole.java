package com.bnp.cardiff.claimRequest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PolicyRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String role;
    @ManyToMany
    @JsonIgnore
    private Set<Policy> policies;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Person> persons;
}
