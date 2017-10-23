package com.fc.fcstroe.dagger.module;

import com.fc.fcstroe.data.model.AppDetailModel;
import com.fc.fcstroe.data.net.ApiServer;
import com.fc.fcstroe.presenter.AppDetailPresenter;
import com.fc.fcstroe.presenter.contract.IAppInfoConract;

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
public class AppDetailModule {

    private IAppInfoConract.AppDetailView mView;

    public AppDetailModule(IAppInfoConract.AppDetailView view) {

        this.mView = view;

    }

    @Provides
    public IAppInfoConract.AppDetailView provideAppDetailView(){
        return mView;
    }

    @Provides
    public AppDetailModel provideAppDetailModel(ApiServer apiServer){
        return new AppDetailModel(apiServer);
    }

    @Provides
    public AppDetailPresenter provideAppDetailPresenter(IAppInfoConract.AppDetailView view,AppDetailModel model){
        return new AppDetailPresenter(view, model);
    }

}
