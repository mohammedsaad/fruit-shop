package com.example.fruitshop.business.domain;


import com.example.fruitshop.model.entity.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CategoryMapperTest {

    public static final String NAME = "Jim";
    public static final long ID = 1L;

    @Test
    public void categoryToCategoryDTOTest(){
        // given
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);
        //when
        CategoryDTO mappedCategory= CategoryMapper.INSTANCE.categoryToCategoryDTO(category);
        //then
        assertEquals(mappedCategory.getId(),ID);
        assertEquals(mappedCategory.getName(), NAME);
    }

}