package com.fc.fcstroe.presenter;

import com.fc.fcstroe.common.constant.Constant;
import com.fc.fcstroe.common.rx.RxHttpResponseCompat;
import com.fc.fcstroe.common.rx.observer.ErrorHandlerObserver;
import com.fc.fcstroe.common.util.ACache;
import com.fc.fcstroe.common.util.VerificationUtils;
import com.fc.fcstroe.data.bean.LoginBean;
import com.fc.fcstroe.presenter.contract.ILoginContract.*;

import java.io.IOException;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class LoginPresenter extends BasePresenter<ILoginModel,LoginView> {

    /**
     * model层需要ApiServer
     *
     * @param mView
     * @param ILoginModel
     */
    public LoginPresenter(LoginView mView, ILoginModel ILoginModel) {
        super(mView, ILoginModel);
    }

    public void login(String phone ,String password){
        if(!VerificationUtils.matcherPhoneNum(phone)){//进一步检查格式
            mView.checkPhoneError();
            return;
        }

        mModel.login(phone, password)
                .compose(RxHttpResponseCompat.<LoginBean>compatResult())
                .subscribe(new ErrorHandlerObserver<LoginBean>(mContext) {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mView.showLoading();
                    }

                    @Override
                    public void onNext(@NonNull LoginBean bean) {
                        savUser(bean);
                        mView.dismissLoading();
                        mView.loginSuccess(bean);

                    }

                    @Override
                    public void onComplete() {
                        mView.dismissLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.loginError(e.getMessage());
                    }
                });

    }

    public void savUser(LoginBean bean){
        ACache aCache = ACache.get(mContext);
        aCache.put(Constant.TOKEN, bean.getToken());
        aCache.put(Constant.USER, bean.getUser());

    }


}
