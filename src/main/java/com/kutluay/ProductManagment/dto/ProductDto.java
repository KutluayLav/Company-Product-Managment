package com.kutluay.ProductManagment.dto;

import com.kutluay.ProductManagment.model.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {


    private String id;
    @NotEmpty
    private String name;
    @NotEmpty
    private double price;

    private String features;
    @NotEmpty
    private long Quantity;

    private ImageDto image;

    private Set<CategoryDto> categoriesDto=new HashSet<>();
}
