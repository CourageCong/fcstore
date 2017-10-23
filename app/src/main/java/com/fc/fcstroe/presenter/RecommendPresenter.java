package com.fc.fcstroe.presenter;

import android.app.Activity;

import com.fc.fcstroe.common.rx.RxHttpResponseCompat;
import com.fc.fcstroe.common.rx.observer.ProgressObserver;
import com.fc.fcstroe.data.bean.AppInfo;
import com.fc.fcstroe.data.bean.IndexBean;
import com.fc.fcstroe.data.bean.PageBean;
import com.fc.fcstroe.data.model.AppModel;
import com.fc.fcstroe.presenter.contract.IAppInfoConract;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static android.Manifest.permission.READ_PHONE_STATE;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class RecommendPresenter extends BasePresenter<AppModel,IAppInfoConract.View> {



    public RecommendPresenter(IAppInfoConract.View mView, AppModel mAppModel) {
        super(mView, mAppModel);
    }

    public void requestPermission(){

        RxPermissions rxPermissions = new RxPermissions((Activity) mContext);

        rxPermissions.request(READ_PHONE_STATE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            mView.onRequestPermissionSuccess();
                        }else{
                            mView.onRequestPermissionError();

                        }
                    }
                });

    }


    public void requestDatas() {


//        mView.showLoading();
//        requestTestData();
        mModel.getRecommendPage()
                .compose(RxHttpResponseCompat.<IndexBean>compatResult())
                .subscribe(new ProgressObserver<IndexBean>(mContext, mView) {

                    @Override
                    public boolean isShowProgress() {
                        return true;
                    }

                    @Override
                    public void onNext(@NonNull IndexBean indexBean) {
                        mView.showNewResult(indexBean);
                    }
                });
    }

    public void requestTestData(){
        RxPermissions rxPermissions = new RxPermissions((Activity) mContext);

        rxPermissions.request(READ_PHONE_STATE)
                .flatMap(new Function<Boolean, Observable<PageBean<AppInfo>>>() {
                    @Override
                    public Observable<PageBean<AppInfo>> apply(@NonNull Boolean aBoolean) throws Exception {
                        if(aBoolean){
//                            return mModel.getApps().compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult());// TODO: 2017-08-21
                        }else{
                            mView.onRequestPermissionError();
                        }

                        return Observable.empty();// TODO: 2017-08-21
                    }
                })
                .subscribe(new ProgressObserver<PageBean<AppInfo>>(mContext,mView) {

                    @Override
                    public boolean isShowProgress() {
                        return true;
                    }

                    @Override
                    public void onNext(@NonNull PageBean<AppInfo> appInfoPageBean) {
                        if (appInfoPageBean != null) {

                            mView.showResult(appInfoPageBean.getDatas());

                        } else {

                            mView.showNoData();//提示没有数据
                        }

                        mView.dismissLoading();
                    }

                });


        /*mModel.getApps()
                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())// TODO: 2017-08-21
                .subscribe(new ProgressObserver<PageBean<AppInfo>>(mContext,mView) {

                    @Override
                    public void onNext(@NonNull PageBean<AppInfo> appInfoPageBean) {
                        if (appInfoPageBean != null) {

                            mView.showResult(appInfoPageBean.getDatas());

                        } else {

                            mView.showNoData();//提示没有数据
                        }

                        mView.dismissLoading();
                    }

                });*/

        /*new Observer<PageBean<AppInfo>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull PageBean<AppInfo> response) {

            }

            @Override
            public void onError(@NonNull Throwable e) {
            }

            @Override
            public void onComplete() {
                mView.dismissLoading();
            }
        }*/
    }
}
