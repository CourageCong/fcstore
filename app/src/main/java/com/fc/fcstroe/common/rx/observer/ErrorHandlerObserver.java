package com.fc.fcstroe.common.rx.observer;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.fc.fcstroe.common.exception.BaseException;
import com.fc.fcstroe.common.rx.RxErrorHandler;
import com.fc.fcstroe.ui.activity.LoginActivity;

import static com.baidu.location.d.g.B;
import static com.baidu.location.d.g.i;

/**
 *其他ovserver都应该继承该类，该类用来处理网络数据的各种错误
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public abstract class ErrorHandlerObserver<T> extends BaseObserver<T> {

    protected RxErrorHandler mErrorHandler = null;
    protected Context mContext;


    public ErrorHandlerObserver(Context context){

        this.mContext = context;
        this.mErrorHandler = new RxErrorHandler(context);


    }


    @Override
    public void onError(Throwable e) {

        BaseException baseException =  mErrorHandler.handleError(e);

        e.printStackTrace();
        if(baseException==null){
            Log.d("ErrorHandlerSubscriber",e.getMessage());
        }
        else {

            //提示用户错误信息
            mErrorHandler.showErrorMessage(baseException);

            //token失效，跳转到登录页面
            if (baseException.getCode() == BaseException.ERROR_TOKEN) {
                toLogin();
            }



        }

    }

    private  void toLogin(){
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
    }
}
