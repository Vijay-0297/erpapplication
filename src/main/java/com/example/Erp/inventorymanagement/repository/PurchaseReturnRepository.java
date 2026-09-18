package com.example.Erp.inventorymanagement.repository;

import com.example.Erp.inventorymanagement.model.PurchaseReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseReturnRepository
        extends JpaRepository<PurchaseReturn, Integer> {

    List<PurchaseReturn> findByPurchase_PurchaseId(
            Integer purchaseId
    );

    List<PurchaseReturn> findBySupplier_SupplierId(
            Integer supplierId
    );
}