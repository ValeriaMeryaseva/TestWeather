package com.example.lera.weather.db;

import com.yahoo.squidb.annotations.TableModelSpec;

@TableModelSpec(className = "WeatherModel", tableName = "weather_table")
public class WeatherModelSpec {
    String city;
    String temperature;
}
