package com.sample.openweather.net.request;

import com.sample.openweather.presenter.RequestStatusListener;

/**
 * Created by venugopalraog on 10/13/17.
 */

public abstract class BaseRequest {

    protected RequestStatusListener networkListener;

    public abstract void getWeatherModel(String city, RequestStatusListener listener);

    public abstract void getWeatherForecastModel(String city, RequestStatusListener listener);
}
