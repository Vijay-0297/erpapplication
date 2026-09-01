package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.ProductRequest;
import com.example.Erp.inventorymanagement.dto.ProductResponse;
import com.example.Erp.inventorymanagement.exception.ResourceNotFoundException;
import com.example.Erp.inventorymanagement.model.Category;
import com.example.Erp.inventorymanagement.model.Product;
import com.example.Erp.inventorymanagement.repository.CategoryRepository;
import com.example.Erp.inventorymanagement.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductResponse create(ProductRequest request) {

        if(repository.findBySku(request.getSku()).isPresent()){
            throw new RuntimeException("SKU already exists");
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));

        Product product = Product.builder()
                .category(category)
                .productName(request.getProductName())
                .sku(request.getSku())
                .barcode(request.getBarcode())
                .purchasePrice(request.getPurchasePrice())
                .sellingPrice(request.getSellingPrice())
                .stockQuantity(request.getStockQuantity())
                .minimumStock(request.getMinimumStock())
                .unit(request.getUnit())
                .status(request.getStatus())
                .build();

        return map(repository.save(product));
    }

    @Override
    public ProductResponse update(Integer id, ProductRequest request) {

        Product product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));

        product.setCategory(category);
        product.setProductName(request.getProductName());
        product.setSku(request.getSku());
        product.setBarcode(request.getBarcode());
        product.setPurchasePrice(request.getPurchasePrice());
        product.setSellingPrice(request.getSellingPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setMinimumStock(request.getMinimumStock());
        product.setUnit(request.getUnit());
        product.setStatus(request.getStatus());

        return map(repository.save(product));
    }

    @Override
    public ProductResponse getById(Integer id) {

        return map(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found")));
    }

    @Override
    public List<ProductResponse> getAll() {

        return repository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public void delete(Integer id) {

        Product product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));

        repository.delete(product);
    }

    private ProductResponse map(Product product){

        return ProductResponse.builder()
                .productId(product.getProductId())
                .categoryId(product.getCategory().getCategoryId())
                .productName(product.getProductName())
                .sku(product.getSku())
                .barcode(product.getBarcode())
                .purchasePrice(product.getPurchasePrice())
                .sellingPrice(product.getSellingPrice())
                .stockQuantity(product.getStockQuantity())
                .minimumStock(product.getMinimumStock())
                .unit(product.getUnit())
                .status(product.getStatus())
                .createdAt(product.getCreatedAt())
                .build();
    }
}