package com.example.Erp.inventorymanagement.repository;

import com.example.Erp.inventorymanagement.model.Purchase;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

    @EntityGraph(attributePaths = {"supplier", "createdBy"})
    Optional<Purchase> findById(Integer id);

    @EntityGraph(attributePaths = {"supplier", "createdBy"})
    List<Purchase> findAll();
}