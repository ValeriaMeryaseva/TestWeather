package com.example.lera.weather.model;

public class DayWeatherForecast {

    private String time;
    private String temperature;

    public DayWeatherForecast(String time, String temperature) {
        this.time = time;
        this.temperature = temperature;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemperature() {
        return temperature + " ℃";
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
