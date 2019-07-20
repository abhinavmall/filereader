package com.assignment.egmat.controller;

import com.assignment.egmat.dto.form.UploadFileForm;
import com.assignment.egmat.service.FileService;
import com.assignment.egmat.util.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;

@Controller
public class QuestionFileUploadController {
    private static final Logger logger =
            LoggerFactory
                    .getLogger(
                            QuestionFileUploadController.class);

    private static final String FILE_UPLOAD_PAGE =
            "uploadquestionfile";


    @Autowired
    Utilities utilities;

    @Autowired
    FileService fileService;

    @GetMapping("/uploadquestionfile")
    public String getQuestionFileUploadPage(
            Model model) {
        logger.info("Getting file upload page");
        model.addAttribute("uploadFileForm", new UploadFileForm());
        return FILE_UPLOAD_PAGE;
    }

    @PostMapping("/uploadquestionfile")
    public String getQuestionPaperCountFromFile(
            @ModelAttribute("uploadFileForm")
            @Valid
                    UploadFileForm uploadFileForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            logger.error("No file uploaded");
            model.addAttribute("failure", "Invalid file");
            return FILE_UPLOAD_PAGE;
        }

        // Check File type
        if (null == uploadFileForm.getFile()
                || uploadFileForm.getFile().isEmpty()
                || !utilities.isTextFile(
                uploadFileForm.getFile())) {
            bindingResult.rejectValue("file",
                    null,
                    "Please select a valid text file.");
            logger.error("Invalid file uploaded");
            model.addAttribute("failure", "Invalid file");
            return FILE_UPLOAD_PAGE;
        }

        File file =
                fileService.convertMultipartFileToFile(
                        uploadFileForm.getFile());

        // TODO: Count and populate redirect attributes
        int count = 0;

        redirectAttributes.addFlashAttribute("success",
                "Total number of question papers possible = "
                        + count
                        + " from file "
                        + uploadFileForm.getFile().getOriginalFilename());

        return "redirect:/" + FILE_UPLOAD_PAGE;
    }
}
