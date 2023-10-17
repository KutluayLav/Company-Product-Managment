package com.kutluay.ProductManagment.dto;

import com.kutluay.ProductManagment.model.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;

@Component
@AllArgsConstructor
public class CategoryConverter {

    public CategoryDto convertToDto(Category category) {
       return new CategoryDto(category.getId(),
               category.getName(),new ArrayList<>());
    }
    public Category convertoCategory(CategoryDto categoryDto){
        return new Category(categoryDto.getId()
                , categoryDto.getName()
                , new HashSet<>());
    }
}
