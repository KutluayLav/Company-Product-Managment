package com.kutluay.ProductManagment.service;

import com.kutluay.ProductManagment.dto.ProductDto;
import com.kutluay.ProductManagment.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product addProduct(ProductDto productDto) ;

    void deleteProduct(long productId);

    Product updateProduct(long productId, Product updatedProduct);

    List<Product> getAllProducts();

    Optional<Product> getProductById(long productId);
}
