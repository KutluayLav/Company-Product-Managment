package com.kutluay.ProductManagment.dto;

import com.kutluay.ProductManagment.model.Category;
import com.kutluay.ProductManagment.model.Image;
import com.kutluay.ProductManagment.model.Product;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class ProductConverter {

    private final CategoryConverter categoryConverter;

    private final Logger logger = LoggerFactory.getLogger(ProductConverter.class);

    public ProductDto ProductToProductDtoConverter(Product product){
        Set<CategoryDto> categoriesDto = product.getCategories().stream()
                .map(categoryConverter::convertToDto)
                .collect(Collectors.toSet());

        System.out.println(product+" : productConverterClass");

        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getFeatures(),
                product.getQuantity(),
                product.getImage(),
                categoriesDto);
    }
    public Product ProductDtoToProductConverter(ProductDto productDto) {

        logger.debug("hata product convert :"+productDto);

        System.out.println(productDto+" : productConverterClass");

        Set<Category> categories = productDto.getCategoriesDto().stream()
                .map(categoryConverter::convertoCategory)
                .collect(Collectors.toSet());

        Product product = new Product(productDto.getId(),
                productDto.getName(),
                productDto.getPrice(),
                productDto.getFeatures(),
                productDto.getQuantity(),
                productDto.getImage(),
                categories
               );

        return product;
    }

}
