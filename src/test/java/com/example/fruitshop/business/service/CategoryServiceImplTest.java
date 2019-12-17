package com.example.fruitshop.business.service;

import com.example.fruitshop.business.domain.CategoryDTO;
import com.example.fruitshop.business.domain.CategoryMapper;
import com.example.fruitshop.model.entity.Category;
import com.example.fruitshop.model.repository.CategoryRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CategoryServiceImplTest {
    final String NAME = "Jim";
    final long ID = 1L;
    @Mock
    CategoryRepository categoryRepository;

    CategoryServiceImpl categoryServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        categoryServiceImpl = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    void getAllCategories() {
        // given
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);
        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("Ali");
        List <Category> categories = new ArrayList <>();
        categories.add(category);
        categories.add(category2);
        when(categoryRepository.findAll()).thenReturn(categories);
        //when
        List <CategoryDTO> categoryDTOS = categoryServiceImpl.getAllCategories();
        //then
        assertEquals(categoryDTOS.size(), 2);
    }


    @Test
    void getCategoryByName() throws NotFoundException {
        //given
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        when(categoryRepository.findByName(any(String.class))).thenReturn(Optional.of(category));
        //when
        CategoryDTO categoryDTO = categoryServiceImpl.getCategoryByName(NAME);

        //then
        assertEquals(categoryDTO.getId(), ID);
    }


    @Test
    void getCategoryById() {
        //given
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        when(categoryRepository.findById(any(Long.class))).thenReturn(Optional.of(category));
        //when
        CategoryDTO categoryDTO = categoryServiceImpl.getCategoryById(ID);

        //then
        assertEquals(categoryDTO.getId(), ID);
    }
}