package com.kutluay.ProductManagment.controller;

import com.kutluay.ProductManagment.dto.ProductConverter;
import com.kutluay.ProductManagment.dto.ProductDto;
import com.kutluay.ProductManagment.model.Product;
import com.kutluay.ProductManagment.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ProductController {

    private final ProductService productService;
    private final ProductConverter productConverter;


    public ProductController(ProductService productService, ProductConverter productConverter) {
        this.productService = productService;
        this.productConverter = productConverter;
    }

    @GetMapping("/add-product")
    public String showAddProductForm(Model model) {
        ProductDto productDto=new ProductDto();
        model.addAttribute("product", productDto);
        return "add-product";
    }
    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute("product") Product product) {



        productService.addProduct(product);
        return "redirect:/admin";
    }
    @GetMapping("/update-product/{productId}")
    public String showUpdateProductForm(@PathVariable String productId, Model model) {
        Optional<Product> existingProduct = productService.getProductById(productId);
        model.addAttribute("product", existingProduct);
        return "update-product";
    }
    @PostMapping("/update-product/{productId}")
    public String updateProduct(@PathVariable String productId, @ModelAttribute Product updatedProduct) {
        productService.updateProduct(productId, updatedProduct);
        return "redirect:/admin";
    }
    @GetMapping("/delete-product/{productId}")
    public String deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(productId);
        return "redirect:/admin";
    }
    @GetMapping("/all-products")
    public String listAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "all-products";
    }
}
