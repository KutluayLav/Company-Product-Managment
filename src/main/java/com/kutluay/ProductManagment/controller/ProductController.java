package com.kutluay.ProductManagment.controller;

import com.kutluay.ProductManagment.model.Product;
import com.kutluay.ProductManagment.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class ProductController {

    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
    @GetMapping("/user")
    public String user(){
        return "user";
    }
    @GetMapping("/add-product")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }
    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute Product product) {
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
