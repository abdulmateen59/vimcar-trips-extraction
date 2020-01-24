package com.vimcar;

import java.util.List;

public interface TripExtractionService {

    List<Trip> extractTrips(List<DataGram> dataGramList);
}
