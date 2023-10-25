package com.kutluay.ProductManagment.controller;

import com.kutluay.ProductManagment.dto.CategoryDto;
import com.kutluay.ProductManagment.model.Product;
import com.kutluay.ProductManagment.service.CategoryService;
import com.kutluay.ProductManagment.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CategoryController {

    private final CategoryService categoryService;
    private final ProductService productService;

    public CategoryController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/add-category")
    public String showAddCategoryForm(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        CategoryDto categoryDto = new CategoryDto();
        model.addAttribute("categoryDto", categoryDto);
        return "add-category";
    }

    @PostMapping("/add-category")
    public String addCategory(@ModelAttribute("categoryDto") CategoryDto categoryDto,
                              @RequestParam("productIds") List<Long> productIds) {
        categoryService.addCategory(categoryDto, productIds);
        return "redirect:/all-products";
    }

}
