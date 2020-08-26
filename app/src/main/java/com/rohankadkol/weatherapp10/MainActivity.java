package com.rohankadkol.weatherapp10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.rohankadkol.weatherapp10.aac.WeatherViewModel;
import com.rohankadkol.weatherapp10.models.ApiResponse;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    ConstraintLayout mWeatherCard;
    GifImageView mIvWeather;
    TextView mTvTemp;
    TextView mTvWeather;
    TextView mTvHigh;
    TextView mTvLow;
    TextView mTvUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWeatherCard = findViewById(R.id.weather_card);
        mIvWeather = mWeatherCard.findViewById(R.id.iv_weather);
        mTvTemp = mWeatherCard.findViewById(R.id.tv_temp);
        mTvWeather = mWeatherCard.findViewById(R.id.tv_weather);
        mTvHigh = mWeatherCard.findViewById(R.id.tv_high);
        mTvLow = mWeatherCard.findViewById(R.id.tv_low);
        mTvUnit = mWeatherCard.findViewById(R.id.tv_unit);

        WeatherViewModel weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
//        WeatherViewModel weatherViewModel1 = new ViewModelProvider.NewInstanceFactory().create(WeatherViewModel.class);

        weatherViewModel.downloadWeather();

        weatherViewModel.getApiResponse().observe(this, new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse response) {
                Log.d(TAG, "onChanged: " + response.getCurrent().getTemp());
                populateUi(response);
            }
        });
    }

    private void populateUi(ApiResponse response) {
        mTvTemp.setText(String.format("%.0f°", response.getCurrent().getTemp()));
        mTvUnit.setText("F");
        mTvWeather.setText(response.getCurrent().getWeather()[0].getMain());
        mTvHigh.setText(String.format("%.0f°", response.getDaily()[0].getTemp().getMax()));
        mTvLow.setText(String.format("%.0f°", response.getDaily()[0].getTemp().getMin()));
        setWeatherIcon(response.getCurrent().getWeather()[0].getIcon());
    }

    private void setWeatherIcon(String code) {
        switch (code) {
            case "01d":
                mIvWeather.setBackgroundResource(R.drawable.weather_0);
                break;
            case "01n":
                mIvWeather.setBackgroundResource(R.drawable.weather_1);
                break;
            case "02d":
                mIvWeather.setBackgroundResource(R.drawable.weather_2);
                break;
            case "02n":
                mIvWeather.setBackgroundResource(R.drawable.weather_3);
                break;
            case "03d":
            case "03n":
                mIvWeather.setBackgroundResource(R.drawable.weather_4);
                break;
            case "04d":
            case "04n":
                mIvWeather.setBackgroundResource(R.drawable.weather_5);
                break;
            case "09d":
            case "09n":
                mIvWeather.setBackgroundResource(R.drawable.weather_6);
                break;
            case "10d":
                mIvWeather.setBackgroundResource(R.drawable.weather_7);
                break;
            case "10n":
                mIvWeather.setBackgroundResource(R.drawable.weather_8);
                break;
            case "11d":
            case "11n":
                mIvWeather.setBackgroundResource(R.drawable.weather_9);
                break;
            case "13d":
            case "13n":
                mIvWeather.setBackgroundResource(R.drawable.weather_10);
                break;
            case "50d":
            case "50n":
                mIvWeather.setBackgroundResource(R.drawable.weather_11);
                break;
            default:
                mIvWeather.setBackgroundResource(android.R.color.white);
        }
    }
}
