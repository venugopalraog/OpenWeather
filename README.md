# OpenWeather
# Description
Open weather application allows user to view Current weather and 5 days weather forecast
information.
# Features
 1. Displays Current Temp, Min Temp, Max Temp, brief description and weather Icon.
 2. Allows users to change the City Name and fetches the latest weather information.
 3. Saves the last searched City Name and displays last searched when user restart the
application.
 4. Currently this application supports only Celsius unit.
# Application Architecture
  Used Model-View-Presenter (MVP) Architecture in this application.
  
  Model – Classes inside package com.sample.openweather.models. Main classes WeatherResponse, WeatherForecastResponse.
  
  Presenter – HomePresenter and RequestStatusListener interface between Presenter and Model.  
  
  View – classes inside com.sample.openweather.views are part of View component.
