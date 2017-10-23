package com.fc.fcstroe.data.model;

import com.fc.fcstroe.common.http.CommonParamsInterceptor;
import com.fc.fcstroe.common.rx.RxHttpResponseCompat;
import com.fc.fcstroe.common.rx.observer.ProgressObserver;
import com.fc.fcstroe.data.bean.AppInfo;
import com.fc.fcstroe.data.bean.BaseBean;
import com.fc.fcstroe.data.net.ApiServer;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class AppDetailModel {

    private ApiServer mApiServer;

    public AppDetailModel(ApiServer mApiServer) {

        this.mApiServer = mApiServer;

    }

    public Observable<BaseBean<AppInfo>> getAppDetail(int id){

        return mApiServer.getAppDetail(id);

    }
}
