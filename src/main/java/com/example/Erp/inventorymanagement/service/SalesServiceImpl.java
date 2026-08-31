package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.SalesRequest;
import com.example.Erp.inventorymanagement.dto.SalesResponse;
import com.example.Erp.inventorymanagement.model.Customer;
import com.example.Erp.inventorymanagement.model.Sales;
import com.example.Erp.inventorymanagement.model.User;
import com.example.Erp.inventorymanagement.repository.CustomerRepository;
import com.example.Erp.inventorymanagement.repository.SalesRepository;
import com.example.Erp.inventorymanagement.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesServiceImpl implements SalesService {

    private final SalesRepository salesRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    @Override
    public SalesResponse createSale(SalesRequest request) {

        if (salesRepository.existsByInvoiceNumber(request.getInvoiceNumber())) {
            throw new RuntimeException("Invoice number already exists");
        }

        Customer customer = customerRepository
                .findById(request.getCustomerId())
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        User user = userRepository
                .findById(request.getCreatedBy())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Sales sale = Sales.builder()
                .customer(customer)
                .invoiceNumber(request.getInvoiceNumber())
                .saleDate(OffsetDateTime.now())
                .totalAmount(request.getTotalAmount())
                .paymentStatus(request.getPaymentStatus())
                .createdBy(user)
                .build();

        Sales savedSale = salesRepository.save(sale);

        return mapToResponse(savedSale);
    }

    @Override
    public SalesResponse getSaleById(Integer saleId) {

        Sales sale = salesRepository.findById(saleId)
                .orElseThrow(() ->
                        new RuntimeException("Sale not found"));

        return mapToResponse(sale);
    }

    @Override
    public List<SalesResponse> getAllSales() {

        return salesRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public SalesResponse updateSale(
            Integer saleId,
            SalesRequest request) {

        Sales sale = salesRepository.findById(saleId)
                .orElseThrow(() ->
                        new RuntimeException("Sale not found"));

        Customer customer = customerRepository
                .findById(request.getCustomerId())
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        User user = userRepository
                .findById(request.getCreatedBy())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        sale.setCustomer(customer);
        sale.setInvoiceNumber(request.getInvoiceNumber());
        sale.setTotalAmount(request.getTotalAmount());
        sale.setPaymentStatus(request.getPaymentStatus());
        sale.setCreatedBy(user);

        Sales updatedSale = salesRepository.save(sale);

        return mapToResponse(updatedSale);
    }

    @Override
    public void deleteSale(Integer saleId) {

        Sales sale = salesRepository.findById(saleId)
                .orElseThrow(() ->
                        new RuntimeException("Sale not found"));

        salesRepository.delete(sale);
    }

    private SalesResponse mapToResponse(Sales sale) {

        return SalesResponse.builder()
                .saleId(sale.getSaleId())
                .customerId(
                        sale.getCustomer() != null
                                ? sale.getCustomer().getCustomerId()
                                : null
                )
                .invoiceNumber(sale.getInvoiceNumber())
                .saleDate(sale.getSaleDate())
                .totalAmount(sale.getTotalAmount())
                .paymentStatus(sale.getPaymentStatus())
                .createdBy(
                        sale.getCreatedBy() != null
                                ? sale.getCreatedBy().getUserId()
                                : null
                )
                .build();
    }
}