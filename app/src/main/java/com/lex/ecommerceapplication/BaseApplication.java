package com.lex.ecommerceapplication;

import android.app.Application;
import android.util.Log;

import timber.log.Timber;

/**
 * Base application
 *
 * @author DeepS
 */
public class BaseApplication extends Application
{
    private static final String TAG = "BaseApplication";

    private AppComponent appComponent;

    @Override
    public void onCreate()
    {
        super.onCreate();

        if (BuildConfig.DEBUG)
        {
            Log.d(TAG, "onCreate: ");
            Timber.plant(new Timber.DebugTree());
        }

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        appComponent.inject(this);
    }

    public AppComponent getAppComponent()
    {
        return appComponent;
    }
}
