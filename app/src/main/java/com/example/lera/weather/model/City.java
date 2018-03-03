package com.example.lera.weather.model;

public class City {

    private String name;
    private int code;
    private Weather weather;

    public City(String name, int code, Weather weather) {
        this.name = name;
        this.code = code;
        this.weather = weather;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
