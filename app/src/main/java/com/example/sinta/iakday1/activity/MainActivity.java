package com.example.sinta.iakday1.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sinta.iakday1.App;
import com.example.sinta.iakday1.R;
import com.example.sinta.iakday1.WeatherController;
import com.example.sinta.iakday1.WeatherEvent;
import com.example.sinta.iakday1.adapter.WeatherAdapter;
import com.example.sinta.iakday1.model.Forecast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.main_today)
    TextView mainToday;
    @BindView(R.id.main_weather_image)
    ImageView mainWeatherImage;
    @BindView(R.id.main_weather_desc)
    TextView mainWeatherDesc;
    @BindView(R.id.main_weather_temp)
    TextView mainWeatherTemp;
    @BindView(R.id.main_weather_list)
    RecyclerView mainWeatherList;

    private WeatherAdapter weatherAdapter;

    private EventBus eventBus = App.getInstance().getEventBus();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eventBus.register(this);

        initView();

        WeatherController controller = new WeatherController();
        controller.getWeatherList();
    }

    @Override
    protected void onDestroy() {
        eventBus.unregister(this);
        super.onDestroy();
    }

    private void initView() {
        ButterKnife.bind(this);

        mainWeatherList.setLayoutManager(new LinearLayoutManager(this));
        mainWeatherList.setHasFixedSize(true);

        weatherAdapter = new WeatherAdapter();
        mainWeatherList.setAdapter(weatherAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveWeatherList(WeatherEvent event) {
        if (event.isSuccess()) {
            List<Forecast> forecasts = event.getForecastList();
            Forecast todayForecast = forecasts.get(0);

            Date date = new Date(todayForecast.getForecastDate() * 1000);
            String datePattern = "EEEE, dd MMMM yyyy";
            SimpleDateFormat outputFormat = new SimpleDateFormat(datePattern, Locale.getDefault());
            String strDate = outputFormat.format(date);

            mainToday.setText(strDate);
            Glide.with(this).load(getWeatherImageUrl(todayForecast.getWeatherList().get(0).getWeatherIcon())).into(mainWeatherImage);
            mainWeatherDesc.setText(todayForecast.getWeatherList().get(0).getWeatherDesc());
            long tempDay = Math.round(todayForecast.getTemperature().getTempDay());
            mainWeatherTemp.setText(tempDay + getString(R.string.degree));

            weatherAdapter.setData(forecasts);
        } else {

        }
    }

    private String getWeatherImageUrl(String weatherIcon) {
        return "http://openweathermap.org/img/w/" + weatherIcon + ".png";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_setting) {
            Toast.makeText(this, "Ini menu Setting", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
