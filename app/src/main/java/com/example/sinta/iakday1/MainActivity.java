package com.example.sinta.iakday1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvDaya;
    private ImageView ivWeather;
    private TextView tvWeather;
    private TextView tvTemperature;
    private RecyclerView mainWeatherList;
    private WeatherAdapter weatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){

        tvDaya = (TextView)findViewById(R.id.tv_day);
        ivWeather = (ImageView)findViewById(R.id.iv_weather);
        tvWeather = (TextView)findViewById(R.id.tv_weather);
        tvTemperature = (TextView)findViewById(R.id.tv_temperature);

        mainWeatherList = (RecyclerView) findViewById(R.id.rv_weather);

        mainWeatherList.setLayoutManager(new LinearLayoutManager(this));
        mainWeatherList.setHasFixedSize(true);

        tvDaya.setText("Minggu");
        ivWeather.setImageResource(R.mipmap.ic_launcher_round);
        tvWeather.setText("Cuaca Berawan");
        tvTemperature.setText("100" + getString(R.string.degree));

        weatherAdapter = new WeatherAdapter();
        mainWeatherList.setAdapter(weatherAdapter);

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
