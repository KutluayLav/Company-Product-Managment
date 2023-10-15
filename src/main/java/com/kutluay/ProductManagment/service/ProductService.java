package com.kutluay.ProductManagment.service;

import com.kutluay.ProductManagment.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product addProduct(Product product);

    void deleteProduct(String productId);

    Product updateProduct(String productId, Product updatedProduct);

    List<Product> getAllProducts();

    Optional<Product> getProductById(String productId);
}
