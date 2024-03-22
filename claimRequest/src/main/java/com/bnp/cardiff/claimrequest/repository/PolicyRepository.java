package com.bnp.cardiff.claimrequest.repository;

import com.bnp.cardiff.claimrequest.models.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository extends JpaRepository<Policy,Long> {
}
