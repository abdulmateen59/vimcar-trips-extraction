package com.vimcar;

import java.util.List;

public class App {

    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("No file provided");
            System.exit(0);
        }
        System.out.println("Processing file " + args[0]);

        List<DataGram> dataGramList = CsvDataParser.parseFile(args[0]);
        dataGramList.forEach(System.out::println);
        TripExtractionService tripExtractionService = new TripExtractionServiceImpl();
        List<Trip> trips = tripExtractionService.extractTrips(dataGramList);
        trips.forEach(System.out::println);
    }
}
