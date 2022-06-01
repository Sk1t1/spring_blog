package com.example.projekt_k.dto;

public class FilesDto {

    private String base64File;

    public FilesDto(String base64File) {
        this.base64File = base64File;
    }

    public FilesDto() {
    }

    public String getBase64File() {
        return base64File;
    }

    public void setBase64File(String base64File) {
        this.base64File = base64File;
    }
}
