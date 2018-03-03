package com.example.lera.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.lera.weather.db.DBHelper;
import com.example.lera.weather.db.DatabaseDao;

public class AddCityActivity extends AppCompatActivity {

    private DatabaseDao mDao;
    private EditText mAddCityEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);
        mDao = DatabaseDao.getDBInstance(this);
        mAddCityEditText = findViewById(R.id.addCityEditText);
    }

    public void onAddCityButtonClick(View view) {
        if (mAddCityEditText.getText().toString().isEmpty()) {
            return;
        }
        DBHelper.addWeatherToDB(mDao, mAddCityEditText.getText().toString(), null);
        finish();
    }

}
