package com.sample.openweather.views.fragments;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sample.openweather.BuildConfig;
import com.sample.openweather.R;
import com.sample.openweather.di.MyApplication;
import com.sample.openweather.models.WeatherForecastResponse;
import com.sample.openweather.models.WeatherResponse;
import com.sample.openweather.models.events.NetworkResponseEvent;
import com.sample.openweather.models.events.NetworkResponseFailedEvent;
import com.sample.openweather.models.weather.Weather;
import com.sample.openweather.presenter.MainScreenPresenter;
import com.sample.openweather.utils.CommonUtils;
import com.sample.openweather.utils.DateTimeUtils;
import com.sample.openweather.views.adapters.WeatherForecastAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by venugopalraog on 10/12/17.
 */

public class WeatherFragment extends Fragment implements View.OnClickListener {

    public static final String TAG = WeatherFragment.class.getSimpleName();

    private String city = "Westerville";


    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.weather_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.weather_date)
    TextView weatherDate;

    @BindView(R.id.weather_current_temp)
    TextView currentTemp;

    @BindView(R.id.weather_min_temp)
    TextView minTemp;

    @BindView(R.id.weather_max_temp)
    TextView maxTemp;

    @BindView(R.id.weather_city_name)
    TextView cityName;

    @BindView(R.id.weather_icon)
    ImageView weatherIcon;

    @BindView(R.id.weather_description)
    TextView weatherDescription;

    @BindView(R.id.loading_forecast_progress)
    ProgressBar progressBar;

    @BindView(R.id.update_city)
    FloatingActionButton updateCityFAB;


    @Inject
    MainScreenPresenter presenter;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    EventBus eventBus;

    private WeatherResponse weatherResponse;
    private WeatherForecastResponse weatherForecastResponse;
    private WeatherForecastAdapter adapter;
    private Dialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Add WeatherFragment to Dagger
        MyApplication.getAppComponent(MyApplication.getAppContext()).inject(this);
        //Register WeatherFragment for EventBus
        eventBus.register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, rootView);
        adapter = new WeatherForecastAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        updateCityFAB.setOnClickListener(this);

        //Fetch Weather Data from openWeather API
        presenter.fetchWeatherData(city);

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Unregister the EventBus
        if (eventBus.isRegistered(this))
            eventBus.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(NetworkResponseEvent event) {
        Timber.tag(TAG).d("Response received from Network");
        if (event.getBaseResponse() instanceof WeatherResponse) {
            weatherResponse = (WeatherResponse) event.getBaseResponse();
            handleWeatherResponse();
        } else if (event.getBaseResponse() instanceof WeatherForecastResponse) {
            weatherForecastResponse = (WeatherForecastResponse) event.getBaseResponse();
            handleWeatherForecastResponse();
            CommonUtils.hideProgressBar(progressDialog);
            progressDialog = null;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(NetworkResponseFailedEvent event) {
        CommonUtils.hideProgressBar(progressDialog);
        progressDialog = null;
        createSnackBar(event.getMessage());
    }

    private void createSnackBar(String message) {
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "Server Response: " + message, Snackbar.LENGTH_LONG)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new UpdateCityDialog().show(getActivity().getFragmentManager(), "UpdateCityFragment");
                    }
                });

        snackbar.show();
    }

    private void handleWeatherForecastResponse() {
        progressBar.setVisibility(View.GONE);
        adapter.setWeatherForecastResponse(weatherForecastResponse);
        adapter.notifyDataSetChanged();
    }

    private void handleWeatherResponse() {
        loadWeatherData();
        presenter.fetchWeatherForecastData(city);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void loadWeatherData() {
        String minTempStr = String.format("%s %d", "Min", (int) weatherResponse.getMain().getTemp_min());
        String maxTempStr = String.format("%s %d", "Max", (int) weatherResponse.getMain().getTemp_max());
        CommonUtils.setTextToTextView(minTemp, minTempStr + (char)0x00B0);
        CommonUtils.setTextToTextView(maxTemp, maxTempStr + (char)0x00B0);

        String temp = String.format("%d", (int) weatherResponse.getMain().getTemp());
        CommonUtils.setTextToTextView(currentTemp, temp + (char)0x00B0 + "C");
        CommonUtils.setTextToTextView(weatherDate, DateTimeUtils.getDate(weatherResponse.getDt()));
        CommonUtils.setTextToTextView(cityName, weatherResponse.getName());

        if (weatherResponse.getWeatherList() != null && weatherResponse.getWeatherList().size() > 0) {
            Weather weather = weatherResponse.getWeatherList().get(0);
            loadImage(weather);
            CommonUtils.setTextToTextView(weatherDescription, weather.getDescription());
        }
    }

    private void loadImage(Weather weather) {
        String url = String.format("%s/%s", BuildConfig.ICON_URL, weather.getIcon());

        Timber.tag(TAG).d("Image Url: " + url);
        // Load weather icon into imageView using Glide
        Glide.with(this)
                .load(url)
                .fitCenter()
                .error(R.drawable.ic_sunny)
                .into(weatherIcon);
    }

    public void updateWeatherCity(String cityName) {
        progressDialog = CommonUtils.createProgressBarDialog(getActivity());
        city = cityName;
        presenter.fetchWeatherData(cityName);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.update_city) {
            new UpdateCityDialog().show(getActivity().getFragmentManager(), "UpdateCityFragment");
        }
    }
}
