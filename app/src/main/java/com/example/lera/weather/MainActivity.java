package com.example.lera.weather;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lera.weather.db.DBHelper;
import com.example.lera.weather.db.DatabaseDao;
import com.example.lera.weather.db.WeatherModel;
import com.example.lera.weather.model.City;
import com.example.lera.weather.model.Weather;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseDao mDao;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDao = DatabaseDao.getDBInstance(this);
        mContext = this;

        List<City> list = new ArrayList<>();
        list.add(new City("Ulyanovsk", 0, new Weather("", "")));
        list.add(new City("Samara", 1, new Weather("", "")));
        list.add(new City("Kazan", 2, new Weather("", "")));
        list.add(new City("Moscow", 3, new Weather("", "")));

        for (City city : list) {
            if (!DBHelper.addWeatherToDB(mDao, city.getName(), null)) {
                Toast.makeText(this, R.string.db_error, Toast.LENGTH_SHORT).show();
            }
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(mContext, AddCityActivity.class));
        }
    });
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<WeatherModel> weatherModelList = DBHelper.getForecast(mDao);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(new CityListAdapter(getApplicationContext(), weatherModelList));

    }
}
