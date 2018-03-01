package com.example.lera.weather.net;

import com.example.lera.weather.Weather;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoadWeatherService {

    private static final String START_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String METRIC_PARAMS_URL = "&units=metric&APPID=";
    private static final String API_KEY = "a9ef9c7e94b04ebd4689a84570e67559";

    public static Weather loadWeather(String cityName) throws IOException, JSONException {
        Weather weather = new Weather("", "");

        try {
            URL url = new URL(START_URL + cityName + METRIC_PARAMS_URL + API_KEY);
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
            weather.details = jsonObject.getJSONArray("weather").getJSONObject(0).get("main") +
                    "\n" + jsonObject.getJSONArray("weather").getJSONObject(0).get("description") +
                    "\n" + "Humidity: " + observation.getString("humidity") +
                    "\n" + "Pressure: " + observation.getString("pressure") + " hPa";
            weather.temperature = observation.getString("temp") + " ℃";

        } catch (Exception e) {
            throw new IOException();
        }
        return weather;
    }

}
