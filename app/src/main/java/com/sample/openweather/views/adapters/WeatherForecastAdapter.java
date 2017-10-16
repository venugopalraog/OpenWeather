package com.sample.openweather.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sample.openweather.BuildConfig;
import com.sample.openweather.R;
import com.sample.openweather.models.WeatherForecastResponse;
import com.sample.openweather.models.weather.ForecastData;
import com.sample.openweather.models.weather.Weather;
import com.sample.openweather.utils.CommonUtils;
import com.sample.openweather.utils.DateTimeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by venugopalraog on 10/14/17.
 */

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.ForecastViewHolder> {
    private static final String TAG = WeatherForecastAdapter.class.getSimpleName();

    WeatherForecastResponse weatherForecastResponse;

    public WeatherForecastAdapter() {   }

    public void setWeatherForecastResponse(WeatherForecastResponse weatherForecastResponse) {
        this.weatherForecastResponse = weatherForecastResponse;
    }

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder viewHolder, int position) {
        viewHolder.bindData(getItemData(position));
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_weather_forecast_details;
    }

    private ForecastData getItemData(int pos) {
        return weatherForecastResponse.getList().get(pos);
    }

    @Override
    public int getItemCount() {
        if (weatherForecastResponse == null || weatherForecastResponse.getList() == null)
            return 0;

        return weatherForecastResponse.getList().size();
    }

    public class ForecastViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.weather_forecast_icon)
        ImageView weatherIcon;

        @BindView(R.id.weather_forecast_status)
        TextView weatherStatus;

        @BindView(R.id.weather_forecast_day)
        TextView forecastDay;

        @BindView(R.id.weather_forecast_date)
        TextView forecastDate;

        @BindView(R.id.weather_forecast_max_temp)
        TextView maxTemp;

        @BindView(R.id.weather_forecast_min_temp)
        TextView minTemp;


        public ForecastViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(ForecastData forecastData) {

            CommonUtils.setTextToTextView(forecastDay, DateTimeUtils.getDayOfWeek(forecastData.getDt()));
            CommonUtils.setTextToTextView(forecastDate, DateTimeUtils.getDate(forecastData.getDt()));

            String minTempStr = String.format("%s %d", "Min", (int) forecastData.getTemp().getMin());
            String maxTempStr = String.format("%s %d", "Max", (int) forecastData.getTemp().getMax());
            CommonUtils.setTextToTextView(minTemp, minTempStr + (char)0x00B0);
            CommonUtils.setTextToTextView(maxTemp, maxTempStr + (char)0x00B0);

            if (forecastData.getWeatherList() == null || forecastData.getWeatherList().size() < 1) {
                Timber.tag(TAG).d("Icon name is null - return");
                return;
            }

            Weather weather = forecastData.getWeatherList().get(0);
            CommonUtils.setTextToTextView(weatherStatus, weather.getMain());
            loadImage(weather);
        }

        private void loadImage(Weather weather) {
            String url = String.format("%s/%s", BuildConfig.ICON_URL, weather.getIcon());
            Timber.tag(TAG).d("Image Url: " + url);
            // Load weather icon into imageView using Glide
            Glide.with(itemView.getContext())
                    .load(url)
                    .fitCenter()
                    .error(R.drawable.ic_sunny)
                    .into(weatherIcon);
        }
    }
}
