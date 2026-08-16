package com.example.Erp.inventorymanagement.repository;

import com.example.Erp.inventorymanagement.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuppliersRepository extends JpaRepository<Supplier, Integer> {

}