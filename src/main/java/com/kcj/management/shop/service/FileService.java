package com.kcj.management.shop.service;

import com.kcj.management.shop.model.File;
import com.kcj.management.shop.repository.FileRepository;
import jakarta.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    @Value("${shop.file.directory}")
    private String localDirectory;

    public File fileSave(MultipartFile multipartFile){
        File file = new File();

        file.setName(multipartFile.getName());
        file.setOriginalName(multipartFile.getOriginalFilename());
        fileRepository.save(file);
        file.setDirectory(file.initDirectory(multipartFile, file.getId(), localDirectory));
        file.setSize(multipartFile.getSize());

        return file;
    }
}
