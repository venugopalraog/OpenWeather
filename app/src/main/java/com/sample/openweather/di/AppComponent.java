package com.sample.openweather.di;

import com.sample.openweather.presenter.MainScreenPresenter;
import com.sample.openweather.views.activities.HomeActivity;
import com.sample.openweather.views.fragments.WeatherFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by venugopalraog on 10/12/17.
 */

@Singleton
@Component(modules={BaseModuleApplication.class})
public interface AppComponent {

    void inject(HomeActivity activity);
    void inject(MainScreenPresenter mainScreenPresenter);
    void inject(WeatherFragment weatherFragment);
}
