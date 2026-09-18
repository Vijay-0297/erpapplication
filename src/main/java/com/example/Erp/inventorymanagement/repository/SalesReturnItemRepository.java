package com.example.Erp.inventorymanagement.repository;

import com.example.Erp.inventorymanagement.model.SalesReturnItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesReturnItemRepository
        extends JpaRepository<SalesReturnItem, Integer> {

    List<SalesReturnItem> findBySalesReturn_SalesReturnId(Integer salesReturnId);

    List<SalesReturnItem> findByProduct_ProductId(Integer productId);

    void deleteBySalesReturn_SalesReturnId(Integer salesReturnId);
}