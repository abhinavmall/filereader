package com.assignment.egmat.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public interface FileReaderService<T> {
    List<T> readBufferedResource(BufferedReader br) throws IOException;
}
