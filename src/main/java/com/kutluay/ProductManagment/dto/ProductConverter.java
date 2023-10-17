package com.kutluay.ProductManagment.dto;

import com.kutluay.ProductManagment.model.Category;
import com.kutluay.ProductManagment.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class ProductConverter {

    private final CategoryConverter categoryConverter;
    private final ImageConverter imageConverter;

    public ProductDto ProductToProductDtoConverter(Product product){

        System.out.println(product);

        return new ProductDto(product.getId(),
                product.getName(),
                product.getPrice(),
                product.getFeatures(),
                product.getQuantity(),
                imageConverter.convertToDto(product.getImage()),
                (Set<CategoryDto>) product.getCategories()
                        .stream()
                        .map(t -> categoryConverter.convertToDto(t)).collect(Collectors.toList()));
    }
    public Product ProductDtoToProductConverter(ProductDto productDto) {

        System.out.println(productDto);

        Product product = new Product(
                productDto.getName(),
                productDto.getPrice(),
                productDto.getFeatures(),
                productDto.getQuantity(),
                imageConverter.convertToImage(productDto.getImage()),
                (Set<Category>) productDto.getCategoriesDto().stream()
                        .map(t->categoryConverter.convertoCategory(t))
                        .collect(Collectors.toSet()));

        return product;
    }

}
