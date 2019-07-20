package com.assignment.egmat.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Utilities {
    private static final Logger logger =
            LoggerFactory
                    .getLogger(
                            Utilities.class);

    private static final String TEXT_FILE_EXTENSION = "txt";

    public boolean isTextFile(MultipartFile multipartFile) {
        boolean result = false;
        if (null == multipartFile
                || multipartFile.isEmpty()) {
            logger.info("File is empty. Returning false.");
        }

        String extension = FilenameUtils
                .getExtension(
                        multipartFile.getOriginalFilename());

        if (StringUtils
                .equalsIgnoreCase(
                        extension,
                        TEXT_FILE_EXTENSION)) {
            logger.info("File is text file.");
            result = true;
        }

        return result;
    }
}
