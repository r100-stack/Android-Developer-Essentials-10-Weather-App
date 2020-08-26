package com.rohankadkol.weatherapp10.models;

public class ApiResponse {
    private CurrentAndHourly current;
    private CurrentAndHourly[] hourly;
    private Daily[] daily;

    public CurrentAndHourly getCurrent() {
        return current;
    }

    public CurrentAndHourly[] getHourly() {
        return hourly;
    }

    public Daily[] getDaily() {
        return daily;
    }
}
