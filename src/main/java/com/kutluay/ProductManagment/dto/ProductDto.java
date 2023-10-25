package com.kutluay.ProductManagment.dto;

import com.kutluay.ProductManagment.model.FileData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {


    private long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private BigDecimal price;
    @NotEmpty
    private String features;
    @NotEmpty
    private long Quantity;

    private FileData fileData;

    private Set<CategoryDto> categoriesDto=new HashSet<>();
}
