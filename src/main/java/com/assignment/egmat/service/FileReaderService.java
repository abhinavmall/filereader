package com.assignment.egmat.service;

import java.io.File;
import java.util.List;

public interface FileReaderService<T> {
    public List<T> readAllLines(File file);
}
