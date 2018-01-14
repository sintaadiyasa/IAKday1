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

import com.example.sinta.iakday1.R;
import com.example.sinta.iakday1.adapter.WeatherAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tv_day)
    TextView tvDaya;
    @BindView(R.id.iv_weather)
    ImageView ivWeather;
    @BindView(R.id.tv_weather)
    TextView tvWeather;
    @BindView(R.id.tv_temperature)
    TextView tvTemperature;
    @BindView(R.id.rv_weather)
    RecyclerView mainWeatherList;

    WeatherAdapter weatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){

        ButterKnife.bind(this);
/*tvDaya = (TextView)findViewById(R.id.tv_day);
        ivWeather = (ImageView)findViewById(R.id.iv_weather);
        tvWeather = (TextView)findViewById(R.id.tv_weather);
        tvTemperature = (TextView)findViewById(R.id.tv_temperature);
        mainWeatherList = (RecyclerView) findViewById(R.id.rv_weather);*/


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
