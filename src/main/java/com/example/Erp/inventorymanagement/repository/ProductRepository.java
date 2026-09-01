package com.example.Erp.inventorymanagement.repository;


import com.example.Erp.inventorymanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    Optional<Product> findBySku(String sku);

}