package com.rohankadkol.weatherapp10.models;

import com.google.gson.annotations.SerializedName;

public class Daily {
    private long dt;
    private Temp temp;
    private Weather[] weather;

    public long getDt() {
        return dt;
    }

    public Temp getTemp() {
        return temp;
    }

    public Weather[] getWeather() {
        return weather;
    }
}
