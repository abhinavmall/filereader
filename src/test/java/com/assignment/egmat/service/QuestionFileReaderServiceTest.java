package com.assignment.egmat.service;

import com.assignment.egmat.dto.Question;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class QuestionFileReaderServiceTest {
    private static final Logger logger = LoggerFactory.getLogger
            (QuestionFileReaderServiceTest.class);
    private static final String QUESTION_FILE_TWO_RECORDS =
            "Question1 |HARD|Tag1\n" +
                    "Question2 |MEDIUM|Tag2\n";
    @InjectMocks
    QuestionFileReaderService questionFileReaderService = new
            QuestionFileReaderService();

    @Before
    public void beforeTest() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void afterTest() {
    }

    @Test
    public void readResourceTest() {
        // Setup
        Reader stringReader = new StringReader(QUESTION_FILE_TWO_RECORDS);
        BufferedReader br = new BufferedReader(stringReader);

        List<Question> result = null;

        try {
            result = questionFileReaderService.readBufferedResource(br);
        } catch (Exception e) {
            logger.error("Exception occurred", e);
        }

        Assert.assertNotNull(result);
        Assert.assertEquals(2,
                result.size());
        Assert.assertEquals("Question1",
                result.get(0).getQuestion());
        Assert.assertEquals(Question.Difficulty.HARD,
                result.get(0).getDifficulty());
        Assert.assertEquals(Question.Tag.TAG1,
                result.get(0).getTag());
        Assert.assertEquals("Question2",
                result.get(1).getQuestion());
        Assert.assertEquals(Question.Difficulty.MEDIUM,
                result.get(1).getDifficulty());
        Assert.assertEquals(Question.Tag.TAG2,
                result.get(1).getTag());
    }
}
