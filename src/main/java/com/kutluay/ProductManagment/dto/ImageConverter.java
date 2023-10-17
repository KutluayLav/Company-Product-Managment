package com.kutluay.ProductManagment.dto;

import com.kutluay.ProductManagment.model.Image;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ImageConverter {


    public ImageDto convertToDto(Image image) {
        return new ImageDto(image.getId(),
                image.getImageData()
                );
    }
    public Image convertToImage(ImageDto imageDto){
        return new Image(imageDto.getId()
                ,imageDto.getImageData());
    }


}
