package com.fc.fcstroe.dagger.component;

import android.app.Application;

import com.fc.fcstroe.common.rx.RxErrorHandler;
import com.fc.fcstroe.dagger.module.ApModule;
import com.fc.fcstroe.dagger.module.HttpModule;
import com.fc.fcstroe.data.model.AppModel;
import com.fc.fcstroe.data.net.ApiServer;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;

/**
 * application级别Component
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */
@Singleton
@Component(modules = {ApModule.class, HttpModule.class})

public interface ApComponent {

    ApiServer getAipServer();// TODO: 2017-08-11 这句话非常重要，是暴露给孩子的接口
    Gson getGson();

    Application getApplication();

    RxErrorHandler getRxErrorHandler();

    AppModel getAppModel();

}
