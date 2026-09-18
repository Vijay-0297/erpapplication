package com.example.Erp.inventorymanagement.repository;

import com.example.Erp.inventorymanagement.model.PurchaseReturnItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseReturnItemRepository
        extends JpaRepository<PurchaseReturnItem, Integer> {

    List<PurchaseReturnItem> findByPurchaseReturn_PurchaseReturnId(
            Integer purchaseReturnId
    );

    List<PurchaseReturnItem> findByProduct_ProductId(
            Integer productId
    );
}