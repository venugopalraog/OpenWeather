package com.sample.openweather.net.request;

import com.sample.openweather.models.WeatherForecastResponse;
import com.sample.openweather.models.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by venugopalraog on 10/13/17.
 */

public interface OpenWeatherInterface {

    /* Method to fetch Weather Data from Server
    *
    * Params
    * city -- city Name
    * unit -- Temperature unit (metrics,
    *
    * */
    //http://api.openweathermap.org/data/2.5/weather?q=Westervile,oh,us&APPID=0130c1b90ca4611a93e316a154b4c718&units=metric
    @GET("weather")
    Call<WeatherResponse>  getWeatherData (@Query("q") String city,
                                           @Query("units") String unit,
                                           @Query("APPID") String appid);

    /* Method to fetch Weather Forecast Data from Server
    *
    * Params
    * city -- city Name
    * unit -- Temperature unit (metrics,
    * cnt  -- number days
    *
    * */
    //http://api.openweathermap.org/data/2.5/forecast?q=Westervile,oh,us&APPID=0130c1b90ca4611a93e316a154b4c718&units=metric&cnt=5
    @GET("forecast/daily")
    Call<WeatherForecastResponse> getWeatherForecastData(@Query("q") String city,
                                                         @Query("units") String unit,
                                                         @Query("cnt") String count,
                                                         @Query("APPID") String appid);

}