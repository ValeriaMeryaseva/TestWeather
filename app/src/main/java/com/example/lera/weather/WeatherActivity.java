package com.example.lera.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lera.weather.cityList.CityListAdapter;
import com.example.lera.weather.net.LoadWeatherService;

public class WeatherActivity extends AppCompatActivity {

    TextView mCityName;
    TextView mTemperature;
    ListView mWeatherByDays;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        mCityName = findViewById(R.id.city_name);
        mTemperature = findViewById(R.id.temperature);
        mWeatherByDays = findViewById(R.id.weather_by_days);

        final String cityName = getIntent().getStringExtra(CityListAdapter.CITY_NAME_KEY);

        new Thread() {
            public void run() {
                try {
                    final Weather weather = LoadWeatherService.loadWeather(cityName);
                    if (weather == null) {
                        return;
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mCityName.setText(cityName);
                            mTemperature.setText(weather.temperature);
                        }
                    });
                } catch (Exception e) {
                    System.out.println();
                }
            }
        }.start();
    }
}
