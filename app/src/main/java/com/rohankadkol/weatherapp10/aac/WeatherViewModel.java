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
        // TODO (3): Download the weather. If there's no error, then call mApiResponse.setValue()
    }

    public MutableLiveData<ApiResponse> getApiResponse() {
        return mApiResponse;
    }
}
