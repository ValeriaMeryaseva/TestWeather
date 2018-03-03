package com.example.lera.weather;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.lera.weather.db.WeatherModel;

import java.util.List;

public class CityListAdapter extends BaseAdapter {

    public final static String CITY_NAME_KEY = "city_name_key";

    private List<WeatherModel> weatherModelList;
    private Context mContext;

    public CityListAdapter(Context context, List<WeatherModel> weatherModelList) {
        this.weatherModelList = weatherModelList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return weatherModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return weatherModelList.get(position);
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

        WeatherModel weatherModel = weatherModelList.get(position);

        ((TextView) view.findViewById(R.id.city_name)).setText(weatherModel.getCity());
        ((TextView) view.findViewById(R.id.temperature)).setText(weatherModel.getTemperature());
        view.setOnClickListener(onClickListener);
        view.setTag(position);

        return view;
    }

    Button.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            WeatherModel weatherModel = weatherModelList.get((Integer) v.getTag());

            Intent intent = new Intent(mContext, WeatherActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(CITY_NAME_KEY, weatherModel.getCity());

            mContext.startActivity(intent);
        }
    };
}
