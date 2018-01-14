package com.example.sinta.iakday1;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by SINTA on 1/14/2018.
 */

public class WeatherViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private final ImageView weatherImage;
    private final TextView weatherDate;
    private final TextView weatherDesc;
    private final TextView weatherTemp;
    private final WeatherCallback callback;
    private static final int weatherLayout = R.layout.view_holder_weather;

    public WeatherViewHolder(View itemView, WeatherCallback callback) {
        super(itemView);
        weatherImage = (ImageView) itemView.findViewById(R.id.weather_item_image);
        weatherDate = (TextView) itemView.findViewById(R.id.weather_item_date);
        weatherDesc = (TextView) itemView.findViewById(R.id.weather_item_desc);
        weatherTemp = (TextView) itemView.findViewById(R.id.weather_item_temp);
        this.callback = callback;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        callback.onWeatherClick(this);
    }

    public interface WeatherCallback {
        void onWeatherClick(WeatherViewHolder holder);
    }

    public ImageView getWeatherImage() {
        return weatherImage;
    }

    public TextView getWeatherDate() {
        return weatherDate;
    }

    public TextView getWeatherDesc() {
        return weatherDesc;
    }

    public TextView getWeatherTemp() {
        return weatherTemp;
    }

    public static int getWeatherLayout() {
        return weatherLayout;
    }
}
