package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.SalesReturnRequest;
import com.example.Erp.inventorymanagement.dto.SalesReturnResponse;
import com.example.Erp.inventorymanagement.model.Customer;
import com.example.Erp.inventorymanagement.model.Sales;
import com.example.Erp.inventorymanagement.model.SalesReturn;
import com.example.Erp.inventorymanagement.repository.CustomerRepository;
import com.example.Erp.inventorymanagement.repository.SalesRepository;
import com.example.Erp.inventorymanagement.repository.SalesReturnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SalesReturnServiceImpl implements SalesReturnService {

    private final SalesReturnRepository salesReturnRepository;
    private final SalesRepository saleRepository;
    private final CustomerRepository customerRepository;

    @Override
    public SalesReturnResponse create(SalesReturnRequest request) {

        Sales sale = saleRepository.findById(request.getSaleId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Sale not found with ID: " + request.getSaleId()
                        ));

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found with ID: " + request.getCustomerId()
                        ));

        SalesReturn salesReturn = SalesReturn.builder()
                .sale(sale)
                .customer(customer)
                .totalAmount(request.getTotalAmount())
                .refundStatus(
                        request.getRefundStatus() == null ||
                                request.getRefundStatus().isBlank()
                                ? "PENDING"
                                : request.getRefundStatus().toUpperCase()
                )
                .notes(request.getNotes())
                .build();

        SalesReturn saved = salesReturnRepository.save(salesReturn);

        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public SalesReturnResponse getById(Integer id) {

        SalesReturn salesReturn = salesReturnRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Sales return not found with ID: " + id
                        ));

        return mapToResponse(salesReturn);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SalesReturnResponse> getAll() {

        return salesReturnRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public SalesReturnResponse update(
            Integer id,
            SalesReturnRequest request) {

        SalesReturn salesReturn = salesReturnRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Sales return not found with ID: " + id
                        ));

        Sales sale = saleRepository.findById(request.getSaleId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Sale not found with ID: " + request.getSaleId()
                        ));

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found with ID: " + request.getCustomerId()
                        ));

        salesReturn.setSale(sale);
        salesReturn.setCustomer(customer);
        salesReturn.setTotalAmount(request.getTotalAmount());

        if (request.getRefundStatus() != null &&
                !request.getRefundStatus().isBlank()) {

            salesReturn.setRefundStatus(
                    request.getRefundStatus().toUpperCase()
            );
        }

        salesReturn.setNotes(request.getNotes());

        SalesReturn updated =
                salesReturnRepository.save(salesReturn);

        return mapToResponse(updated);
    }

    @Override
    public void delete(Integer id) {

        if (!salesReturnRepository.existsById(id)) {
            throw new RuntimeException(
                    "Sales return not found with ID: " + id
            );
        }

        salesReturnRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SalesReturnResponse> getByCustomerId(
            Long customerId) {

        return salesReturnRepository
                .findByCustomer_CustomerId(customerId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SalesReturnResponse> getBySaleId(
            Integer saleId) {

        return salesReturnRepository
                .findBySale_SaleId(saleId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SalesReturnResponse> getByRefundStatus(
            String refundStatus) {

        return salesReturnRepository
                .findByRefundStatus(refundStatus.toUpperCase())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private SalesReturnResponse mapToResponse(
            SalesReturn salesReturn) {

        return SalesReturnResponse.builder()
                .salesReturnId(salesReturn.getSalesReturnId())
                .saleId(
                        salesReturn.getSale() != null
                                ? salesReturn.getSale().getSaleId()
                                : null
                )
                .customerId(
                        salesReturn.getCustomer() != null
                                ? salesReturn.getCustomer().getCustomerId()
                                : null
                )
                .returnDate(salesReturn.getReturnDate())
                .totalAmount(salesReturn.getTotalAmount())
                .refundStatus(salesReturn.getRefundStatus())
                .notes(salesReturn.getNotes())
                .build();
    }
}