package com.example.Erp.inventorymanagement.repository;

import com.example.Erp.inventorymanagement.model.SalesItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesItemRepository extends JpaRepository<SalesItem, Integer> {

    List<SalesItem> findBySale_SaleId(Integer saleId);

    List<SalesItem> findByProduct_ProductId(Integer productId);

    void deleteBySale_SaleId(Integer saleId);
}