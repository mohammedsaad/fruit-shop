package com.example.fruitshop.business.service;

import com.example.fruitshop.business.domain.CategoryDTO;
import com.example.fruitshop.business.domain.CategoryMapper;
import com.example.fruitshop.exception.ResourceNotFoundException;
import com.example.fruitshop.model.entity.Category;
import com.example.fruitshop.model.repository.CategoryRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List <CategoryDTO> getAllCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryByName(String name) throws NotFoundException {
        Optional <Category> optionalCategory = categoryRepository.findByName(name);
        if (!optionalCategory.isPresent())
            throw new ResourceNotFoundException("No resource with name :" + name);
        return categoryMapper.categoryToCategoryDTO(optionalCategory.get());
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Optional <Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
                throw new RuntimeException("No resource with id :" + id);
        return categoryMapper.categoryToCategoryDTO(optionalCategory.get());
}
}
