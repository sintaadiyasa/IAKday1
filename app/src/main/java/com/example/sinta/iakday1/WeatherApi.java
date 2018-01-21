package com.example.sinta.iakday1;

import com.example.sinta.iakday1.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Sinta Adiyasa on 1/20/2018.
 * Email: sintaadiyasa@gmail.com
 */

public interface WeatherApi {
    String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    String API_KEY = App.getInstance().getApplicationContext().getString(R.string.api_key);

    @GET("forecast/daily")
    Call<WeatherResponse> getDailyForecast(@Query("q") String cityName, @Query("mode") String mode, @Query("units") String unit, @Query("cnt") int countData, @Query("appid") String apiKey);
}
