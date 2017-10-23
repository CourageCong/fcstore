package com.fc.fcstroe.common.rx.observer;

import android.app.ProgressDialog;
import android.content.Context;

import com.fc.fcstroe.common.rx.RxErrorHandler;
import com.fc.fcstroe.common.util.ProgressDialogHandler;
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

public abstract class ProgressDialogObserver<T> extends ErrorHandlerObserver<T> implements ProgressDialogHandler.OnProgressCancelListener {

    //两种方法创建ProgressDialog
    private ProgressDialog mProgressDialog;
    private Context mContext;
    protected Disposable d;
//    private BaseView view;

    public ProgressDialogObserver(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        this.d = d;
        if (isShowDialog())
            showDialog();
    }

    @Override
    public void onError(@NonNull Throwable e) {
        super.onError(e);//记得调用父类的onError
        if (isShowDialog())
            dismissDialog();
    }

    @Override
    public void onComplete() {
        if (isShowDialog())
            dismissDialog();
    }

    /**
     * 如果某次不想显示dialog,只需返回false
     */
    public abstract boolean isShowDialog();

    private void initDialog() {

        if (mProgressDialog == null) {
            //注意这里的context对象不能是application的context
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setMessage("加载中....");
        }
    }

    private void showDialog() {
        initDialog();
        mProgressDialog.show();
//        view.showLoading();

    }

    private void dismissDialog() {

        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onCancelProgress() {
        d.dispose();
    }

}
