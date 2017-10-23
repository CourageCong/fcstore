package com.fc.fcstroe.ui.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fc.fcstroe.FcApplication;
import com.fc.fcstroe.R;
import com.fc.fcstroe.common.util.DeviceUtils;
import com.fc.fcstroe.common.util.UT;
import com.fc.fcstroe.dagger.component.ApComponent;
import com.fc.fcstroe.dagger.component.DaggerLogingComponent;
import com.fc.fcstroe.dagger.module.LoginModule;
import com.fc.fcstroe.data.bean.LoginBean;
import com.fc.fcstroe.presenter.LoginPresenter;
import com.fc.fcstroe.presenter.contract.ILoginContract;
import com.fc.fcstroe.ui.widget.LoadingButton;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static android.Manifest.permission.READ_PHONE_STATE;
import static com.fc.fcstroe.common.constant.Constant.USER_RESULT;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginContract.LoginView {


    private static final int READ_PHONE_STATE_CODE = 666;

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.txt_mobi)
    EditText mTxtMobi;
    @BindView(R.id.view_mobi_wrapper)
    TextInputLayout mViewMobiWrapper;
    @BindView(R.id.txt_password)
    EditText mTxtPassword;
    @BindView(R.id.view_password_wrapper)
    TextInputLayout mViewPasswordWrapper;
    @BindView(R.id.btn_login)
    LoadingButton mBtnLogin;
    @BindView(R.id.register_account)
    TextView mRegisterAccount;


    @Override
    public int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void setComponent(ApComponent component) {
        DaggerLogingComponent.builder()
                .apComponent(component)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }


    @Override
    public void init() {
        initView();
    }

    private void initView() {

        Observable<CharSequence> obNum = RxTextView.textChanges(mTxtMobi);
        Observable<CharSequence> obPassWord = RxTextView.textChanges(mTxtPassword);

        Observable.combineLatest(obNum, obPassWord, new BiFunction<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(@io.reactivex.annotations.NonNull CharSequence mobile, @io.reactivex.annotations.NonNull CharSequence password) throws Exception {
                return isPhoneValid(mobile.toString()) && isPasswordValid(password.toString());//检查格式
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Boolean enable) throws Exception {
                RxView.enabled(mBtnLogin).accept(enable);

            }
        });

        RxView.clicks(mBtnLogin).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Object o) throws Exception {

                if (mTxtMobi.getText().toString().length() != 11) {
                    mViewMobiWrapper.setError("请输入正确的手机号码");
                } else {
                    mViewMobiWrapper.setError("");
                    mViewMobiWrapper.setErrorEnabled(false);
                    presenter.login(mTxtMobi.getText().toString(), mTxtPassword.getText().toString());
                }
            }
        });
/*
        RxTextView.textChanges(mTxtMobi)
                .map(new Function<CharSequence, String>() {
                    @Override
                    public String apply(@io.reactivex.annotations.NonNull CharSequence charSequence) throws Exception {
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());*/
    }

    private boolean isPhoneValid(String phone) {
        return phone.length() == 11;
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }




    @Override
    public void checkPhoneError() {
        mViewMobiWrapper.setError("请输入正确的手机号码");
    }

    @Override
    public void showLoading() {
        mBtnLogin.showLoading();
    }

    @Override
    public void dismissLoading() {
        mBtnLogin.showButtonText();
    }

    @Override
    public void showError(String msg) {
        mBtnLogin.showButtonText();
    }

    @Override
    public void loginSuccess(LoginBean bean) {
        FcApplication.getInstance().setLoginSuccess(true);
        Intent intent = new Intent();
        intent.putExtra(USER_RESULT, bean);
        setResult(RESULT_OK,intent);
        UT.show("登录成功");
        finish();
    }

    // TODO: 2017-10-10  错误的msg需要正确传递
    @Override
    public void loginError(String msg) {
        if (FcApplication.getInstance().isLoginSuccess()) {
            return;
        }
        Intent intent = new Intent();
        setResult(RESULT_CANCELED,intent);
        UT.show("登录失败");
    }

    @Override
    public void checkPhoneSuccess() {
        mViewMobiWrapper.setError("");
        mViewMobiWrapper.setErrorEnabled(false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    /************
     * test
     *********/
    public void onClick() {
//        originalRequest();
        RxPermissions rxP = new RxPermissions(this);
        rxP.request(READ_PHONE_STATE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            Toast.makeText(mApplication, "请求成功，进行操作", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(mApplication, "请求失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 普通请求方法
     */
    public void originalRequest() {
        int result = ActivityCompat.checkSelfPermission(this, READ_PHONE_STATE);//检查权限

        if (result != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{READ_PHONE_STATE}, READ_PHONE_STATE_CODE);//请求授权
        } else {

            //已经授权
            String imei = DeviceUtils.getIMEI(this);
            Toast.makeText(mApplication, "" + imei, Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == READ_PHONE_STATE_CODE) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                String imei = DeviceUtils.getIMEI(this);
                Toast.makeText(mApplication, "" + imei, Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(mApplication, "用户拒绝授权", Toast.LENGTH_SHORT).show();
            }
        }

    }


}
