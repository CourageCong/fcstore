package com.fc.fcstroe.dagger.module;

import android.app.Application;

import com.fc.fcstroe.data.model.AppModel;
import com.google.gson.Gson;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */
@Module
public class ApModule {

    private Application mApplication;

    public ApModule(Application mApplication) {
        this.mApplication = mApplication;
    }


    @Provides
    @Singleton
    public Application provideApplication(){
        return mApplication;
    }

    @Provides
    @Singleton
    public Gson provideGson(){
        return new Gson();
    }


}
