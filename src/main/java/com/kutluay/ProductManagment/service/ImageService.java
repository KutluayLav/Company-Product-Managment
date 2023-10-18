package com.kutluay.ProductManagment.service;

import com.kutluay.ProductManagment.dto.ImageUtils;
import com.kutluay.ProductManagment.exception.ImageNotFoundException;
import com.kutluay.ProductManagment.model.Image;
import com.kutluay.ProductManagment.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image uploadImage(MultipartFile imageFile) throws IOException {
        var imageToSave = Image.builder()
                .name(imageFile.getOriginalFilename())
                .type(imageFile.getContentType())
                .imageData(ImageUtils.compressImage(imageFile.getBytes()))
                .build();

        imageRepository.save(imageToSave);
        return imageToSave;
    }
    public void deleteImage(long id){
        Optional<Image> image = imageRepository.findById(id);
        if (image != null){
            imageRepository.deleteById(id);
        }else {
            throw new ImageNotFoundException("Image Not exist:");
        }

    }
}
