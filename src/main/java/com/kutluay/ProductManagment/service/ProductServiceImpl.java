package com.kutluay.ProductManagment.service;
import com.kutluay.ProductManagment.dto.CategoryConverter;
import com.kutluay.ProductManagment.dto.ImageConverter;
import com.kutluay.ProductManagment.dto.ProductConverter;
import com.kutluay.ProductManagment.exception.ProductNotFoundException;
import com.kutluay.ProductManagment.model.Product;
import com.kutluay.ProductManagment.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final ImageConverter imageConverter;
    private final CategoryConverter categoryConverter;

    public ProductServiceImpl(ProductRepository productRepository, ProductConverter productConverter, ImageConverter imageConverter, CategoryConverter categoryConverter) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
        this.imageConverter = imageConverter;
        this.categoryConverter = categoryConverter;
    }

    @Override
    public Product addProduct(Product product) {

        System.out.println(product);
        System.out.println(product.getId());
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(String productId) {
        Product existingProduct = productRepository.findById(productId).orElse(null);
        if (existingProduct != null) {
            productRepository.delete(existingProduct);
        } else {
            throw new ProductNotFoundException(productId);
        }
    }

    @Override
    public Product updateProduct(String productId, Product updatedProduct) {
        Product existingProduct = productRepository.findById(productId).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setFeatures(updatedProduct.getFeatures());
            existingProduct.setQuantity(updatedProduct.getQuantity());
            existingProduct.setImage(updatedProduct.getImage());
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
    public Optional<Product> getProductById(String productId) {
        return productRepository.findById(productId);
    }

}
