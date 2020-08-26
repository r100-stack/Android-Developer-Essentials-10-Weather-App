package com.rohankadkol.weatherapp10.models;

public class CurrentAndHourly {
    private long dt;
    private double temp;
    private Weather[] weather;

    public long getDt() {
        return dt;
    }

    public double getTemp() {
        return temp;
    }

    public Weather[] getWeather() {
        return weather;
    }
}
