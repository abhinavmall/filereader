package com.assignment.controller;

import com.assignment.dto.form.UploadFileForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuestionFileUploadController {
    private static final Logger logger =
            LoggerFactory
                    .getLogger(
                            QuestionFileUploadController.class);

    private static final String FILE_UPLOAD_PAGE =
            "uploadquestionfile";

    @GetMapping("/upload")
    public String getQuestionFileUploadPage(
            Model model) {
        logger.info("Getting file upload page");
        model.addAttribute("uploadFileForm", new UploadFileForm());
        return FILE_UPLOAD_PAGE;
    }
}
