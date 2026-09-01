package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.CategoryRequest;
import com.example.Erp.inventorymanagement.dto.CategoryResponse;
import com.example.Erp.inventorymanagement.model.Category;
import com.example.Erp.inventorymanagement.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final ModelMapper mapper;

    @Override
    public CategoryResponse create(CategoryRequest request) {

        if(repository.existsByCategoryName(request.getCategoryName())){
            throw new RuntimeException("Category already exists");
        }

        Category category = mapper.map(request, Category.class);

        if(category.getStatus()==null){
            category.setStatus("active");
        }

        Category saved = repository.save(category);

        return mapper.map(saved, CategoryResponse.class);
    }

    @Override
    public List<CategoryResponse> getAll() {

        return repository.findAll()
                .stream()
                .map(category -> mapper.map(category, CategoryResponse.class))
                .toList();
    }

    @Override
    public CategoryResponse getById(Integer id) {

        Category category = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        return mapper.map(category, CategoryResponse.class);
    }

    @Override
    public CategoryResponse update(Integer id, CategoryRequest request) {

        Category category = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());
        category.setStatus(request.getStatus());

        Category updated = repository.save(category);

        return mapper.map(updated, CategoryResponse.class);
    }

    @Override
    public void delete(Integer id) {

        Category category = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        repository.delete(category);
    }
}