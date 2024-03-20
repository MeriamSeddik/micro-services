package com.bnp.cardiff.policies.repository;

import com.bnp.cardiff.policies.models.ProtectionPolicies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PoliciesRepository extends JpaRepository<ProtectionPolicies,Integer> {

    @Query("SELECT p FROM ProtectionPolicies p WHERE p.idPolicies = :contractId AND (:date IS NULL OR p.date = :date) AND (:history = false OR p.history = true)")
    List<ProtectionPolicies> findProtectionPolicies(
            @Param("contractId") String contractId,
            @Param("date") LocalDate date,
            @Param("history") boolean history);
    Optional<ProtectionPolicies> findByIdPolicies(String idPolicies);

}
