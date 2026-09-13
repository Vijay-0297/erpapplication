package com.example.Erp.inventorymanagement.repository;

import com.example.Erp.inventorymanagement.model.SalesReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesReturnRepository
        extends JpaRepository<SalesReturn, Integer> {

    List<SalesReturn> findByCustomer_CustomerId(Long customerId);

    List<SalesReturn> findBySale_SaleId(Integer saleId);

    List<SalesReturn> findByRefundStatus(String refundStatus);
}