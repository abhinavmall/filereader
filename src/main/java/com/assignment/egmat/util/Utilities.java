package com.assignment.egmat.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Map;

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

    /**
     * This method returns true if the collection is null or is empty.
     * @param collection
     * @return true | false
     */
    public boolean isEmpty( Collection<?> collection ){
        if( collection == null || collection.isEmpty() ){
            return true;
        }
        return false;
    }

    /**
     * This method returns true of the map is null or is empty.
     * @param map
     * @return true | false
     */
    public boolean isEmpty( Map<?, ?> map ){
        if( map == null || map.isEmpty() ){
            return true;
        }
        return false;
    }

    /**
     * This method returns true if the object is null.
     * @param object
     * @return true | false
     */
    public boolean isEmpty( Object object ){
        if( object == null ){
            return true;
        }
        return false;
    }

    /**
     * This method returns true if the input array is null or its length is zero.
     * @param array
     * @return true | false
     */
    public boolean isEmpty( Object[] array ){
        if( array == null || array.length == 0 ){
            return true;
        }
        return false;
    }

    /**
     * This method returns true if the input string is null or its length is zero.
     * @param string
     * @return true | false
     */
    public boolean isEmpty( String string ){
        if( string == null || string.trim().length() == 0 ){
            return true;
        }
        return false;
    }
}
