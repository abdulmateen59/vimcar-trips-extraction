package com.vimcar;

public class DataGram {

    private String time;
    private String odometer;

    public DataGram() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOdometer() {
        return odometer;
    }

    public void setOdometer(String odometer) {
        this.odometer = odometer;
    }

    @Override
    public String toString() {
        return "DataGram{" +
                "time='" + time + '\'' +
                ", odometer='" + odometer + '\'' +
                '}';
    }
}
