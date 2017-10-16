package com.sample.openweather.net.request;

import android.content.Context;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import com.sample.openweather.models.WeatherForecastResponse;
import com.sample.openweather.models.WeatherResponse;
import com.sample.openweather.presenter.RequestStatusListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import timber.log.Timber;

/**
 * Created by venugopalraog on 10/16/17.
 */

public class ModelFromRawAssets extends BaseRequest {

    private static String TAG = ModelFromRawAssets.class.getSimpleName();

    private final Context context;

    public ModelFromRawAssets(Context context) {
        this.context = context;
    }

    @Override
    public void getWeatherModel(String city, RequestStatusListener listener) {
        String jsonResponse = readFakeResponseFromAssets(context, "weather");
        if (jsonResponse != null) {
            Gson gson = new Gson();
            WeatherResponse weatherResponse = gson.fromJson(jsonResponse, WeatherResponse.class);
            listener.onRequestSuccess(weatherResponse);
        } else
            listener.onRequestFailed("Resource Not found");
    }

    @Override
    public void getWeatherForecastModel(String city, RequestStatusListener listener) {
        String jsonResponse = readFakeResponseFromAssets(context, "forecast");
        if (jsonResponse != null) {
            Gson gson = new Gson();
            WeatherForecastResponse weatherResponse = gson.fromJson(jsonResponse, WeatherForecastResponse.class);
            listener.onRequestSuccess(weatherResponse);
        } else
            listener.onRequestFailed("Resource Not found");
    }

    private String readFakeResponseFromAssets(Context context, String fileName) {
        int resourceId = context.getResources().getIdentifier(fileName, "raw", context.getPackageName());
        String jsonResponse = null;
        if (resourceId == 0) {
            Timber.tag(TAG).d("No Response fake file found in resources");
            return null;
        }

        InputStream stream = context.getResources().openRawResource(resourceId);
        InputStreamReader streamReader = new InputStreamReader(stream, Charsets.UTF_8);
        try {
            jsonResponse = CharStreams.toString(streamReader);
            streamReader.close();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }

}
