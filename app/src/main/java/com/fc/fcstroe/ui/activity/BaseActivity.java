package com.fc.fcstroe.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.fc.fcstroe.FcApplication;
import com.fc.fcstroe.dagger.component.ApComponent;
import com.fc.fcstroe.presenter.BasePresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity{

    protected Unbinder mUnbinder;
    protected FcApplication mApplication;
    
    @Inject
    protected T presenter;// TODO: 2017-08-11

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());

        mUnbinder = ButterKnife.bind(this);

        mApplication = FcApplication.getInstance();

        setComponent(mApplication.getDaggerAppComponent());

        init();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != Unbinder.EMPTY) {

            mUnbinder.unbind();//解除绑定
        }
    }

    /**
     * 初始化布局，返回layout Id
     * */
    public abstract int setLayoutId();

    /**
     * dagger注入
     * */
    public abstract void setComponent(ApComponent component);

    /**
     * 负责初始化工作
     * */
    public abstract void init();

}
