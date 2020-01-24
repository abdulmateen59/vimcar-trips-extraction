package com.vimcar;

import org.joda.time.DateTime;
import org.joda.time.Minutes;

import java.util.ArrayList;
import java.util.List;

public class TripExtractionServiceImpl implements TripExtractionService {

    @Override
    public List<Trip> extractTrips(List<DataGram> dataGramList) {

        List<Trip> trips = new ArrayList<>();

        int previousOdometer = 0;
        DateTime previousDateTime = null;
        String tripStartTime = null;
        int tripDistance = 0;
        for (DataGram dataGram : dataGramList) {

            if (tripStartTime == null) {
                tripStartTime = dataGram.getTime();
            }

            int currentOdometer = Integer.parseInt(dataGram.getOdometer());
            if (previousOdometer == 0) {
                previousOdometer = currentOdometer;
            }

            DateTime dateTime = DateTime.parse(dataGram.getTime());
            if (previousDateTime == null) {
                previousDateTime = dateTime;
            }

            int distanceDiff = currentOdometer - previousOdometer;
            int timeDiffMinutes = Minutes.minutesBetween(previousDateTime, dateTime).getMinutes();

            if (timeDiffMinutes < 3) {
                if (distanceDiff > 0) {
                    tripDistance += distanceDiff;
                }
            } else if (tripDistance > 0) {
                Trip trip = new Trip();
                trip.setDistance(tripDistance);
                trip.setStartTime(tripStartTime);
                trip.setEndTime(previousDateTime.toString());
                trips.add(trip);
                tripDistance = 0;
                tripStartTime = dataGram.getTime();
            }
            previousDateTime = dateTime;
            previousOdometer = currentOdometer;
        }

        return trips;
    }
}
