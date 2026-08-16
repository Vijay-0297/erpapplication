package com.example.Erp.inventorymanagement.repository;

import com.example.Erp.inventorymanagement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    boolean existsByEmail(String email);

}