package com.assignment.service;

import java.io.File;
import java.util.List;

public interface FileReaderService<T> {
    public List<T> readAllLines(File file);
}
