package com.rohankadkol.weatherapp10.aac;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.rohankadkol.weatherapp10.models.ApiResponse;
import com.rohankadkol.weatherapp10.retrofit.ApiMethods;
import com.rohankadkol.weatherapp10.retrofit.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherViewModel extends AndroidViewModel {
    private MutableLiveData<ApiResponse> mApiResponse;

    public WeatherViewModel(@NonNull Application application) {
        super(application);
        mApiResponse = new MutableLiveData<>();
    }

    public void downloadWeather(double latitude, double longitude) {
        ApiMethods apiMethods = RetrofitClientInstance.getInstance().create(ApiMethods.class);
        apiMethods.getWeather(latitude, longitude).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    return;
                }
                ApiResponse apiResponse = response.body();
                mApiResponse.setValue(apiResponse);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<ApiResponse> getApiResponse() {
        return mApiResponse;
    }
}
