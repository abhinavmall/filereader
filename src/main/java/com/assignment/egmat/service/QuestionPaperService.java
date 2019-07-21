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
    QuestionMatrixService questionMatrixService;

    @Autowired
    Utilities utilities;

    private static final int MAX_QUESTIONS_IN_PAPAER = 10;

    public int getCountOfPossibleQuestionPaper(
            List<Question> questions) {
        if (utilities.isEmpty(questions)
                || questions.size() < MAX_QUESTIONS_IN_PAPAER) {
            logger.info("Questions are empty or less than 10");
            return 0;
        }

        int count = 0;
        int[][] matrix = questionMatrixService.getQuestionMatrix(questions);

        // Calculate maximum question paper possible
        int maxQuestionPapers = questions.size()/10;

        for (int i = 0; i < maxQuestionPapers; i++) {
            // Check feasibility of creating a question paper.
            // Two of each difficulty
            // One of each tag
            if (!questionMatrixService.isQuestionPaperPossible(matrix)) {
                logger.info("Selecting further questions for question paper " +
                        "not possible. Total count = {}", count);
                logger.info(questionMatrixService.convertQuestionMatrix(matrix));
                return count;
            }

            // Question paper feasible
            // Target is to select a set of 6 questions which fulfills
            // minimum criteria for a question paper.
            // Remaining 4 questions per set could be selected from the pool
            // at random that remains.
            // Selecting remaining does not matter as we are focusing on
            // count.

            int questionDifficulty[] = new int[3];
            for (int tag = 0; tag < 6; tag++) {
                Question.Difficulty difficulty =
                        questionMatrixService
                                .getLeastCountDifficulty(
                                        matrix,
                                        tag,
                                        questionDifficulty);

                if (utilities.isEmpty(difficulty)) {
                    // Won't be executed as we are already checking feasibility
                    return count;
                }

                logger.info("Least question count for Tag {} is for {} = {}",
                        tag + 1,
                        difficulty.difficultyLevel,
                        matrix[difficulty.difficultyIndex][tag]);

                // Decrease question count
                matrix[difficulty.difficultyIndex][tag]--;

                // Increase questionDifficultyCount
                questionDifficulty[difficulty.difficultyIndex]++;
            }
            count++;
        }

        return count;
    }
}
