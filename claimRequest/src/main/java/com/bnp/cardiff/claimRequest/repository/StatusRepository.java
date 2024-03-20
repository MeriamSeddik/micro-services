package com.bnp.cardiff.claimRequest.repository;

import com.bnp.cardiff.claimRequest.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status,Long> {
}
