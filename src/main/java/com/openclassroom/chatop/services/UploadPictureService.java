package com.openclassroom.chatop.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UploadPictureService {

    private final Path root = Paths.get("uploads/images");

    public String uploadFile(Long ownerId, MultipartFile picture) throws Exception {
        try {
            if (picture == null || picture.isEmpty()) {
                throw new IllegalArgumentException("File is null or empty");
            }
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }
            String filename = ownerId + "_" + UUID.randomUUID() + "_" + picture.getOriginalFilename();
            Files.copy(picture.getInputStream(), root.resolve(filename));
            return "http://localhost:3001/images/" + filename;
        } catch (Exception e) {
            throw new Exception("Could not store the file. Error: " + e.getMessage());
        }
    }

}