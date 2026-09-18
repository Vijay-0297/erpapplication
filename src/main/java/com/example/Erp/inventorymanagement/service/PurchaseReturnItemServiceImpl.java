package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.PurchaseReturnItemRequest;
import com.example.Erp.inventorymanagement.dto.PurchaseReturnItemResponse;
import com.example.Erp.inventorymanagement.model.Product;
import com.example.Erp.inventorymanagement.model.PurchaseReturn;
import com.example.Erp.inventorymanagement.model.PurchaseReturnItem;
import com.example.Erp.inventorymanagement.repository.ProductRepository;
import com.example.Erp.inventorymanagement.repository.PurchaseReturnItemRepository;
import com.example.Erp.inventorymanagement.repository.PurchaseReturnRepository;
import com.example.Erp.inventorymanagement.service.PurchaseReturnItemService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PurchaseReturnItemServiceImpl
        implements PurchaseReturnItemService {

    private final PurchaseReturnItemRepository purchaseReturnItemRepository;

    private final PurchaseReturnRepository purchaseReturnRepository;

    private final ProductRepository productRepository;


    @Override
    public PurchaseReturnItemResponse create(
            PurchaseReturnItemRequest request) {

        PurchaseReturn purchaseReturn =
                purchaseReturnRepository.findById(
                        request.getPurchaseReturnId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Purchase return not found with ID: "
                                        + request.getPurchaseReturnId()
                        )
                );

        Product product =
                productRepository.findById(
                        request.getProductId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Product not found with ID: "
                                        + request.getProductId()
                        )
                );

        BigDecimal total =
                request.getPrice()
                        .multiply(
                                BigDecimal.valueOf(
                                        request.getQuantity()
                                )
                        );

        PurchaseReturnItem item =
                PurchaseReturnItem.builder()
                        .purchaseReturn(purchaseReturn)
                        .product(product)
                        .quantity(request.getQuantity())
                        .price(request.getPrice())
                        .total(total)
                        .build();

        PurchaseReturnItem saved =
                purchaseReturnItemRepository.save(item);

        return mapToResponse(saved);
    }


    @Override
    @Transactional(readOnly = true)
    public PurchaseReturnItemResponse getById(Integer id) {

        PurchaseReturnItem item =
                purchaseReturnItemRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Purchase return item not found with ID: "
                                                + id
                                )
                        );

        return mapToResponse(item);
    }


    @Override
    @Transactional(readOnly = true)
    public List<PurchaseReturnItemResponse> getAll() {

        return purchaseReturnItemRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    @Override
    @Transactional(readOnly = true)
    public List<PurchaseReturnItemResponse>
    getByPurchaseReturnId(Integer purchaseReturnId) {

        return purchaseReturnItemRepository
                .findByPurchaseReturn_PurchaseReturnId(
                        purchaseReturnId
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    @Override
    @Transactional(readOnly = true)
    public List<PurchaseReturnItemResponse>
    getByProductId(Integer productId) {

        return purchaseReturnItemRepository
                .findByProduct_ProductId(productId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    @Override
    public PurchaseReturnItemResponse update(
            Integer id,
            PurchaseReturnItemRequest request) {

        PurchaseReturnItem item =
                purchaseReturnItemRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Purchase return item not found with ID: "
                                                + id
                                )
                        );

        PurchaseReturn purchaseReturn =
                purchaseReturnRepository.findById(
                        request.getPurchaseReturnId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Purchase return not found with ID: "
                                        + request.getPurchaseReturnId()
                        )
                );

        Product product =
                productRepository.findById(
                        request.getProductId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Product not found with ID: "
                                        + request.getProductId()
                        )
                );

        BigDecimal total =
                request.getPrice()
                        .multiply(
                                BigDecimal.valueOf(
                                        request.getQuantity()
                                )
                        );

        item.setPurchaseReturn(purchaseReturn);
        item.setProduct(product);
        item.setQuantity(request.getQuantity());
        item.setPrice(request.getPrice());
        item.setTotal(total);

        PurchaseReturnItem updated =
                purchaseReturnItemRepository.save(item);

        return mapToResponse(updated);
    }


    @Override
    public void delete(Integer id) {

        PurchaseReturnItem item =
                purchaseReturnItemRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Purchase return item not found with ID: "
                                                + id
                                )
                        );

        purchaseReturnItemRepository.delete(item);
    }


    private PurchaseReturnItemResponse mapToResponse(
            PurchaseReturnItem item) {

        return PurchaseReturnItemResponse.builder()
                .purchaseReturnItemId(
                        item.getPurchaseReturnItemId()
                )
                .purchaseReturnId(
                        item.getPurchaseReturn()
                                .getPurchaseReturnId()
                )
                .productId(
                        item.getProduct()
                                .getProductId()
                )
                .quantity(
                        item.getQuantity()
                )
                .price(
                        item.getPrice()
                )
                .total(
                        item.getTotal()
                )
                .build();
    }
}