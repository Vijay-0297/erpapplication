package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.StockMovementRequest;
import com.example.Erp.inventorymanagement.dto.StockMovementResponse;
import com.example.Erp.inventorymanagement.model.Product;
import com.example.Erp.inventorymanagement.model.StockMovement;
import com.example.Erp.inventorymanagement.repository.ProductRepository;
import com.example.Erp.inventorymanagement.repository.StockMovementRepository;
import com.example.Erp.inventorymanagement.service.StockMovementService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StockMovementServiceImpl
        implements StockMovementService {

    private final StockMovementRepository stockMovementRepository;

    private final ProductRepository productRepository;


    // CREATE
    @Override
    public StockMovementResponse create(
            StockMovementRequest request) {

        Product product =
                productRepository.findById(
                        request.getProductId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Product not found with ID: "
                                        + request.getProductId()
                        )
                );

        StockMovement movement =
                StockMovement.builder()
                        .product(product)
                        .movementType(
                                request.getMovementType()
                        )
                        .quantity(
                                request.getQuantity()
                        )
                        .referenceId(
                                request.getReferenceId()
                        )
                        .notes(
                                request.getNotes()
                        )
                        .build();

        StockMovement saved =
                stockMovementRepository.save(movement);

        return mapToResponse(saved);
    }


    // GET BY ID
    @Override
    @Transactional(readOnly = true)
    public StockMovementResponse getById(
            Integer id) {

        StockMovement movement =
                stockMovementRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Stock movement not found with ID: "
                                                + id
                                )
                        );

        return mapToResponse(movement);
    }


    // GET ALL
    @Override
    @Transactional(readOnly = true)
    public List<StockMovementResponse> getAll() {

        return stockMovementRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    // GET BY PRODUCT
    @Override
    @Transactional(readOnly = true)
    public List<StockMovementResponse>
    getByProductId(Integer productId) {

        // Check product exists
        if (!productRepository.existsById(productId)) {
            throw new RuntimeException(
                    "Product not found with ID: " + productId
            );
        }

        return stockMovementRepository
                .findByProduct_ProductId(productId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    // GET BY MOVEMENT TYPE
    @Override
    @Transactional(readOnly = true)
    public List<StockMovementResponse>
    getByMovementType(String movementType) {

        return stockMovementRepository
                .findByMovementType(movementType)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    // GET BY REFERENCE ID
    @Override
    @Transactional(readOnly = true)
    public List<StockMovementResponse>
    getByReferenceId(Integer referenceId) {

        return stockMovementRepository
                .findByReferenceId(referenceId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    // UPDATE
    @Override
    public StockMovementResponse update(
            Integer id,
            StockMovementRequest request) {

        StockMovement movement =
                stockMovementRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Stock movement not found with ID: "
                                                + id
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

        movement.setProduct(product);

        movement.setMovementType(
                request.getMovementType()
        );

        movement.setQuantity(
                request.getQuantity()
        );

        movement.setReferenceId(
                request.getReferenceId()
        );

        movement.setNotes(
                request.getNotes()
        );

        StockMovement updated =
                stockMovementRepository.save(movement);

        return mapToResponse(updated);
    }


    // DELETE
    @Override
    public void delete(Integer id) {

        StockMovement movement =
                stockMovementRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Stock movement not found with ID: "
                                                + id
                                )
                        );

        stockMovementRepository.delete(movement);
    }


    // ENTITY → RESPONSE DTO
    private StockMovementResponse mapToResponse(
            StockMovement movement) {

        return StockMovementResponse.builder()
                .movementId(
                        movement.getMovementId()
                )
                .productId(
                        movement.getProduct()
                                .getProductId()
                )
                .movementType(
                        movement.getMovementType()
                )
                .quantity(
                        movement.getQuantity()
                )
                .referenceId(
                        movement.getReferenceId()
                )
                .notes(
                        movement.getNotes()
                )
                .createdAt(
                        movement.getCreatedAt()
                )
                .build();
    }
}