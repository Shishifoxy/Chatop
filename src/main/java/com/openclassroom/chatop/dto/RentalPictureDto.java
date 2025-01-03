package com.openclassroom.chatop.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;



@EqualsAndHashCode(callSuper = true)
@Data
public class RentalPictureDto extends RentalsDto{

    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}