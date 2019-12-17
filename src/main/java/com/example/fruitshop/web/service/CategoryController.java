package com.example.fruitshop.web.service;

import com.example.fruitshop.business.domain.CategoryDTO;
import com.example.fruitshop.business.domain.CatorgoryListDTO;
import com.example.fruitshop.business.service.CategoryService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class CategoryController {
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("categories/{categoryId:[0-9]+}")
    ResponseEntity<CategoryDTO> getCategory(@PathVariable Long categoryId){
        CategoryDTO category =categoryService.getCategoryById(categoryId);
        return new ResponseEntity <CategoryDTO>(category, HttpStatus.OK);
    }
    @GetMapping("categories/{categoryName:[a-zA-Z]+}")
    ResponseEntity<CategoryDTO> getCategory(@PathVariable String categoryName) throws NotFoundException {
        CategoryDTO category =categoryService.getCategoryByName(categoryName);
        return new ResponseEntity <CategoryDTO>(category, HttpStatus.OK);
    }
    @GetMapping("categories")
    ResponseEntity <CatorgoryListDTO> getAllCategories(){
        CatorgoryListDTO categories =new CatorgoryListDTO(categoryService.getAllCategories());
        return new ResponseEntity <CatorgoryListDTO>(categories, HttpStatus.OK);
    }
}
