package com.fc.fcstroe.common.rx.observer;

import android.app.ProgressDialog;
import android.content.Context;

import com.fc.fcstroe.common.rx.RxErrorHandler;
import com.fc.fcstroe.common.util.ProgressDialogHandler;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public abstract class NProgressDialogObserver<T> extends ErrorHandlerObserver<T> implements ProgressDialogHandler.OnProgressCancelListener {

    private ProgressDialogHandler mProgressDialogHandler;
    protected Disposable d;


    public NProgressDialogObserver(Context context) {
        super(context);
        //如果为true则有取消按钮
        //mProgressDialogHandler = new ProgressDialogHandler(mContext,true,this);
        mProgressDialogHandler = new ProgressDialogHandler(mContext, false, this);
    }

    protected boolean isShowDialog() {
        return true;
    }

    @Override
    public void onCancelProgress() {
//        unsubscribe();
        d.dispose();
    }


    @Override
    public void onSubscribe(@NonNull Disposable d) {
        this.d = d;
        if (isShowDialog()) {
            this.mProgressDialogHandler.showProgressDialog();
        }
    }

    @Override
    public void onComplete() {

        if (isShowDialog()) {
            this.mProgressDialogHandler.dismissProgressDialog();
        }
    }


    @Override
    public void onError(Throwable e) {
        super.onError(e);

        if (isShowDialog()) {
            this.mProgressDialogHandler.dismissProgressDialog();
        }

    }
}
