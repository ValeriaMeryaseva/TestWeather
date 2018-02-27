package com.example.lera.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WeatherActivity extends AppCompatActivity {

    TextView mCityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        mCityName = findViewById(R.id.city_name);

        String cityName = getIntent().getStringExtra("cityName");

        mCityName.setText(cityName);
    }
}
