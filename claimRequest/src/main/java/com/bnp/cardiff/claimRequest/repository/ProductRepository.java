package com.bnp.cardiff.claimRequest.repository;

import com.bnp.cardiff.claimRequest.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
