package com.bnp.cardiff.claimrequest.repository;

import com.bnp.cardiff.claimrequest.models.ClaimRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRequestRepository extends JpaRepository<ClaimRequest,Long> {
}
