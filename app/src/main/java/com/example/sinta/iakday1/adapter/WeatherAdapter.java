package com.example.sinta.iakday1.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sinta.iakday1.R;
import com.example.sinta.iakday1.activity.DetailWeatherActivity;
import com.example.sinta.iakday1.model.Forecast;
import com.example.sinta.iakday1.viewholder.WeatherViewHolder;

import java.util.ArrayList;
import java.util.List;

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
        holder.getWeatherDate().setText(DateUtils.getRelativeTimeSpanString(holder.itemView.getContext(), forecast.getForecastDate() * 1000));
        holder.getWeatherDesc().setText(forecast.getWeatherList().get(0).getWeatherDesc());
        holder.getWeatherTemp().setText(forecast.getTemperature().getTempDay() + holder.itemView.getContext().getString(R.string.degree));
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
        holder.itemView.getContext().startActivity(intent);
    }
}