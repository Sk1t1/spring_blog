package com.example.projekt_k.services;

import com.example.projekt_k.dto.FileDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    FileDto saveFile(MultipartFile multipartFile);

    String getBase64ImageById(Long id) throws IOException;

    String getBase64ImageByPostId(Long postId) throws IOException;

    byte[] getByteArrayImageById(Long id) throws IOException;


}
