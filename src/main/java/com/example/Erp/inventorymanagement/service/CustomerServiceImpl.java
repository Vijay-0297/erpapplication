package com.example.Erp.inventorymanagement.service;


import com.example.Erp.inventorymanagement.dto.CustomerRequest;
import com.example.Erp.inventorymanagement.dto.CustomerResponse;
import com.example.Erp.inventorymanagement.dto.CustomerUpdateRequest;
import com.example.Erp.inventorymanagement.model.Customer;
import com.example.Erp.inventorymanagement.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Override
    public CustomerResponse create(CustomerRequest request) {

        if(request.getEmail()!=null &&
                repository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        Customer customer = Customer.builder()
                .customerName(request.getCustomerName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .address(request.getAddress())
                .status("active")
                .build();

        repository.save(customer);

        return map(customer);
    }

    @Override
    public CustomerResponse update(Integer id,
                                   CustomerUpdateRequest request) {

        Customer customer = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Customer Not Found"));

        customer.setCustomerName(request.getCustomerName());
        customer.setPhone(request.getPhone());
        customer.setEmail(request.getEmail());
        customer.setAddress(request.getAddress());
        customer.setStatus(request.getStatus());

        repository.save(customer);

        return map(customer);
    }

    @Override
    public CustomerResponse getById(Integer id) {

        Customer customer = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Customer Not Found"));

        return map(customer);
    }

    @Override
    public List<CustomerResponse> getAll() {

        return repository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public void delete(Integer id) {

        Customer customer = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Customer Not Found"));

        repository.delete(customer);
    }

    private CustomerResponse map(Customer customer){

        return CustomerResponse.builder()
                .customerId(customer.getCustomerId())
                .customerName(customer.getCustomerName())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .status(customer.getStatus())
                .createdAt(customer.getCreatedAt())
                .build();
    }
}
