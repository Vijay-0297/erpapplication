package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.SuppliersRequest;
import com.example.Erp.inventorymanagement.dto.SuppliersResponse;
import com.example.Erp.inventorymanagement.exception.ResourceNotFoundException;
import com.example.Erp.inventorymanagement.model.Supplier;
import com.example.Erp.inventorymanagement.repository.SuppliersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SuppliersServiceImpl implements SuppliersService {

    private final SuppliersRepository repository;

    @Override
    public SuppliersResponse createSupplier(SuppliersRequest request) {

        Supplier supplier = Supplier.builder()
                .supplierId(request.getSupplierId())
                .supplierName(request.getSupplierName())
                .contactPerson(request.getContactPerson())
                .phone(request.getPhone())
                .email(request.getEmail())
                .address(request.getAddress())
                .status(request.getStatus())
                .build();

        Supplier saved = repository.save(supplier);

        return map(saved);
    }

    @Override
    public SuppliersResponse getSupplier(Integer id) {

        Supplier supplier = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));

        return map(supplier);
    }

    @Override
    public List<SuppliersResponse> getAllSuppliers() {

        return repository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public SuppliersResponse updateSupplier(Integer id, SuppliersRequest request) {

        Supplier supplier = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));

        supplier.setSupplierName(request.getSupplierName());
        supplier.setContactPerson(request.getContactPerson());
        supplier.setPhone(request.getPhone());
        supplier.setEmail(request.getEmail());
        supplier.setAddress(request.getAddress());
        supplier.setStatus(request.getStatus());

        Supplier updated = repository.save(supplier);

        return map(updated);
    }

    @Override
    public void deleteSupplier(Integer id) {

        Supplier supplier = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));

        repository.delete(supplier);

    }

    private SuppliersResponse map(Supplier supplier) {

        return SuppliersResponse.builder()
                .supplierId(supplier.getSupplierId())
                .supplierName(supplier.getSupplierName())
                .contactPerson(supplier.getContactPerson())
                .phone(supplier.getPhone())
                .email(supplier.getEmail())
                .address(supplier.getAddress())
                .status(supplier.getStatus())
                .createdAt(supplier.getCreatedAt())
                .build();
    }
}