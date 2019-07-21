package com.assignment.egmat.service;

import com.assignment.egmat.dto.Question;
import com.assignment.egmat.util.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionArrayService {
    private static final Logger logger = LoggerFactory.getLogger
            (QuestionArrayService.class);
    @Autowired
    Utilities utilities;

    /**
     * Takes a list of questions and arranges into a 2D array (matrix)
     * Columns correspond to tags 1 to 6.
     * Rows correspond to difficulty - HARD, MEDIUM and EASY.
     *
     * @param questions
     * @return
     */
    public int[][] getQuestionMatrix(List<Question> questions) {
        int[][] result = new int[3][6];

        if (utilities.isEmpty(questions)) {
            logger.info("Question list can't be empty");
            return result;
        }

        // Iterate and populate matrix
        for (Question question: questions) {
            Question.Tag tag = question.getTag();
            Question.Difficulty difficulty = question.getDifficulty();

            // Increment count in matrix
            result[difficulty.difficultyIndex][tag.tagIndex]++;
        }

        logger.info(convertQuestionMatrix(result));
        return result;
    }

    /**
     * To print matrix for debugging purpose
     * @param matrix
     * @return
     */
    public String convertQuestionMatrix(int[][] matrix) {
        if (utilities.isEmpty(matrix)) {
            return null;
        }

        StringBuffer sb = new StringBuffer("Question Matrix - ");
        sb.append('\n');
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                int value = matrix[i][j];

                if (value == 0)
                    sb.append("  ");
                else
                    sb.append(value);

                if (j != 5) {
                    sb.append('|');
                }
            }
            sb.append('\n');
        }

        return sb.toString();
    }
}
