package com.fc.fcstroe.presenter;

import com.fc.fcstroe.common.rx.RxHttpResponseCompat;
import com.fc.fcstroe.common.rx.observer.ProgressObserver;
import com.fc.fcstroe.data.bean.AppInfo;
import com.fc.fcstroe.data.model.AppDetailModel;
import com.fc.fcstroe.data.model.AppModel;
import com.fc.fcstroe.presenter.contract.IAppInfoConract;

import io.reactivex.annotations.NonNull;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class AppDetailPresenter extends BasePresenter<AppDetailModel,IAppInfoConract.AppDetailView>{

    /**
     * model层需要ApiServer
     *
     * @param mView
     * @param model
     */
    public AppDetailPresenter(IAppInfoConract.AppDetailView mView, AppDetailModel model) {
        super(mView, model);
    }

    public void getAppDetail(int id){
        mModel.getAppDetail(id).compose(RxHttpResponseCompat.<AppInfo>compatResult())
                .subscribe(new ProgressObserver<AppInfo>(mContext, mView) {
                    @Override
                    public boolean isShowProgress() {
                        return false;
                    }

                    @Override
                    public void onNext(@NonNull AppInfo appInfo) {
                        mView.showAppDetail(appInfo);
                    }
                });
    }
}
