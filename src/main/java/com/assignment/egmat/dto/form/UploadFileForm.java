package com.assignment.egmat.dto.form;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.AssertTrue;

public class UploadFileForm {
    private MultipartFile file;

    @AssertTrue(message = "File must be provided")
    public boolean isFile() {
        return (file != null) && (! file.isEmpty());
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
