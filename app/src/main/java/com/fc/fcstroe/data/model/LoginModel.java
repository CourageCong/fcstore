package com.fc.fcstroe.data.model;

import com.fc.fcstroe.data.bean.BaseBean;
import com.fc.fcstroe.data.bean.LoginBean;
import com.fc.fcstroe.data.bean.requestbean.LoginRequestBean;
import com.fc.fcstroe.data.net.ApiServer;
import com.fc.fcstroe.presenter.contract.ILoginContract;
import com.google.gson.Gson;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.RequestBody;
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

public class LoginModel implements ILoginContract.ILoginModel{

    private ApiServer mApiServer;


    public LoginModel(ApiServer mApiServer) {
        this.mApiServer = mApiServer;
    }

    @Override
    public Observable<BaseBean<LoginBean>> login(String phone , String password) {
        LoginRequestBean param = new LoginRequestBean();
        param.setEmail(phone);
        param.setPassword(password);

//        Gson gson=new Gson();
//
//        HashMap<String,String> paramsMap=new HashMap<>();
//
//        paramsMap.put("email",phone);
//        paramsMap.put("password",password);
//
//        String strEntity = gson.toJson(paramsMap);
//
//        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),strEntity);

//        return mApiServer.login(param);
        return mApiServer.login(param);
    }
}
