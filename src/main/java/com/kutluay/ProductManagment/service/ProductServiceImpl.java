package com.kutluay.ProductManagment.service;
import com.kutluay.ProductManagment.dto.CategoryConverter;
import com.kutluay.ProductManagment.dto.ProductConverter;
import com.kutluay.ProductManagment.dto.ProductDto;
import com.kutluay.ProductManagment.exception.ProductNotFoundException;

import com.kutluay.ProductManagment.model.Product;
import com.kutluay.ProductManagment.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    private final FileDataService fileDataService;


    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    public ProductServiceImpl(ProductRepository productRepository, ProductConverter productConverter, CategoryConverter categoryConverter, FileDataService fileDataService) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
        this.fileDataService = fileDataService;
    }

    @Override
    public Product addProduct(ProductDto productDto){
        logger.info("productDto ="+productDto);
        logger.debug("hata product service :"+productDto);

        Product product = productConverter.ProductDtoToProductConverter(productDto);

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(long productId) throws IOException {
        Product existingProduct = productRepository.findById(productId).orElse(null);

        fileDataService.deleteImage(existingProduct.getFileData().getId());

        if (existingProduct != null) {
            productRepository.delete(existingProduct);
        } else {
            throw new ProductNotFoundException("Product not exist"+productId);
        }
    }

    @Override
    public Product updateProduct(long productId, Product updatedProduct) {
        Product existingProduct = productRepository.findById(productId).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setFeatures(updatedProduct.getFeatures());
            existingProduct.setQuantity(updatedProduct.getQuantity());
            existingProduct.setFileData(updatedProduct.getFileData());
            existingProduct.setCategories(updatedProduct.getCategories());
            return productRepository.save(existingProduct);
        } else {
            throw new ProductNotFoundException("Product Not Found!!:"+productId);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Product searchProducts(String name) {
        Product product = productRepository.findByName(name);
        return product;
    }

}
