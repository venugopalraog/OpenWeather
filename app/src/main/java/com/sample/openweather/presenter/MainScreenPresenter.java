package com.sample.openweather.presenter;

import android.content.Context;

import com.sample.openweather.models.BaseResponse;
import com.sample.openweather.models.events.NetworkResponseEvent;
import com.sample.openweather.net.request.NetworkRequest;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

/**
 * Created by venugopalraog on 10/12/17.
 */

public class MainScreenPresenter extends BasePresenter implements RequestStatusListner {

    @Inject
    NetworkRequest networkRequest;

    @Inject
    EventBus eventBus;

    @Inject
    MainScreenPresenter(Context context) {
        super(context);
    }

    public void fetchWeatherData(String city) {
        networkRequest.getWeatherModel(city, this);
    }

    public void fetchWeatherForecastData(String city) {
        networkRequest.getWeatherForecastModel(city, this);
    }

    @Override
    public void onRequestSuccess(BaseResponse baseResponse) {
        eventBus.post(new NetworkResponseEvent(baseResponse));

    }

    @Override
    public void onRequestFailed(String message) {

    }

}
