package com.bnp.cardiff.claimrequest.repository;

import com.bnp.cardiff.claimrequest.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status,Long> {
}
