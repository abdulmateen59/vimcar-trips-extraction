package com.vimcar;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.util.Collections;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvDataParser {

    private final static String[] memberFieldsToBindTo = {"time", "odometer"};

    public static List<DataGram> parseFile(final String fileLocation) {

        List<DataGram> dataGramList = new ArrayList<>();
        Reader reader = null;
        try {

            reader = Files.newBufferedReader(Paths.get(fileLocation));
            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
            strategy.setType(DataGram.class);
            strategy.setColumnMapping(memberFieldsToBindTo);
            CsvToBean<DataGram> csvToBean = new CsvToBeanBuilder(reader)
                    .withMappingStrategy(strategy)
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            dataGramList = csvToBean.parse();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return dataGramList;
    }
}
