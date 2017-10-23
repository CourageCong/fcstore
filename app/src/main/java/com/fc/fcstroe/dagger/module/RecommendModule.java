package com.fc.fcstroe.dagger.module;

import android.app.ProgressDialog;

import com.fc.fcstroe.data.model.AppModel;
import com.fc.fcstroe.presenter.RecommendPresenter;
import com.fc.fcstroe.presenter.contract.IAppInfoConract;
import com.fc.fcstroe.ui.fragment.RecommendFragment;

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
public class RecommendModule {

    private IAppInfoConract.View mView;

    public RecommendModule(IAppInfoConract.View view) {

        this.mView = view;
    }

    @Provides
    public RecommendPresenter providePresenter(IAppInfoConract.View view, AppModel appModel) {

        return new RecommendPresenter(view, appModel);//直接从构造方法中获得也可以
    }

    @Provides
    public IAppInfoConract.View provideView(){
        return mView;
    }


    @Provides
    public ProgressDialog provideProgressDialog(IAppInfoConract.View view){
        return new ProgressDialog(((RecommendFragment) view).getActivity());
    }

}
