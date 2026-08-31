package com.example.Erp.inventorymanagement.repository;

import com.example.Erp.inventorymanagement.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Integer> {

    boolean existsByInvoiceNumber(String invoiceNumber);
}