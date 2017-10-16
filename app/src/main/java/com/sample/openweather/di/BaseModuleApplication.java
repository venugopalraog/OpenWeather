package com.sample.openweather.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.sample.openweather.net.request.NetworkRequest;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by venugopalraog on 10/12/17.
 */
@Module
public class BaseModuleApplication {

    MyApplication mMyApplication;

    public BaseModuleApplication(MyApplication myApplication) {
        mMyApplication = myApplication;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mMyApplication;
    }

    @Provides
    @Singleton
    Context providesContext() {
        return mMyApplication.getApplicationContext();
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    EventBus providesEventBus(Application application) {
        return EventBus.getDefault();
    }

    @Provides
    @Singleton
    NetworkRequest providesNetworkRequest() {
        return new NetworkRequest();
    }

}
