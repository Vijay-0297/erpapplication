package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.SalesReturnItemRequest;
import com.example.Erp.inventorymanagement.dto.SalesReturnItemResponse;
import com.example.Erp.inventorymanagement.model.Product;
import com.example.Erp.inventorymanagement.model.SalesReturn;
import com.example.Erp.inventorymanagement.model.SalesReturnItem;
import com.example.Erp.inventorymanagement.repository.ProductRepository;
import com.example.Erp.inventorymanagement.repository.SalesReturnItemRepository;
import com.example.Erp.inventorymanagement.repository.SalesReturnRepository;
import com.example.Erp.inventorymanagement.service.SalesReturnItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SalesReturnItemServiceImpl implements SalesReturnItemService {

    private final SalesReturnItemRepository salesReturnItemRepository;
    private final SalesReturnRepository salesReturnRepository;
    private final ProductRepository productRepository;

    @Override
    public SalesReturnItemResponse create(
            SalesReturnItemRequest request) {

        SalesReturn salesReturn = salesReturnRepository
                .findById(request.getSalesReturnId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Sales return not found with ID: "
                                        + request.getSalesReturnId()
                        ));

        Product product = productRepository
                .findById(request.getProductId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Product not found with ID: "
                                        + request.getProductId()
                        ));

        BigDecimal total = request.getPrice()
                .multiply(BigDecimal.valueOf(request.getQuantity()));

        SalesReturnItem item = SalesReturnItem.builder()
                .salesReturn(salesReturn)
                .product(product)
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .total(total)
                .build();

        SalesReturnItem saved =
                salesReturnItemRepository.save(item);

        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public SalesReturnItemResponse getById(Integer id) {

        SalesReturnItem item =
                salesReturnItemRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Sales return item not found with ID: "
                                                + id
                                ));

        return mapToResponse(item);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SalesReturnItemResponse> getAll() {

        return salesReturnItemRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SalesReturnItemResponse> getBySalesReturnId(
            Integer salesReturnId) {

        return salesReturnItemRepository
                .findBySalesReturn_SalesReturnId(salesReturnId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SalesReturnItemResponse> getByProductId(
            Integer productId) {

        return salesReturnItemRepository
                .findByProduct_ProductId(productId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public SalesReturnItemResponse update(
            Integer id,
            SalesReturnItemRequest request) {

        SalesReturnItem item =
                salesReturnItemRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Sales return item not found with ID: "
                                                + id
                                ));

        SalesReturn salesReturn =
                salesReturnRepository
                        .findById(request.getSalesReturnId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Sales return not found with ID: "
                                                + request.getSalesReturnId()
                                ));

        Product product =
                productRepository
                        .findById(request.getProductId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Product not found with ID: "
                                                + request.getProductId()
                                ));

        BigDecimal total = request.getPrice()
                .multiply(BigDecimal.valueOf(request.getQuantity()));

        item.setSalesReturn(salesReturn);
        item.setProduct(product);
        item.setQuantity(request.getQuantity());
        item.setPrice(request.getPrice());
        item.setTotal(total);

        SalesReturnItem updated =
                salesReturnItemRepository.save(item);

        return mapToResponse(updated);
    }

    @Override
    public void delete(Integer id) {

        if (!salesReturnItemRepository.existsById(id)) {
            throw new RuntimeException(
                    "Sales return item not found with ID: " + id
            );
        }

        salesReturnItemRepository.deleteById(id);
    }

    private SalesReturnItemResponse mapToResponse(
            SalesReturnItem item) {

        return SalesReturnItemResponse.builder()
                .salesReturnItemId(item.getSalesReturnItemId())
                .salesReturnId(
                        item.getSalesReturn().getSalesReturnId()
                )
                .productId(
                        item.getProduct().getProductId()
                )
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .total(item.getTotal())
                .build();
    }
}