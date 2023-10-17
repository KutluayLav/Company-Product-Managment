package com.kutluay.ProductManagment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {

    private String id;

    private byte[] imageData;

    private ProductDto product;

    public ImageDto(String id, byte[] imageData) {
        this.id = id;
        this.imageData = imageData;
    }
}
