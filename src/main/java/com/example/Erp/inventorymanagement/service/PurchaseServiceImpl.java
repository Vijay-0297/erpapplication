package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.PurchaseRequest;
import com.example.Erp.inventorymanagement.dto.PurchaseResponse;
import com.example.Erp.inventorymanagement.exception.ResourceNotFoundException;
import com.example.Erp.inventorymanagement.model.Purchase;
import com.example.Erp.inventorymanagement.model.Supplier;
import com.example.Erp.inventorymanagement.model.User;
import com.example.Erp.inventorymanagement.repository.PurchaseRepository;
import com.example.Erp.inventorymanagement.repository.SuppliersRepository;
import com.example.Erp.inventorymanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final SuppliersRepository supplierRepository;
    private final UserRepository userRepository;

    @Override
    public PurchaseResponse create(PurchaseRequest request) {

        Supplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));

        User user = userRepository.findById(request.getCreatedBy())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Purchase purchase = Purchase.builder()
                .supplier(supplier)
                .invoiceNumber(request.getInvoiceNumber())
                .purchaseDateTime(request.getPurchaseDateTime())
                .totalAmount(request.getTotalAmount())
                .paymentStatus(request.getPaymentStatus())
                .createdBy(user)
                .build();

        return map(purchaseRepository.save(purchase));
    }

    @Override
    public PurchaseResponse update(Integer id, PurchaseRequest request) {

        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase not found"));

        Supplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));

        User user = userRepository.findById(request.getCreatedBy())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        purchase.setSupplier(supplier);
        purchase.setInvoiceNumber(request.getInvoiceNumber());
        purchase.setPurchaseDateTime(request.getPurchaseDateTime());
        purchase.setTotalAmount(request.getTotalAmount());
        purchase.setPaymentStatus(request.getPaymentStatus());
        purchase.setCreatedBy(user);

        return map(purchaseRepository.save(purchase));
    }

    @Override
    public PurchaseResponse getById(Integer id) {

        return map(purchaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase not found")));
    }

    @Override
    public List<PurchaseResponse> getAll() {

        return purchaseRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public void delete(Integer id) {

        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase not found"));

        purchaseRepository.delete(purchase);
    }

    private PurchaseResponse map(Purchase purchase) {

        return PurchaseResponse.builder()
                .purchaseId(purchase.getPurchaseId())
                .supplierId(purchase.getSupplier().getSupplierId())
                .supplierName(purchase.getSupplier().getSupplierName() != null
                                ? purchase.getSupplier().getSupplierName()
                                : null
                )
                .invoiceNumber(purchase.getInvoiceNumber())
                .purchaseDateTime(purchase.getPurchaseDateTime())
                .totalAmount(purchase.getTotalAmount())
                .paymentStatus(purchase.getPaymentStatus())
                .createdBy(purchase.getCreatedBy().getUserId())
                .build();
    }
}