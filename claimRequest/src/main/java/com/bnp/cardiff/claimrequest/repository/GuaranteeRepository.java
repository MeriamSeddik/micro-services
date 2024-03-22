package com.bnp.cardiff.claimrequest.repository;

import com.bnp.cardiff.claimrequest.models.Guarantee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuaranteeRepository extends JpaRepository<Guarantee,Long> {
}
