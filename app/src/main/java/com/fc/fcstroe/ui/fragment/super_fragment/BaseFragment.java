package com.fc.fcstroe.ui.fragment.super_fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fc.fcstroe.FcApplication;
import com.fc.fcstroe.dagger.component.ApComponent;
import com.fc.fcstroe.presenter.BasePresenter;
import com.fc.fcstroe.ui.BaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static butterknife.ButterKnife.bind;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView{

    protected Unbinder mUnbinder;
    protected FcApplication mApplication;

    protected View rootView;

    protected Context mContext;

    private ProgressDialog mProgressDialog;

    @Inject
    protected T presenter;// TODO: 2017-08-11

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(setLayoutId(), container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        mApplication = FcApplication.getInstance();
        mContext = getActivity();


        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setComponent(mApplication.getDaggerAppComponent());

        init();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }

    }


    /**
     * 初始化布局，返回layout Id
     */
    public abstract int setLayoutId();

    /**
     * dagger注入
     * eg.
     * DaggerRecommendCoponent.builder()
     * .apComponent(component)
     * .recommendModule(new RecommendModule(this))
     * .build()
     * .inject(this);
     */
    public abstract void setComponent(ApComponent component);

    /**
     * 负责初始化工作
     */
    public abstract void init();

    /**
     * 加载数据前显示loading
     * */
    public void showLoading(){

    }

    /**
     * 隐藏loading
     * */
    public void dismissLoading(){

    }

    /**
     * 显示错误
     * */
    public void showError(String msg){

    }

}
