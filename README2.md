### **Vimcar !**

### Bugs

- **Extracted trip end date is before start date**
    | Bug ID | 01 |
    | ------ | ------ |
    | Severity | `Significant` |
    | Assignee | Unknown |
    | Reproducible | Yes |
    | Steps to produce | Execute **testTripEndTime** using **datagram-2.csv** |
    | Actual Result | Trip{startTime='2019-07-19T16:25:39.000Z', endTime='2019-07-17T07:56:40.000Z', distance=2433} |
    | Expected Result | Trip{startTime='2019-07-19T16:25:39.000Z', endTime='2019-07-19T16:25:39.000Z', distance=****} |

- **Trip Extraction Wrong Marking**
    | Bug ID | 02 |
    | ------ | ------ |
    | Severity | `Significant` |
    | Assignee | Unknown |
    | Reproducible | Yes |
    | Steps to produce | Execute **testExtractTrips** using **extractTrips.csv** |
    | Expected | Distance 9 |
    | Actual | Distance 19 |



---
---


### Trip Extraction Automated Tests
Provided solution consist of five testcases
 -  **testExtractTrips**
 -  **testTripEndTime**
 -  **testDistanceCalculation**
 -  **testOneSecDistance**
 -  **testRedundantDataGrams**

#### 1) testExtractTrips
To verify the extracted trips a verbose stub service is implemented which extracts the trips and compare to the actual implemented service. Although, the implemented approach is prone to static testing by reason of testing the state rather than behavior.
Use extractTrips/distance csv files for detailed analysis.
Flow chart of the stub service could be found in **misc** directory.

#### 2) testTripEndTime
Test case testTripEndTime checks the start date and end date. In case the end date appears before the start date which is not possible in the real-world scenario, test case will fail.

#### 3) testDistanceCalculation
Following test case make sure all the caluated distances are positive and no ambigious distance is there in list.

#### 4) testOneSecDistance
Trips with 1-second duration should not exceed a certain threshold to avoid system abuse. The following test case makes sure there is no fake/abused trip.
Execute OneSecDistance.csv to check the problem.

#### 5) testRedundantDataGrams
This test case is to check the redundant datagrams on the list. It counts the number of datagram entries on the same DateTime and saves it in the dictionary. Currently. the threshold is set to 3 if the threshold exceeds the test will fail. 
Analyze redundant.csv for the issue.
Additionally, provided **datagrams-2.csv** also have datagrams replication.

```Change the CSV file path manualy from appTest.java```

### Docker Environment
 - Redirect to project root folder
 - Build & Run
  ```sh
 $ docker build -t vimcar . && docker run -it --rm vimcar /bin/bash
 ```
  - Clean 
```sh
$ mvn clean -f pom.xml
```
  - Execution
```sh
$ mvn verify -f pom.xml
```
 
---
 _P.S_
 _I think a small brainstorming session certainly would boost the test cases._

* [Git] - for further samples of my work


[//]: # 
[Git]: <https://bitbucket.org/abdul_mateen59/>