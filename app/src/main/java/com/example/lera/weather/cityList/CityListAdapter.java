package com.example.lera.weather.cityList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lera.weather.City;
import com.example.lera.weather.R;

import java.util.List;

public class CityListAdapter extends BaseAdapter {

    private List<City> mCities;
    private Context mContext;

    public CityListAdapter(Context context, List<City> cities) {
        this.mCities = cities;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mCities.size();
    }

    @Override
    public Object getItem(int position) {
        return mCities.get(position);
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
            view = inflater.inflate(R.layout.city_list_item, parent, false);
        }

        City city = getCity(position);

        ((TextView) view.findViewById(R.id.city_name)).setText(city.getName());
//        ((TextView) view.findViewById(R.id.temperature)).setText(city.getWeather().temperature);

        return view;
    }

    City getCity(int position) {
        return ((City) getItem(position));
    }

}
