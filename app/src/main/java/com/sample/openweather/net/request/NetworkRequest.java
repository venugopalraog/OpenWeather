package com.sample.openweather.net.request;

import com.google.gson.GsonBuilder;
import com.sample.openweather.BuildConfig;
import com.sample.openweather.models.BaseResponse;
import com.sample.openweather.presenter.RequestStatusListner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by venugopalraog on 9/25/16.
 */

public class NetworkRequest extends BaseRequest {

    private static final String TAG = NetworkRequest.class.getSimpleName();

    public static final String BASE_URL = BuildConfig.WEATHER_API_URL;


    private OpenWeatherInterface createWeatherInterface() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();

        return retrofit.create(OpenWeatherInterface.class);
    }

    private <T extends BaseResponse> void requestServer(Call<T> call) {

        Timber.tag(TAG).d("Http URL :: " + call.request().url().toString());

        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                Timber.tag(TAG).d("Received server response...");
                if (response.body() != null) {
                    Timber.tag(TAG).d(" response: " + response.body().toString());
                    if (networkListener != null)
                        networkListener.onRequestSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Timber.tag(TAG).d("Server Error: " + t.getMessage());
                if (networkListener != null)
                    networkListener.onRequestFailed(t.getMessage());
            }
        });
    }

    public void getWeatherModel(String city, RequestStatusListner listner) {
        this.networkListener = listner;
        requestServer(createWeatherInterface().getWeatherData(city, "metric", BuildConfig.OPEN_WEATHER_API_KEY));
    }

    public void getWeatherForecastModel(String city, RequestStatusListner listner) {
        this.networkListener = listner;
        requestServer(createWeatherInterface().getWeatherForecastData(city, "metric", "5", BuildConfig.OPEN_WEATHER_API_KEY));
    }

}
