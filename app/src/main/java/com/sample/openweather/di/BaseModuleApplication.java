package com.sample.openweather.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.sample.openweather.net.request.BaseRequest;
import com.sample.openweather.net.request.NetworkRequest;
import com.sample.openweather.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by venugopalraog on 10/12/17.
 */
@Module
public class BaseModuleApplication {

    MyApplication application;

    public BaseModuleApplication(MyApplication myApplication) {
        application = myApplication;
    }

    @Provides
    Application providesApplication() {
        return application;
    }

    @Provides
    Context providesContext() {
        return application.getApplicationContext();
    }

    @Provides
    EventBus providesEventBus() {
        return EventBus.getDefault();
    }

    @Provides
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    SharedPreferencesUtil providesSharedPreferencesUtil(SharedPreferences sharedPreferences) {
        return new SharedPreferencesUtil(sharedPreferences);
    }

    @Provides
    @Singleton
    BaseRequest providesNetworkRequest() {
        return new NetworkRequest();

        // Use ModelFromRawAssets class to test Weather fragment with local Json Response from raw resource folder
        // return new ModelFromRawAssets(mMyApplication);
    }
}