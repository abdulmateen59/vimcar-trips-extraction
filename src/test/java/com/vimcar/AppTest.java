package com.vimcar;

import java.util.*;
import org.joda.time.DateTime;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runners.Parameterized.Parameter;


public class AppTest {

    @Parameter
    List<Trip> Actual;
    @Parameter
    List<Trip> Expected;
    @Parameter
    List<DataGram> dataGramList;

    @Before
    public void appTest() {
        dataGramList = CsvDataParser.parseFile("datagrams-2.csv");
        TripExtractionService TripExtractionServiceStub = new TripExtractionServiceStub();
        TripExtractionServiceImpl TripExtractionServiceImpl = new TripExtractionServiceImpl();
        Actual = TripExtractionServiceImpl.extractTrips(dataGramList);
        Expected = TripExtractionServiceStub.extractTrips(dataGramList);
    }

    @Test
    public void testExtractTrips() {
        for (int i = 0; i < Actual.size(); i++) {
            try {
                Assert.assertEquals(Expected.get(i).getStartTime(), Actual.get(i).getStartTime());
                Assert.assertEquals(Expected.get(i).getEndTime(), Actual.get(i).getEndTime());
                Assert.assertTrue(Expected.get(i).getDistance() == Actual.get(i).getDistance());
                System.out.println("Expected = " + Expected.get(i) + " \nActual = " + Actual.get(i)
                        + "\n------------------------- PASSED-------------------------- ");
            } catch (AssertionError e) {
                System.out.println("Expected = " + Expected.get(i) + " \nActual = " + Actual.get(i)
                        + "\n-------------------------- FAILED-------------------------- ");
                fail("Err...");
            }
        }
    }

    @Test
    public void testTripEndTime() {
        for (Trip element : Actual) {
            DateTime endDate = DateTime.parse(element.getEndTime());
            DateTime startDate = DateTime.parse(element.getStartTime());
            try {
                Assert.assertFalse(endDate.isBefore(startDate));
            } catch (final AssertionError e) {
                System.out.println(element);
                fail("Trip endTime is before startTime...");
            }
        }
    }

    @Test
    public void testDistanceCalculation() {
        for (Trip element : Actual) {
            try {
                System.out.println(element);
                Assert.assertTrue(element.getDistance() > 0);
            } catch (AssertionError e) {
                System.out.println(element);
                fail("Calculated distance should be greater then 0...");
            }
        }
    }

    @Test
    public void testOneSecDistance()  {
        for (Trip element : Actual) {
            try {
                DateTime endDate = DateTime.parse(element.getEndTime());
                DateTime startDate = DateTime.parse(element.getStartTime());
                if (startDate.isEqual(endDate)) {
                    Assert.assertFalse(element.getDistance() > 10);
                }
            } catch (AssertionError e) {
                System.out.println(element);
                fail("Distance threshold exceeded...");
                throw e;
            }
            
        }
    }

    @Test
    public void testRedundantDataGrams() {
        Map<DateTime, Integer> dictionary = new HashMap<DateTime, Integer>();
        DateTime outerDateTime, innerDateTime;
        int count = 0;
        for (int i = 0; i < dataGramList.size(); i++) {
            count = 1;
            outerDateTime = DateTime.parse(dataGramList.get(i).getTime());
            for (int j = i + 1; j < dataGramList.size(); j++) {
                innerDateTime = DateTime.parse(dataGramList.get(j).getTime());
                if (outerDateTime.isEqual(innerDateTime)) {
                    count ++;
                }
            }
            if (!dictionary.containsKey(outerDateTime)) {
                dictionary.put(outerDateTime, count);
            }
        }
        System.out.println(dictionary);
        for (Map.Entry<DateTime, Integer> entry : dictionary.entrySet()) {
            if(entry.getValue() > 3) {
                fail("Redundant datagrams threshold exceeded...");
            }
        }
    }

}