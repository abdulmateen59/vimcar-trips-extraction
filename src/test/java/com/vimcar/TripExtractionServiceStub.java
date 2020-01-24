package com.vimcar;

import org.joda.time.DateTime;
import org.joda.time.Minutes;

import java.util.ArrayList;
import java.util.List;

public class TripExtractionServiceStub implements TripExtractionService {

    @Override
    public List<Trip> extractTrips(List<DataGram> dataGramList) {

        List<Trip> trips = new ArrayList<>();

        int previousOdometer = 0;
        DateTime previousDateTime = null;
        String tripStartTime = null;
        int tripDistance = 0;
        int distanceDiff = 0;
        int timeDiffMinutes = 0;
        DateTime dateTime = null;

        for (DataGram dataGram : dataGramList) {

            int currentOdometer = Integer.parseInt(dataGram.getOdometer());
            dateTime = DateTime.parse(dataGram.getTime());
            
            if(currentOdometer < 0) {
                currentOdometer = previousOdometer;
            }

            if (tripStartTime == null && previousOdometer == 0 && previousDateTime == null) {
                tripStartTime = dataGram.getTime();
            } else {
                if (currentOdometer >= previousOdometer) {
                    distanceDiff = currentOdometer - previousOdometer;
                }
                if (Minutes.minutesBetween(previousDateTime, dateTime).getMinutes() >= 0) {
                    timeDiffMinutes = Minutes.minutesBetween(previousDateTime, dateTime).getMinutes();
                }
                if (timeDiffMinutes < 3 && timeDiffMinutes >= 0 && distanceDiff >= 0
                        && currentOdometer >= previousOdometer) {
                    tripDistance += distanceDiff;

                } else if (tripDistance > 0 && currentOdometer > previousOdometer
                        && (Minutes.minutesBetween(previousDateTime, dateTime).getMinutes()) >= 0) {
                    Trip trip = new Trip();
                    trip.setDistance(tripDistance);
                    trip.setStartTime(tripStartTime);
                    trip.setEndTime(previousDateTime.toString());
                    trips.add(trip);
                    tripDistance = 0;
                    tripStartTime = dataGram.getTime();
                }
            }
            previousDateTime = dateTime;
            previousOdometer = currentOdometer;
        }
        return trips;
    }
}