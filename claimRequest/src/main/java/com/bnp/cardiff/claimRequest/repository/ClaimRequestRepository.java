package com.bnp.cardiff.claimRequest.repository;

import com.bnp.cardiff.claimRequest.models.ClaimRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRequestRepository extends JpaRepository<ClaimRequest,Long> {
}
