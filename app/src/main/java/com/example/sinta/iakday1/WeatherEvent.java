package com.example.sinta.iakday1;

import com.example.sinta.iakday1.model.Forecast;

import java.util.List;

/**
 * Created by Sinta Adiyasa on 1/20/2018.
 * Email: sintaadiyasa@gmail.com
 */

public class WeatherEvent {
    private boolean success;
    private String message;
    private List<Forecast> forecastList;

    public WeatherEvent(boolean success, List<Forecast> forecastList) {
        this.success = success;
        this.forecastList = forecastList;
    }

    public WeatherEvent(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Forecast> getForecastList() {
        return forecastList;
    }
}