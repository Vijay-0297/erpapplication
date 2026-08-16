package com.example.Erp.inventorymanagement.repository;

import com.example.Erp.inventorymanagement.model.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseItemRepository
        extends JpaRepository<PurchaseItem, Integer> {

    List<PurchaseItem> findByPurchasePurchaseId(Integer purchaseId);

    List<PurchaseItem> findByProductProductId(Integer productId);
}