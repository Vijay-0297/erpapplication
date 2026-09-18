package com.example.Erp.inventorymanagement.repository;

import com.example.Erp.inventorymanagement.model.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockMovementRepository
        extends JpaRepository<StockMovement, Integer> {

    List<StockMovement> findByProduct_ProductId(
            Integer productId
    );

    List<StockMovement> findByMovementType(
            String movementType
    );

    List<StockMovement> findByReferenceId(
            Integer referenceId
    );
}