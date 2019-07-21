package com.assignment.egmat.service;

import com.assignment.egmat.util.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class FileService {
    public static final Logger logger =
            LoggerFactory
                    .getLogger(
                            FileService.class);
    @Autowired
    Utilities utilities;

    public File convertMultipartFileToFile(
            MultipartFile multipartFile) throws IOException {
        //File file = new File(uploadDirectory +
        //        multipartFile.getOriginalFilename());
        File file = File.createTempFile(
                multipartFile.getOriginalFilename(),
                "");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
        } catch (Exception e) {
            logger.error("Exception occurred while converting multipart file " +
                    "to file", e);
            return null;
        }

        return file;
    }

    public BufferedReader getBufferedReader(File file) {
        if (utilities.isEmpty(file)) {
            logger.error("File can't be empty.");
            return null;
        }

        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(file));
        }
        catch (Exception e) {
            logger.error("Error opening uploaded file", e);
            return null;
        }
        return br;
    }
}
