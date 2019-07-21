package com.assignment.egmat.service;

import com.assignment.egmat.dto.Question;
import com.assignment.egmat.util.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionMatrixService {
    private static final Logger logger = LoggerFactory.getLogger
            (QuestionMatrixService.class);
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

    public boolean isQuestionPaperPossible(int[][] matrix) {
        if (utilities.isEmpty(matrix)) {
            logger.info("Empty matrix");
            return false;
        }
        boolean result = true;

        logger.info(convertQuestionMatrix(matrix));

        // Check for empty or only one difficulty level
        // HARD - 0 MEDIUM - 1 AND EASY - 2
        for (int i = 0; i < 3; i++) {
            int count = 0;
            for (int j = 0; j < 6; j++) {
                if (matrix[i][j] > 0) {
                    count++;
                }
            }
            // Selecting two questions of a difficulty level not possible
            if (count <= 1) {
                logger.info("Selecting two questions of difficulty level {} " +
                        "not possible.", i + 1);
                result = false;
                return result;
            }
        }

        // Check for empty tags
        // Tag 1 to 6
        for (int i = 0; i < 6; i++) {
            int count = 0;
            for (int j = 0; j< 3; j++) {
                if (matrix[j][i] > 0) {
                    count++;
                }
            }

            // Selecting one questions of a tag not possible
            if (count == 0) {
                logger.info("Selecting  one questions of a tag {} " +
                        "not possible.", i);
                result = false;
                return result;
            }
        }
        logger.info("Question paper possible");
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

    /**
     * This method gets the difficulty level with least question for a given
     * tag, so that feasible sets could be created.
     * QuestionDifficulty array is to keep count of  Hard, Medium & Easy
     * questions so that we do not have to check entire matrix. LEt's say if
     * we have selected 2 HARD questions, then there is no need to check for
     * those values further in matrix.
     *
     * @param matrix
     * @param tag
     * @param questionDifficulty
     * @return
     */
    public Question.Difficulty getLeastCountDifficulty(
            int[][] matrix,
            int tag,
            int[] questionDifficulty) {
        int least = 0;

        // Set first feasible non-zero index as least
        for (int i = 0; i < 3; i++) {
            if (questionDifficulty[i] == 2) {
                continue;
            }
            if (matrix[i][tag] != 0) {
                least = i;
                break;
            }
        }

        for (int i = least; i < 3; i++) {
            if (questionDifficulty[i] == 2) {
                continue;
            }

            if (matrix[i][tag] < matrix[least][tag]
                    && matrix[i][tag] > 0) {
                least = i;
            }
        }

        switch(least) {
            case 0: return Question.Difficulty.HARD;
            case 1: return Question.Difficulty.MEDIUM;
            case 2: return Question.Difficulty.EASY;
            default: return null;
        }
    }
}
