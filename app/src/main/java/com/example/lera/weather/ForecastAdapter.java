package com.example.lera.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lera.weather.model.DayWeatherForecast;

import java.util.List;

public class ForecastAdapter extends BaseAdapter {

    public void setmForecasts(List<DayWeatherForecast> mForecasts) {
        this.mForecasts = mForecasts;
    }

    private List<DayWeatherForecast> mForecasts;
    private Context mContext;

    public ForecastAdapter(List<DayWeatherForecast> mForecasts, Context mContext) {
        this.mForecasts = mForecasts;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mForecasts.size();
    }

    @Override
    public Object getItem(int position) {
        return mForecasts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.day_forecast_item, parent, false);
        }
        ((TextView) view.findViewById(R.id.date)).setText(mForecasts.get(position).getTime());
        ((TextView) view.findViewById(R.id.temperature)).setText(mForecasts.get(position).getTemperature());
        return view;
    }
}
