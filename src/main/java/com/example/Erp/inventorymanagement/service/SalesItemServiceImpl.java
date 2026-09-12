package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.SalesItemRequest;
import com.example.Erp.inventorymanagement.dto.SalesItemResponse;
import com.example.Erp.inventorymanagement.model.Product;
import com.example.Erp.inventorymanagement.model.Sales;
import com.example.Erp.inventorymanagement.model.SalesItem;
import com.example.Erp.inventorymanagement.repository.ProductRepository;
import com.example.Erp.inventorymanagement.repository.SalesRepository;
import com.example.Erp.inventorymanagement.repository.SalesItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SalesItemServiceImpl implements SalesItemService {

    private final SalesItemRepository salesItemRepository;
    private final SalesRepository saleRepository;
    private final ProductRepository productRepository;

    @Override
    public SalesItemResponse create(SalesItemRequest request) {

        Sales sale = saleRepository.findById(request.getSaleId())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Sale not found with ID: " + request.getSaleId()
                        )
                );

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Product not found with ID: " + request.getProductId()
                        )
                );

        BigDecimal total = request.getSellingPrice()
                .multiply(BigDecimal.valueOf(request.getQuantity()));

        SalesItem salesItem = SalesItem.builder()
                .sale(sale)
                .product(product)
                .quantity(request.getQuantity())
                .sellingPrice(request.getSellingPrice())
                .total(total)
                .build();

        SalesItem saved = salesItemRepository.save(salesItem);

        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public SalesItemResponse getById(Integer id) {

        SalesItem salesItem = salesItemRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Sales item not found with ID: " + id
                        )
                );

        return mapToResponse(salesItem);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SalesItemResponse> getAll() {

        return salesItemRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SalesItemResponse> getBySaleId(Integer saleId) {

        return salesItemRepository.findBySale_SaleId(saleId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SalesItemResponse> getByProductId(Integer productId) {

        return salesItemRepository.findByProduct_ProductId(productId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public SalesItemResponse update(
            Integer id,
            SalesItemRequest request
    ) {

        SalesItem salesItem = salesItemRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Sales item not found with ID: " + id
                        )
                );

        Sales sale = saleRepository.findById(request.getSaleId())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Sale not found with ID: " + request.getSaleId()
                        )
                );

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Product not found with ID: " + request.getProductId()
                        )
                );

        BigDecimal total = request.getSellingPrice()
                .multiply(BigDecimal.valueOf(request.getQuantity()));

        salesItem.setSale(sale);
        salesItem.setProduct(product);
        salesItem.setQuantity(request.getQuantity());
        salesItem.setSellingPrice(request.getSellingPrice());
        salesItem.setTotal(total);

        SalesItem updated = salesItemRepository.save(salesItem);

        return mapToResponse(updated);
    }

    @Override
    public void delete(Integer id) {

        SalesItem salesItem = salesItemRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Sales item not found with ID: " + id
                        )
                );

        salesItemRepository.delete(salesItem);
    }

    private SalesItemResponse mapToResponse(SalesItem item) {

        return SalesItemResponse.builder()
                .saleItemId(item.getSaleItemId())
                .saleId(item.getSale().getSaleId())
                .productId(item.getProduct().getProductId())
                .quantity(item.getQuantity())
                .sellingPrice(item.getSellingPrice())
                .total(item.getTotal())
                .build();
    }
}