package com.bnp.cardiff.claimRequest.repository;

import com.bnp.cardiff.claimRequest.models.Guarantee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuaranteeRepository extends JpaRepository<Guarantee,Long> {
}
