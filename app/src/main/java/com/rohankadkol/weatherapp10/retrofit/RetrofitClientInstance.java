package com.rohankadkol.weatherapp10.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static Retrofit sRetrofit;
    private static String OPEN_WEATHER_BASE_URI = "https://api.openweathermap.org/";

    public static Retrofit getInstance() {
        // TODO (1): Initialize sRetrofit if null.
        return sRetrofit;
    }
}
