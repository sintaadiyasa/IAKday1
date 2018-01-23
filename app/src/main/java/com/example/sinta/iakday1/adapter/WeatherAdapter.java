package com.example.sinta.iakday1.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sinta.iakday1.App;
import com.example.sinta.iakday1.R;
import com.example.sinta.iakday1.activity.DetailWeatherActivity;
import com.example.sinta.iakday1.model.Forecast;
import com.example.sinta.iakday1.viewholder.WeatherViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by SINTA on 1/14/2018.
 */

public class WeatherAdapter extends RecyclerView.Adapter implements WeatherViewHolder.WeatherCallback {
    List<Forecast> forecasts;

    public WeatherAdapter() {
        forecasts = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(WeatherViewHolder.getWeatherLayout(), parent, false);
        return new WeatherViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        setWeatherItem((WeatherViewHolder) holder);

    }

    private void setWeatherItem(WeatherViewHolder holder) {
        Forecast forecast = forecasts.get(holder.getAdapterPosition());
        Glide.with(holder.itemView.getContext()).load(getWeatherImageUrl(forecast.getWeatherList().get(0).getWeatherIcon())).into(holder.getWeatherImage());
        Date date = new Date(forecast.getForecastDate() * 1000);
        String datePattern = "EEE, MMM dd";
        SimpleDateFormat outputFormat = new SimpleDateFormat(datePattern, Locale.getDefault());
        String strDate = outputFormat.format(date);
        holder.getWeatherDate().setText(strDate);
        holder.getWeatherDesc().setText(forecast.getWeatherList().get(0).getWeatherDesc());
        long tempDay = Math.round(forecast.getTemperature().getTempDay());
        holder.getWeatherTemp().setText(tempDay + holder.itemView.getContext().getString(R.string.degree));

    }

    private String getWeatherImageUrl(String weatherIcon) {
        return "http://openweathermap.org/img/w/" + weatherIcon + ".png";
    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }

    public void setData(List<Forecast> forecasts) {
        this.forecasts.addAll(forecasts);
        notifyDataSetChanged();
    }

    @Override
    public void onWeatherClick(WeatherViewHolder holder) {
        Intent intent = new Intent(holder.itemView.getContext(), DetailWeatherActivity.class);
        String forecastJson = App.getInstance().getGson().toJson(forecasts.get(holder.getAdapterPosition()));
        intent.putExtra("forecast", forecastJson);
        holder.itemView.getContext().startActivity(intent);
    }
}