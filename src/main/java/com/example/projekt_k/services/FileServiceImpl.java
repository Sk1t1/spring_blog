package com.example.projekt_k.services;

import com.example.projekt_k.dto.FileDto;
import com.example.projekt_k.entity.FilesEntity;
import com.example.projekt_k.entity.PostFiles;
import com.example.projekt_k.exeptions.BadRequestException;
import com.example.projekt_k.repository.FilesRepository;
import com.example.projekt_k.repository.PostFilesRepository;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    private final FilesRepository filesRepository;
    private final PostFilesRepository postFilesRepository;

    public FileServiceImpl(FilesRepository filesRepository,
                           PostFilesRepository postFilesRepository) {
        this.filesRepository = filesRepository;
        this.postFilesRepository = postFilesRepository;
    }

    @Override
    public FileDto saveFile(MultipartFile file) {

        try {
            byte[] bytes = file.getBytes();
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            String randomName = randomString();
            String path = file.getName() + "_" + randomName + "." + extension;
            File saved = new File(path);
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(saved));
            stream.write(bytes);
            stream.close();
            FilesEntity files = new FilesEntity();
            files.setPath(path);
            files.setName(file.getName());
            files.setFileKey(randomName);
            files = filesRepository.save(files);
            FileDto fileDto = new FileDto();
            fileDto.setId(files.getId());
            fileDto.setName(files.getName());
            fileDto.setFileKey(files.getFileKey());
            return fileDto;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private String randomString() {
        Date date = new Date();
        long time = date.getTime();
        return String.valueOf(time);
    }

    @Override
    public String getBase64ImageById(Long fileId) throws IOException {
        FilesEntity files = filesRepository.getById(fileId);
        File saved = new File(files.getPath());
        byte[] fileContent = FileUtils.readFileToByteArray(saved);
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        return encodedString;
    }

    @Override
    public String getBase64ImageByPostId(Long postId) throws IOException {
        List<PostFiles> allByPostId = postFilesRepository.findAllByPostId(postId);
        PostFiles files = allByPostId.stream().findFirst().orElseThrow(() -> new BadRequestException("File is not found"));
        FilesEntity file = files.getFile();
        return getBase64ImageById(file.getId());
    }

    @Override
    public byte[] getByteArrayImageById(Long id) throws IOException {
        FilesEntity files = filesRepository.getById(id);
        File saved = new File(files.getPath());
        byte[] fileContent = FileUtils.readFileToByteArray(saved);
        return fileContent;
    }
}

