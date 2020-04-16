package edu.gsu.ays.gpi.inoisbatch.services;

import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import edu.gsu.ays.gpi.inoisbatch.entity.InoisEntity;
import edu.gsu.ays.gpi.inoisbatch.entity.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;



public class CSVService {

    private static final String SAMPLE_CSV_FILE_PATH = "/Users/chad.mckee/Documents/GSU/inois/tests/utils/example_inois_data2.csv";
    private static Logger log = LoggerFactory.getLogger(CSVService.class);

    public static void readCsV() throws IOException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
        ) {
            BeanListProcessor<Test> rowProcessor = new BeanListProcessor<Test>(Test.class);

            CsvParserSettings parserSettings = new CsvParserSettings();
            parserSettings.getFormat().setLineSeparator("\n");
            parserSettings.setRowProcessor(rowProcessor);
            parserSettings.setHeaderExtractionEnabled(true);

            CsvParser parser = new CsvParser(parserSettings);
            parser.parse(reader);

// The BeanListProcessor provides a list of objects extracted from the input.
            List<Test> beans = rowProcessor.getBeans();
            log.info("processed CSV");
        }
    }

    public static void processCSV(){
        try {
            CSVService.readCsV();
        }
        catch (IOException ex){
            log.error("Failed to parse CSV");
        }
    }
}
