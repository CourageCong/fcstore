package com.fc.fcstroe.presenter.contract;

import com.fc.fcstroe.data.bean.AppInfo;
import com.fc.fcstroe.data.bean.BaseBean;
import com.fc.fcstroe.data.bean.IndexBean;
import com.fc.fcstroe.data.bean.PageBean;
import com.fc.fcstroe.presenter.BasePresenter;
import com.fc.fcstroe.ui.BaseView;

import java.util.List;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public interface IAppInfoConract {

    interface View extends BaseView {

        /**
         * 没有数据的回调
         * */
        void showNoData();

        /**
         * 显示错误信息
         * @param msg 错误String
         * */
        void showError(String msg);


        /**
         * 返回数据并且显示
         * @param info 数据列表
         * */
        void showResult(List<AppInfo> info);

        /**
         * 返回网络数据
         * */
        void showNewResult(IndexBean indexBean);

        /**
         * 请求权限成功
         * */
        void onRequestPermissionSuccess();

        /**
         * 请求权限失败
         * */
        void onRequestPermissionError();
    }

    interface AppInfoView extends BaseView {

        /**
         * 返回数据并且显示
         * @param info 数据列表
         * */
        void showResult(PageBean<AppInfo> info);

        /**
         * 加载更多加载完成
         * */
        void onLoadMoreComplete();


    }

    interface  AppDetailView extends BaseView{

        void showAppDetail(AppInfo appInfo);
    }



}

