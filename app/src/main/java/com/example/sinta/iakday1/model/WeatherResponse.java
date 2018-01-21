package com.example.sinta.iakday1.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sinta Adiyasa on 1/20/2018.
 * Email: sintaadiyasa@gmail.com
 */

public class WeatherResponse {
    @SerializedName("list")
    private List<Forecast> forecastList;

    public List<Forecast> getForecastList() {
        return forecastList;
    }
}
