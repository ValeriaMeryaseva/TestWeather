package com.example.lera.weather.model;

public class Weather {
    String date;
    String temperature;
    String details;

    public Weather(String temperature, String details) {
        this.temperature = temperature;
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
