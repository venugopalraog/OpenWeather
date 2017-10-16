package com.sample.openweather.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sample.openweather.R;
import com.sample.openweather.views.fragments.WeatherFragment;

import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    public void updateCityName(String cityName) {
        WeatherFragment weatherFragment = (WeatherFragment) getSupportFragmentManager().findFragmentById(R.id.weather_fragment);
        weatherFragment.updateWeatherCity(cityName);
    }
}
