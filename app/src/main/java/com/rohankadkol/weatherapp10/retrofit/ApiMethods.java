package com.rohankadkol.weatherapp10.retrofit;

import com.rohankadkol.weatherapp10.models.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiMethods {
    @GET("data/2.5/onecall?exclude=minutely&units=imperial&appid=12f7848a8cdf604e4a0e4df0cfcdc8bf")
    Call<ApiResponse> getWeather(@Query("lat") double lat, @Query("lon") double lon);
}
