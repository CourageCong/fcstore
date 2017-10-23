package com.fc.fcstroe;

import android.app.Application;
import android.os.Handler;
import android.view.View;

import com.fc.fcstroe.dagger.component.DaggerApComponent;
import com.fc.fcstroe.dagger.module.ApModule;
import com.fc.fcstroe.dagger.module.HttpModule;

import javax.inject.Qualifier;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class FcApplication extends Application{

    private DaggerApComponent mDaggerAppComponent;
    private static FcApplication fcApplication = null;
    private Handler mHandler;
    private boolean hasLoginSuccess = false;
    //保存传递的视图
    private View mView;

    // TODO: 2017-08-11
    public static FcApplication getInstance(){
        return fcApplication;
    }

    public DaggerApComponent getDaggerAppComponent(){
        return mDaggerAppComponent;
    }



    @Override
    public void onCreate() {
        super.onCreate();

        fcApplication = this;

        //之前apModule显示过时，是因为在ApComponent中没有提供注册接口，同时也没有对外开放ApModule中
        //的实例，ApModule被认定为没有被使用，所以显示过时，在ApComponent中对外提供了一个Gson接口后，
        //不再过时
        mDaggerAppComponent = (DaggerApComponent) DaggerApComponent.builder().httpModule(new HttpModule())
                .apModule(new ApModule(fcApplication)).build();

        mHandler = new Handler();
    }

    public Handler getUiHandler(){
        return mHandler;
    }

    public void runOnUiThread(Runnable runnable){
        mHandler.post(runnable);
    }

    public boolean isLoginSuccess() {
        return hasLoginSuccess;
    }

    public void setLoginSuccess(boolean hasLoginSuccess) {
        this.hasLoginSuccess = hasLoginSuccess;
    }

    public View getView() {
        return mView;
    }

    public void setView(View view) {
        mView = view;
    }
}
