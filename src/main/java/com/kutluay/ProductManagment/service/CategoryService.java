package com.kutluay.ProductManagment.service;

import com.kutluay.ProductManagment.dto.CategoryConverter;
import com.kutluay.ProductManagment.dto.CategoryDto;
import com.kutluay.ProductManagment.model.Category;
import com.kutluay.ProductManagment.model.Product;
import com.kutluay.ProductManagment.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    private final ProductService productService;

    public CategoryService(CategoryRepository categoryRepository, CategoryConverter categoryConverter, ProductService productService) {
        this.categoryRepository = categoryRepository;
        this.categoryConverter = categoryConverter;
        this.productService = productService;
    }

    public Category addCategory(CategoryDto categoryDto, List<Long> productIds){
        Category category = categoryConverter.convertoCategory(categoryDto);

        for (Long productId : productIds) {
            Optional<Product> productOptional = productService.getProductById(productId);

            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                category.getProducts().add(product);
            }

        }

       return categoryRepository
               .save(category);
    }



}
