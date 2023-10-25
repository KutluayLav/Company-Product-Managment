package com.kutluay.ProductManagment.service;

import com.kutluay.ProductManagment.model.FileData;
import com.kutluay.ProductManagment.repository.FileDataRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class FileDataService {

    private final FileDataRepository fileDataRepository;

    public FileDataService(FileDataRepository fileDataRepository) {
        this.fileDataRepository = fileDataRepository;
    }
    private final String FOLDER_PATH="C:\\Users\\kutlu\\OneDrive\\Masaüstü" +
            "\\SpringProject\\ProductManagment\\src\\main\\resources\\static\\images\\";;

    public FileData uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath=FOLDER_PATH+file.getOriginalFilename();

        FileData fileData=fileDataRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath).build());

        file.transferTo(new File(filePath));

        if (fileData != null) {
            return fileData;
        }
        return null;
    }

    public void deleteImage(Long fileId) throws IOException {

        Optional<FileData> optionalFileData = fileDataRepository.findById(fileId);

        if (optionalFileData.isPresent()) {
            FileData fileData = optionalFileData.get();
            String filePath = fileData.getFilePath();

            File file = new File(filePath);

            if (file.exists() && file.isFile()) {
                if (file.delete()) {
                    fileDataRepository.delete(fileData);
                } else {
                    throw new IOException("Dosya silinirken bir hata oluştu.");
                }
            } else {
                throw new IOException("Dosya bulunamadı veya bir dosya değil.");
            }
        } else {
            throw new IOException("Veritabanında dosya bulunamadı.");
        }
    }
}
