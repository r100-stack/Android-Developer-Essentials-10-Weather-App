package com.rohankadkol.weatherapp10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rohankadkol.weatherapp10.aac.WeatherViewModel;
import com.rohankadkol.weatherapp10.models.ApiResponse;
import com.rohankadkol.weatherapp10.utils.GeocoderUtils;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    LinearLayout mInputBar;
    EditText mEtLocation;

    ConstraintLayout mWeatherCard;
    GifImageView mIvWeather;
    TextView mTvTemp;
    TextView mTvWeather;
    TextView mTvHigh;
    TextView mTvLow;
    TextView mTvUnit;

    WeatherViewModel mWeatherViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputBar = findViewById(R.id.input_bar);
        mEtLocation = mInputBar.findViewById(R.id.et_location);

        mWeatherCard = findViewById(R.id.weather_card);
        mIvWeather = mWeatherCard.findViewById(R.id.iv_weather);
        mTvTemp = mWeatherCard.findViewById(R.id.tv_temp);
        mTvWeather = mWeatherCard.findViewById(R.id.tv_weather);
        mTvHigh = mWeatherCard.findViewById(R.id.tv_high);
        mTvLow = mWeatherCard.findViewById(R.id.tv_low);
        mTvUnit = mWeatherCard.findViewById(R.id.tv_unit);

        mWeatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        mWeatherViewModel.getApiResponse().observe(this, new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse response) {
                Log.d(TAG, "onChanged: " + response.getCurrent().getTemp());
                populateUi(response);
            }
        });

        downloadWeather();
    }

    public void onButtonClick(View view) {
        downloadWeather();
    }

    private void downloadWeather() {
        double[] latLng = GeocoderUtils.getLatLng(this, mEtLocation.getText().toString());
        if (latLng != null) {
            mWeatherViewModel.downloadWeather(latLng[0], latLng[1]);
        } else {
            Toast.makeText(this, getString(R.string.download_error), Toast.LENGTH_SHORT).show();
        }
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
                mIvWeather.setImageResource(R.drawable.weather_0);
                break;
            case "01n":
                mIvWeather.setImageResource(R.drawable.weather_1);
                break;
            case "02d":
                mIvWeather.setImageResource(R.drawable.weather_2);
                break;
            case "02n":
                mIvWeather.setImageResource(R.drawable.weather_3);
                break;
            case "03d":
            case "03n":
                mIvWeather.setImageResource(R.drawable.weather_4);
                break;
            case "04d":
            case "04n":
                mIvWeather.setImageResource(R.drawable.weather_5);
                break;
            case "09d":
            case "09n":
                mIvWeather.setImageResource(R.drawable.weather_6);
                break;
            case "10d":
                mIvWeather.setImageResource(R.drawable.weather_7);
                break;
            case "10n":
                mIvWeather.setImageResource(R.drawable.weather_8);
                break;
            case "11d":
            case "11n":
                mIvWeather.setImageResource(R.drawable.weather_9);
                break;
            case "13d":
            case "13n":
                mIvWeather.setImageResource(R.drawable.weather_10);
                break;
            case "50d":
            case "50n":
                mIvWeather.setImageResource(R.drawable.weather_11);
                break;
            default:
                mIvWeather.setImageResource(android.R.color.white);
        }
    }
}
