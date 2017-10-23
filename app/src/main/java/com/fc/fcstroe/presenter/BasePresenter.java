package com.fc.fcstroe.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.fc.fcstroe.data.model.AppModel;
import com.fc.fcstroe.data.net.ApiServer;
import com.fc.fcstroe.ui.BaseView;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class BasePresenter<M,V extends BaseView> {


    protected V mView;
    protected M mModel;
    protected Context mContext;
    /**
     * model层需要ApiServer
     * */
    public BasePresenter( V mView, M model) {

        this.mView = mView;
        this.mModel = model;
        initContext();
    }

    private void initContext() {

        if(mView instanceof Fragment){
            mContext = ((Fragment) mView).getActivity();
        }else{
            mContext = (Activity) mView;
        }

    }
}
