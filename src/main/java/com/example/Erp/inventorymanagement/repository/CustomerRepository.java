package com.example.Erp.inventorymanagement.repository;

import com.example.Erp.inventorymanagement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    boolean existsByEmail(String email);

}