package com.kutluay.ProductManagment.controller;

import com.kutluay.ProductManagment.dto.CategoryDto;
import com.kutluay.ProductManagment.dto.ProductConverter;
import com.kutluay.ProductManagment.dto.ProductDto;
import com.kutluay.ProductManagment.model.FileData;
import com.kutluay.ProductManagment.model.Product;
import com.kutluay.ProductManagment.service.FileDataService;
import com.kutluay.ProductManagment.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
public class ProductController {

    private final ProductService productService;
    private final FileDataService fileDataService;
    private final ProductConverter productConverter;
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);



    public ProductController(ProductService productService, FileDataService fileDataService, ProductConverter productConverter) {
        this.productService = productService;
        this.fileDataService = fileDataService;
        this.productConverter = productConverter;
    }

    @GetMapping("/add-product")
    public String showAddProductForm(Model model) {
        ProductDto productDto=new ProductDto();
        model.addAttribute("product", productDto);
        return "add-product";
    }
    @PostMapping("/add-product" )
    public String addProduct(@ModelAttribute("product") ProductDto productDto,
                   @RequestPart("imageFile")  MultipartFile imageFile) throws IOException {

        FileData uploadImage = fileDataService.uploadImageToFileSystem(imageFile);
        productDto.setFileData(uploadImage);

        System.out.println(productDto+" : productController");

        productService.addProduct(productDto);
        return "redirect:/admin";
    }
    @GetMapping("/update-product/{productId}")
    public String showUpdateProductForm(@PathVariable long productId, Model model) {
        Optional<Product> existingProduct = productService.getProductById(productId);
        model.addAttribute("product", existingProduct);
        return "update-product";
    }
    @PostMapping("/update-product/{productId}")
    public String updateProduct(@PathVariable long productId, @ModelAttribute Product updatedProduct) {
        productService.updateProduct(productId, updatedProduct);
        return "redirect:/admin";
    }
    @GetMapping("/delete-product/{productId}")
    public String deleteProduct(@PathVariable long productId) throws IOException {
        productService.deleteProduct(productId);
        return "redirect:/admin";
    }
    @GetMapping("/all-products")
    public String listAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "all-products";
    }
    @GetMapping("/search")
    public String searchProducts(@RequestParam String name, Model model) {
        Product searchResults = productService.searchProducts(name);
        model.addAttribute("products", searchResults);
      return "all-products";
    }
}
