package com.fc.fcstroe.presenter.contract;

import com.fc.fcstroe.data.bean.BaseBean;
import com.fc.fcstroe.data.bean.LoginBean;
import com.fc.fcstroe.data.bean.requestbean.LoginRequestBean;
import com.fc.fcstroe.ui.BaseView;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public interface ILoginContract {

    public interface ILoginModel {
        Observable<BaseBean<LoginBean>> login(String phone , String password);
    }

    public interface LoginView extends BaseView{
        //电话号码格式错误
        void checkPhoneError();

        void loginSuccess(LoginBean bean);

        void loginError(String msg);

        void checkPhoneSuccess();//更改textinputlayout的error状态
    }

}
