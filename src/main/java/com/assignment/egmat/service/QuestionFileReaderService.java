package com.assignment.egmat.service;

import com.assignment.egmat.dto.Question;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class QuestionFileReaderService implements FileReaderService<Question> {

    @Override
    public List<Question> readBufferedResource(BufferedReader br) throws IOException {
        // Make processor
        final BeanListProcessor<Question> questionBeanListProcessor = new
                BeanListProcessor<>(Question.class);

        // Configure
        CsvParserSettings csvParserSettings = new CsvParserSettings();
        csvParserSettings.getFormat().setDelimiter('|');
        csvParserSettings.getFormat().setQuoteEscape('\\');
        csvParserSettings.getFormat().setCharToEscapeQuoteEscaping('\\');
        csvParserSettings.getFormat().setLineSeparator("\n");
        csvParserSettings.setKeepEscapeSequences(true);
        csvParserSettings.setProcessor(questionBeanListProcessor);
        CsvParser recordParser = new CsvParser(csvParserSettings);

        // Parse
        List<Question> result = null;
        String next, line = br.readLine();
        for (boolean first = true, last = (line == null); !last; first = false, line = next) {
            last = ((next = br.readLine()) == null);
            recordParser.parseLine(line);
        }

        // Close BR
        IOUtils.closeQuietly(br);

        // Return result
        result = questionBeanListProcessor.getBeans();

        return result;
    }
}
