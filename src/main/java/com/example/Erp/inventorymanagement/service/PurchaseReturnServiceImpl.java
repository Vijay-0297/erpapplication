package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.PurchaseReturnRequest;
import com.example.Erp.inventorymanagement.dto.PurchaseReturnResponse;
import com.example.Erp.inventorymanagement.model.Purchase;
import com.example.Erp.inventorymanagement.model.PurchaseReturn;
import com.example.Erp.inventorymanagement.model.Supplier;
import com.example.Erp.inventorymanagement.repository.PurchaseRepository;
import com.example.Erp.inventorymanagement.repository.PurchaseReturnRepository;
import com.example.Erp.inventorymanagement.repository.SuppliersRepository;
import com.example.Erp.inventorymanagement.service.PurchaseReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PurchaseReturnServiceImpl
        implements PurchaseReturnService {

    private final PurchaseReturnRepository purchaseReturnRepository;

    private final PurchaseRepository purchaseRepository;

    private final SuppliersRepository supplierRepository;

    @Override
    public PurchaseReturnResponse create(
            PurchaseReturnRequest request) {

        // Find purchase
        Purchase purchase = purchaseRepository
                .findById(request.getPurchaseId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Purchase not found with ID: "
                                        + request.getPurchaseId()
                        ));

        // Find supplier
        Supplier supplier = supplierRepository
                .findById(request.getSupplierId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Supplier not found with ID: "
                                        + request.getSupplierId()
                        ));

        BigDecimal totalAmount = request.getTotalAmount();

        if (totalAmount == null) {
            totalAmount = BigDecimal.ZERO;
        }

        PurchaseReturn purchaseReturn =
                PurchaseReturn.builder()
                        .purchase(purchase)
                        .supplier(supplier)
                        .totalAmount(totalAmount)
                        .notes(request.getNotes())
                        .build();

        PurchaseReturn saved =
                purchaseReturnRepository.save(purchaseReturn);

        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public PurchaseReturnResponse getById(Integer id) {

        PurchaseReturn purchaseReturn =
                purchaseReturnRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Purchase return not found with ID: "
                                                + id
                                ));

        return mapToResponse(purchaseReturn);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PurchaseReturnResponse> getAll() {

        return purchaseReturnRepository
                .findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PurchaseReturnResponse> getByPurchaseId(
            Integer purchaseId) {

        return purchaseReturnRepository
                .findByPurchase_PurchaseId(purchaseId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PurchaseReturnResponse> getBySupplierId(
            Integer supplierId) {

        return purchaseReturnRepository
                .findBySupplier_SupplierId(supplierId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public PurchaseReturnResponse update(
            Integer id,
            PurchaseReturnRequest request) {

        PurchaseReturn purchaseReturn =
                purchaseReturnRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Purchase return not found with ID: "
                                                + id
                                ));

        Purchase purchase =
                purchaseRepository.findById(
                        request.getPurchaseId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Purchase not found with ID: "
                                        + request.getPurchaseId()
                        ));

        Supplier supplier =
                supplierRepository.findById(
                        request.getSupplierId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Supplier not found with ID: "
                                        + request.getSupplierId()
                        ));

        BigDecimal totalAmount = request.getTotalAmount();

        if (totalAmount == null) {
            totalAmount = BigDecimal.ZERO;
        }

        purchaseReturn.setPurchase(purchase);
        purchaseReturn.setSupplier(supplier);
        purchaseReturn.setTotalAmount(totalAmount);
        purchaseReturn.setNotes(request.getNotes());

        PurchaseReturn updated =
                purchaseReturnRepository.save(purchaseReturn);

        return mapToResponse(updated);
    }

    @Override
    public void delete(Integer id) {

        if (!purchaseReturnRepository.existsById(id)) {
            throw new RuntimeException(
                    "Purchase return not found with ID: " + id
            );
        }

        purchaseReturnRepository.deleteById(id);
    }

    private PurchaseReturnResponse mapToResponse(
            PurchaseReturn purchaseReturn) {

        return PurchaseReturnResponse.builder()
                .purchaseReturnId(
                        purchaseReturn.getPurchaseReturnId()
                )
                .purchaseId(
                        purchaseReturn
                                .getPurchase()
                                .getPurchaseId()
                )
                .supplierId(
                        purchaseReturn
                                .getSupplier()
                                .getSupplierId()
                )
                .returnDate(
                        purchaseReturn.getReturnDate()
                )
                .totalAmount(
                        purchaseReturn.getTotalAmount()
                )
                .notes(
                        purchaseReturn.getNotes()
                )
                .build();
    }
}