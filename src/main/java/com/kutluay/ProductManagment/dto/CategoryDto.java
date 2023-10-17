package com.kutluay.ProductManagment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private String id;

    private String name;

    private List<ProductDto> products;

}
