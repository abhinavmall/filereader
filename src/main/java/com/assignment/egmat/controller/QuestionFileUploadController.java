package com.assignment.egmat.controller;

import com.assignment.egmat.dto.Question;
import com.assignment.egmat.dto.form.UploadFileForm;
import com.assignment.egmat.service.FileService;
import com.assignment.egmat.service.QuestionFileReaderService;
import com.assignment.egmat.service.QuestionPaperService;
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
import java.io.BufferedReader;
import java.io.File;
import java.util.List;

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

    @Autowired
    QuestionFileReaderService fileReaderService;

    @Autowired
    QuestionPaperService questionPaperService;

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

        // Get BR
        BufferedReader br = fileService.getBufferedReader(file);
        if (utilities.isEmpty(br)) {
            redirectAttributes.addFlashAttribute("failure",
                    "Error parsing file");
            return "redirect:/" + FILE_UPLOAD_PAGE;
        }

        // Parse and get questions from file
        List<Question> questions;
        try {
            questions = fileReaderService
                    .readBufferedResource(br);
        }
        catch (Exception e) {
            logger.error("Error while parsing file", e);
            redirectAttributes.addFlashAttribute("failure",
                    "Error parsing file");
            return "redirect:/" + FILE_UPLOAD_PAGE;
        }

        // TODO: Count and populate redirect attributes
        int count = questionPaperService.getCountOfPossibleQuestionPaper(questions);

        redirectAttributes.addFlashAttribute("success",
                "Total number of question papers possible = "
                        + count
                        + " from file "
                        + uploadFileForm.getFile().getOriginalFilename());

        return "redirect:/" + FILE_UPLOAD_PAGE;
    }
}
