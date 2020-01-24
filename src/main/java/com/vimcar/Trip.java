package com.vimcar;

public class Trip {

    private String startTime;
    private String endTime;
    private int distance;

    public Trip() {
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", distance=" + distance +
                '}';
    }
}
