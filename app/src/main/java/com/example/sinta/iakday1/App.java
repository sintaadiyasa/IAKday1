package com.example.sinta.iakday1;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sinta Adiyasa on 1/20/2018.
 * Email: sintaadiyasa@gmail.com
 */

public class App extends Application {
    private static App instance;
    private EventBus eventBus;
    private Gson gson;

    public App() {
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        createEventBus();
        createGson();
    }

    private void createGson() {
        gson = new GsonBuilder().create();
    }

    private void createEventBus() {
        eventBus = EventBus.builder()
                .logNoSubscriberMessages(false)
                .sendNoSubscriberEvent(false)
                .build();
    }

    public Gson getGson() {
        return gson;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public WeatherApi getWeatherApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WeatherApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(WeatherApi.class);
    }

    public static App getInstance() {
        return instance;
    }
}