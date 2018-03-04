package com.example.lera.weather.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.lera.weather.model.DayWeatherForecast;
import com.example.lera.weather.model.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LoadWeatherService {

    private static final String START_URL = "http://api.openweathermap.org/data/2.5/";
    private static final String WEATHER = "weather?q=";
    private static final String FORECAST = "forecast?q=";
    private static final String COUNT = "&cnt=9";
    private static final String METRIC_PARAMS_URL = "&units=metric&APPID=";
    private static final String API_KEY = "a9ef9c7e94b04ebd4689a84570e67559";

    public static Weather loadWeather(String cityName) throws IOException, JSONException {
        Weather weather = new Weather("", "");

        try {
            URL url = new URL(START_URL + WEATHER + cityName + METRIC_PARAMS_URL + API_KEY);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer buffer = new StringBuffer();
            String tmp;
            while ((tmp = reader.readLine()) != null) {
                buffer.append(tmp).append("\n");
            }
            reader.close();
            JSONObject jsonObject = new JSONObject(buffer.toString());
            JSONObject observation = jsonObject.getJSONObject("main");
            weather.setDetails(jsonObject.getJSONArray("weather").getJSONObject(0).get("main") +
                    "\n" + jsonObject.getJSONArray("weather").getJSONObject(0).get("description") +
                    "\n" + "Humidity: " + observation.getString("humidity") +
                    "\n" + "Pressure: " + observation.getString("pressure") + " hPa");
            weather.setTemperature(String.valueOf((int) (Double.valueOf(observation.getString("temp"))).doubleValue()) + " ℃");

        } catch (Exception e) {
            throw new IOException();
        }
        return weather;
    }

    public static List<DayWeatherForecast> loadWeatherForecast(String cityName) {
        List<DayWeatherForecast> dayForecast = new ArrayList<>();
        try {
            URL url = new URL(START_URL + FORECAST + cityName + COUNT + METRIC_PARAMS_URL + API_KEY);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader((new InputStreamReader(connection.getInputStream())));
            StringBuffer buffer = new StringBuffer();
            String tmp;
            while ((tmp = reader.readLine()) != null) {
                buffer.append(tmp).append("\n");
            }
            reader.close();
            JSONObject jsonObject = new JSONObject(buffer.toString());

            JSONArray jsonArray = jsonObject.getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                String temp = String.valueOf((int) Double.valueOf(((JSONObject) ((JSONObject) jsonArray.get(i)).get("main")).get("temp").toString()).doubleValue());
                String time = (((JSONObject) jsonArray.get(i)).get("dt_txt")).toString();
                dayForecast.add(new DayWeatherForecast(time, temp));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dayForecast;
    }

    public static boolean isConnectedToInternet(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}
