package com.fc.fcstroe.common.rx.observer;

import android.content.Context;

import com.fc.fcstroe.common.exception.BaseException;
import com.fc.fcstroe.common.rx.RxErrorHandler;
import com.fc.fcstroe.ui.BaseView;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public  abstract  class ProgressObserver<T> extends ErrorHandlerObserver<T>  {


    private BaseView mView;


    public ProgressObserver(Context context, BaseView view) {
        super(context);
        this.mView = view;

    }

    /**
     * @return true 显示进度条
     * */
    public abstract boolean isShowProgress();


    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if(isShowProgress()){
            mView.showLoading();
        }
    }




    @Override
    public void onComplete() {

        mView.dismissLoading();
    }



    @Override
    public void onError(Throwable e) {
        super.onError(e);
//        e.printStackTrace();
//
        BaseException baseException =  mErrorHandler.handleError(e);
        mView.showError(baseException.getDisplayMessage());
    }

}
