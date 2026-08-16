package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.PurchaseItemRequest;
import com.example.Erp.inventorymanagement.dto.PurchaseItemResponse;
import com.example.Erp.inventorymanagement.model.Product;
import com.example.Erp.inventorymanagement.model.Purchase;
import com.example.Erp.inventorymanagement.model.PurchaseItem;
import com.example.Erp.inventorymanagement.repository.ProductRepository;
import com.example.Erp.inventorymanagement.repository.PurchaseItemRepository;
import com.example.Erp.inventorymanagement.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseItemServiceImpl implements PurchaseItemService {

    private final PurchaseItemRepository purchaseItemRepository;
    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;

    @Override
    public PurchaseItemResponse create(PurchaseItemRequest request) {

        Purchase purchase = purchaseRepository
                .findById(request.getPurchaseId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Purchase not found with id: "
                                        + request.getPurchaseId()));

        Product product = productRepository
                .findById(request.getProductId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Product not found with id: "
                                        + request.getProductId()));

        if (request.getQuantity() == null || request.getQuantity() <= 0) {
            throw new RuntimeException("Quantity must be greater than 0");
        }

        if (request.getPurchasePrice() == null ||
                request.getPurchasePrice().compareTo(BigDecimal.ZERO) < 0) {

            throw new RuntimeException(
                    "Purchase price must be greater than or equal to 0");
        }

        BigDecimal total = request.getPurchasePrice()
                .multiply(BigDecimal.valueOf(request.getQuantity()));

        PurchaseItem purchaseItem = PurchaseItem.builder()
                .purchase(purchase)
                .product(product)
                .quantity(request.getQuantity())
                .purchasePrice(request.getPurchasePrice())
                .total(total)
                .build();

        PurchaseItem saved = purchaseItemRepository.save(purchaseItem);

        return mapToResponse(saved);
    }

    @Override
    public PurchaseItemResponse getById(Integer id) {

        PurchaseItem purchaseItem = purchaseItemRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Purchase item not found with id: " + id));

        return mapToResponse(purchaseItem);
    }

    @Override
    public List<PurchaseItemResponse> getAll() {

        return purchaseItemRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<PurchaseItemResponse> getByPurchaseId(Integer purchaseId) {

        return purchaseItemRepository
                .findByPurchasePurchaseId(purchaseId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<PurchaseItemResponse> getByProductId(Integer productId) {

        return purchaseItemRepository
                .findByProductProductId(productId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public PurchaseItemResponse update(
            Integer id,
            PurchaseItemRequest request) {

        PurchaseItem existing = purchaseItemRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Purchase item not found with id: " + id));

        Purchase purchase = purchaseRepository
                .findById(request.getPurchaseId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Purchase not found with id: "
                                        + request.getPurchaseId()));

        Product product = productRepository
                .findById(request.getProductId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Product not found with id: "
                                        + request.getProductId()));

        if (request.getQuantity() == null || request.getQuantity() <= 0) {
            throw new RuntimeException("Quantity must be greater than 0");
        }

        if (request.getPurchasePrice() == null ||
                request.getPurchasePrice().compareTo(BigDecimal.ZERO) < 0) {

            throw new RuntimeException(
                    "Purchase price must be greater than or equal to 0");
        }

        BigDecimal total = request.getPurchasePrice()
                .multiply(BigDecimal.valueOf(request.getQuantity()));

        existing.setPurchase(purchase);
        existing.setProduct(product);
        existing.setQuantity(request.getQuantity());
        existing.setPurchasePrice(request.getPurchasePrice());
        existing.setTotal(total);

        PurchaseItem updated =
                purchaseItemRepository.save(existing);

        return mapToResponse(updated);
    }

    @Override
    public void delete(Integer id) {

        PurchaseItem existing = purchaseItemRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Purchase item not found with id: " + id));

        purchaseItemRepository.delete(existing);
    }

    private PurchaseItemResponse mapToResponse(
            PurchaseItem purchaseItem) {

        return PurchaseItemResponse.builder()
                .purchaseItemId(purchaseItem.getPurchaseItemId())
                .purchaseId(
                        purchaseItem.getPurchase().getPurchaseId())
                .productId(
                        purchaseItem.getProduct().getProductId())
                .quantity(purchaseItem.getQuantity())
                .purchasePrice(purchaseItem.getPurchasePrice())
                .total(purchaseItem.getTotal())
                .build();
    }
}