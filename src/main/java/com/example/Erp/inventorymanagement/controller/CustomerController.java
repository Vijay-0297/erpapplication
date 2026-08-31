package com.example.Erp.inventorymanagement.controller;


import com.example.Erp.inventorymanagement.dto.CustomerRequest;
import com.example.Erp.inventorymanagement.dto.CustomerResponse;
import com.example.Erp.inventorymanagement.dto.CustomerUpdateRequest;
import com.example.Erp.inventorymanagement.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public CustomerResponse create(
            @RequestBody CustomerRequest request){

        return service.create(request);
    }

    @PutMapping("/{id}")
    public CustomerResponse update(
            @PathVariable Long id,
            @RequestBody CustomerUpdateRequest request){

        return service.update(id,request);
    }

    @GetMapping("/{id}")
    public CustomerResponse getById(
            @PathVariable Long id){

        return service.getById(id);
    }

    @GetMapping
    public List<CustomerResponse> getAll(){

        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id){

        service.delete(id);
        return "Customer Deleted Successfully";
    }
}
