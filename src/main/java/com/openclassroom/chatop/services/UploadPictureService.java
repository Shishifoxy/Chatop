package com.openclassroom.chatop.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UploadPictureService  {

    private final Path root = Paths.get("uploads/images");

    public String uploadFile(Long ownerId, MultipartFile file) throws Exception {
        try {
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }

            String filename = String.valueOf(ownerId) + "_" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), root.resolve(filename));
            return "http://localhost:8080/images/" + filename;
        } catch (Exception e) {
            throw new Exception("Could not store the file. Error: " + e.getMessage());
        }
    }
}