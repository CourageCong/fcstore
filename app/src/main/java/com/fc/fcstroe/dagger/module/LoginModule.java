package com.fc.fcstroe.dagger.module;

import com.fc.fcstroe.data.model.LoginModel;
import com.fc.fcstroe.data.net.ApiServer;
import com.fc.fcstroe.presenter.LoginPresenter;
import com.fc.fcstroe.presenter.contract.ILoginContract;

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
public class LoginModule {

    private ILoginContract.LoginView view;

    public LoginModule(ILoginContract.LoginView view) {
        this.view = view;
    }

    @Provides
    public LoginPresenter providePresenter(ILoginContract.LoginView view, ILoginContract.ILoginModel model) {

        return new LoginPresenter(view, model);//直接从构造方法中获得也可以
    }

    @Provides
    public ILoginContract.LoginView provideLoginView(){
        return view;
    }

    @Provides
    public ILoginContract.ILoginModel provideLoginModel(ApiServer apiServer){
        return new LoginModel(apiServer);
    }


}
