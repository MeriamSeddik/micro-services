package com.bnp.cardiff.claimRequest.repository;

import com.bnp.cardiff.claimRequest.models.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository extends JpaRepository<Policy,Long> {
}
