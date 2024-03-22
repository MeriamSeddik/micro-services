package com.bnp.cardiff.claimrequest.repository;

import com.bnp.cardiff.claimrequest.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
