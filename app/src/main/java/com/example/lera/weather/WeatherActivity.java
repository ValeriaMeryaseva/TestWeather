package com.example.lera.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lera.weather.db.DBHelper;
import com.example.lera.weather.db.DatabaseDao;
import com.example.lera.weather.model.DayWeatherForecast;
import com.example.lera.weather.model.Weather;
import com.example.lera.weather.net.LoadWeatherService;

import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends AppCompatActivity {

    DatabaseDao mDao;
    TextView mCityName;
    TextView mTemperature;
    TextView mTitle;
    ListView mForecasts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        mDao = DatabaseDao.getDBInstance(getApplicationContext());

        mCityName = findViewById(R.id.city_name);
        mTemperature = findViewById(R.id.temperature);
        mTitle = findViewById(R.id.forecast_title);
        mForecasts = findViewById(R.id.forecast);

        final String cityName = getIntent().getStringExtra(CityListAdapter.CITY_NAME_KEY);
        final ForecastAdapter forecastAdapter = new ForecastAdapter(new ArrayList<DayWeatherForecast>(), this);
        mForecasts.setAdapter(forecastAdapter);

        mCityName.setText(cityName);

        new Thread() {
            public void run() {
                try {
                    final Weather weather = LoadWeatherService.loadWeather(cityName);
                    if (weather == null) {
                        return;
                    }
                    final List<DayWeatherForecast> forecasts = LoadWeatherService.loadWeatherForecast(cityName);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTemperature.setText(weather.getTemperature());
                            mTitle.setText(R.string.forecast);
                            forecastAdapter.setmForecasts(forecasts);
                            forecastAdapter.notifyDataSetChanged();

                            DBHelper.addWeatherToDB(mDao, cityName, weather.getTemperature());
                        }
                    });
                } catch (Exception e) {
                }
            }
        }.start();
    }

    public void onRemoveCityClick(View view) {
        DBHelper.removeCityFromDB(mDao, mCityName.getText().toString());
        finish();
    }
}
