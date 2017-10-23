package com.fc.fcstroe.ui.fragment.super_fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.fc.fcstroe.FcApplication;
import com.fc.fcstroe.R;
import com.fc.fcstroe.dagger.component.ApComponent;
import com.fc.fcstroe.presenter.BasePresenter;
import com.fc.fcstroe.ui.BaseView;

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

public abstract class ProgressFragment<T extends BasePresenter> extends Fragment  implements BaseView {



    private FrameLayout mRootView;

    private View mViewProgress;
    private FrameLayout mViewContent;
    private View mViewEmpty;
    private Unbinder mUnbinder;

    private TextView mTextError;

    protected FcApplication mApplication;

    protected Context mContext;
    @Inject
    public T presenter ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        mRootView = (FrameLayout) inflater.inflate(R.layout.fragment_progress,container,false);
        mViewProgress = mRootView.findViewById(R.id.view_progress);
        mViewContent = (FrameLayout) mRootView.findViewById(R.id.view_content);
        mViewEmpty = mRootView.findViewById(R.id.view_empty);

        mTextError = (TextView) mRootView.findViewById(R.id.text_tip);

        //加载错误时，点击错误信息重新加载
        mViewEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEmptyViewClick();
            }
        });


        return mRootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.mApplication = (FcApplication) getActivity().getApplication();
        mContext = getActivity();
        setComponent(mApplication.getDaggerAppComponent());
        setRealContentView();

        init();
    }

    /**
     * 用来处理空页面的点击事件，用来重新加载数据
     * */
    public abstract void onEmptyViewClick();

    private void setRealContentView() {

       View realContentView=  LayoutInflater.from(getActivity()).inflate(setLayoutId(),mViewContent,true);
        mUnbinder=  ButterKnife.bind(this, realContentView);

    }



    public void  showProgressView(){
        showView(R.id.view_progress);
    }

    public void showContentView(){

        showView(R.id.view_content);
    }

    public void showEmptyView(){


        showView(R.id.view_empty);
    }


    public void showEmptyView(int resId){


        showEmptyView();
        mTextError.setText(resId);
    }

    public void showEmptyView(String msg){


        showEmptyView();
        mTextError.setText(msg);
    }



    public void showView(int viewId){

        for(int i=0;i<mRootView.getChildCount();i++){

            if( mRootView.getChildAt(i).getId() == viewId){

                mRootView.getChildAt(i).setVisibility(View.VISIBLE);
            }
            else {
                mRootView.getChildAt(i).setVisibility(View.GONE);
            }

        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mUnbinder != Unbinder.EMPTY){
            mUnbinder.unbind();
        }
    }

    @Override
    public void showLoading() {
        showProgressView();
    }

    @Override
    public void showError(String msg) {
        showEmptyView(msg);
    }

    @Override
    public void dismissLoading() {
        showContentView();
    }

    public abstract void  init();

    public abstract int setLayoutId();

    public abstract  void setComponent(ApComponent appComponent);

}
