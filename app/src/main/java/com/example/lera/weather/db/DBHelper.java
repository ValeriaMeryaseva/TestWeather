package com.example.lera.weather.db;

import com.yahoo.squidb.data.SquidCursor;
import com.yahoo.squidb.sql.Query;

import java.util.ArrayList;
import java.util.List;

public class DBHelper {

    public static boolean addWeatherToDB(DatabaseDao db, String city, String temp) {
        WeatherModel weatherModel = new WeatherModel();
        weatherModel.setCity(city);
        if (temp != null) {
            weatherModel.setTemperature(temp);
        }
        int count = db.query(WeatherModel.class,
                Query.select().from(WeatherModel.TABLE).
                        where(WeatherModel.CITY.eq(city))).getCount();
        if (count != 0) {
            return db.update(WeatherModel.CITY.eq(city), weatherModel) == 1;
        }
        return db.createNew(weatherModel);
    }

    public static void removeCityFromDB(DatabaseDao dao, String cityName) {
        dao.deleteWhere(WeatherModel.class, WeatherModel.CITY.eq(cityName));
    }

    public static List<WeatherModel> getForecast(DatabaseDao db) {
        List<WeatherModel> weatherModels = new ArrayList<>();
        Query query = Query.select().from(WeatherModel.TABLE);
        SquidCursor<WeatherModel> cursor = db.query(WeatherModel.class, query);
        try {
            WeatherModel model = new WeatherModel();
            while (cursor.moveToNext()) {
                model.readPropertiesFromCursor(cursor);
                WeatherModel weatherModel = new WeatherModel();
                weatherModel.setId(model.getId());
                weatherModel.setCity(model.getCity());
                weatherModel.setTemperature(model.getTemperature());
                weatherModels.add(weatherModel);
            }
        } finally {
            cursor.close();
        }
        return weatherModels;
    }
}
