package com.assignment.service;

import com.assignment.dto.Question;

import java.io.File;
import java.util.List;

public class QuestionFileReaderService implements FileReaderService<Question> {
    @Override
    public List<Question> readAllLines(File file) {
        return null;
    }
}
