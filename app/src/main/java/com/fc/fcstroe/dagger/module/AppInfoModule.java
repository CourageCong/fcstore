package com.fc.fcstroe.dagger.module;

import com.fc.fcstroe.data.model.AppModel;
import com.fc.fcstroe.presenter.AppInfoPresenter;
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
public class AppInfoModule {

    private IAppInfoConract.AppInfoView mView;

    public AppInfoModule(IAppInfoConract.AppInfoView view) {

        this.mView = view;
    }

    @Provides
    public AppInfoPresenter providePresenter(IAppInfoConract.AppInfoView view, AppModel appModel) {

        return new AppInfoPresenter(view, appModel);//直接从构造方法中获得也可以
    }

    @Provides
    public IAppInfoConract.AppInfoView provideView(){
        return mView;
    }




}
