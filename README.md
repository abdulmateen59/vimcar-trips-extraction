### Trip Extraction
This is a basic implementation of the Trip Extraction we apply on data sets we receive from cars/dongles.
Please find enclosed 2 CSV files, which represent data received from a device tracking the trajectories of a vehicle while driving.
#### DataGram Entity
This is a single entity of information that's sent from the car at any point of time during the trip. 
And it contains two properties:
- **Time**: The time in seconds at which this piece of information was sent.
- **Odometer**: The reading of odometer. It shows the mileage in meters this vehicle has gone through, the same way as one would see in the dashboard of a car.

#### Trip Extraction Code

- **CsvDataParser**: A simple helper to parse a CSV file with a header of {time, odometer} and map the information into a list of DataGram entities.
- **DataGram**: Explained above.
- **Trip**: An entity with start and end times, and a distance value. The processing of many records of DataGram can result in one or many Trips.
- **TripExtractionServiceImpl**: Implementation of TripExtractionService in which you expect Trips to be extracted out of the provided list of DataGrams depending on multiple factors.

#### Trip Extraction algorithm
- First, it walks through DataGrams and accumulates distance based on the value of odometer.
- If a time difference between the current and previous DataGram is equal to or greater than 3 seconds, we assume that the car has stopped.
- Once we reach a stopping point, we finish a trip by forming a Trip entity with the start time, end time, and the accumulated distance. Then we set new start time, reset the distance, and continue in the loop to form the next trip.

#### How to use the Trip Extraction tool
 - Environment: You need to have Java8 and Maven installed.
 - Build the jar file `mvn clean package`
 - Execution: `java -jar target/VimcarTripExtraction.jar {csv_file_absolute_path}`
 
### Your task
- Write an integration test in Java to test **TripExtractionServiceImpl**.
- Try to think of different scenarios and cases in which the trip extraction might go wrong.
- Try to modify the data and introduce some cases that might exploit bugs in the extraction algorithm.
- You can also write your thoughts on the way the tool works and how it can be tested under different circumstances.
- In case you find bugs, please write a simple and short bug report, and try to asses the severity level of the bug.
- Once you have a working buildable solution, please send us a zipped source code with a second MD file in which you explain your approach.

At Vimcar, we always work in a team, so please don’t hesitate to reach out to us if you have any questions or need any clarification.