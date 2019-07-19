package com.assignment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

@Service
public class FileService {
    public static final Logger logger =
            LoggerFactory
                    .getLogger(
                            FileService.class);

    @Value("${file.upload-dir}")
    private String uploadDirectory;

    public File convertMultipartFileToFile(
            MultipartFile multipartFile) {
        File file = new File(uploadDirectory +
                multipartFile.getOriginalFilename());

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
        }
        catch (Exception e) {
            logger.error("Exception occurred while converting multipart file " +
                    "to file", e);
            return null;
        }

        return file;
    }
}
