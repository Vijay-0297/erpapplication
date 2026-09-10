
        package com.example.Erp.inventorymanagement.repository;

import com.example.Erp.inventorymanagement.model.SalesReturn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesReturnRepository
        extends JpaRepository<SalesReturn, Integer> {

    List<SalesReturn> findBySaleId(Integer saleId);

    List<SalesReturn> findByCustomerId(Integer customerId);

    List<SalesReturn> findByRefundStatusIgnoreCase(String refundStatus);

    boolean existsBySaleId(Integer saleId);
}

