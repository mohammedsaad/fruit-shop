package com.example.fruitshop.business.service;

import com.example.fruitshop.business.domain.CategoryDTO;
import javassist.NotFoundException;

import java.util.List;

public interface CategoryService {
    public List <CategoryDTO> getAllCategories();
    public CategoryDTO getCategoryByName(String name) throws NotFoundException;
    public CategoryDTO getCategoryById(Long id);
}
