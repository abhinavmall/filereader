package com.assignment.egmat.service;

import com.assignment.egmat.dto.Question;
import com.assignment.egmat.util.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionPaperService {
    private static final Logger logger = LoggerFactory.getLogger
            (QuestionPaperService.class);

    @Autowired
    QuestionArrayService questionArrayService;

    @Autowired
    Utilities utilities;

    public int getCountOfPossibleQuestionPaper(
            List<Question> questions) {
        if (utilities.isEmpty(questions)) {
            logger.info("Questions are empty");
            return 0;
        }

        int[][] matrix = questionArrayService.getQuestionMatrix(questions);

        return 0;
    }
}
