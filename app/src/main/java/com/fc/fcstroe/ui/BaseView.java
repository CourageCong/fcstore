package com.fc.fcstroe.ui;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public interface BaseView {

    /**
     * 加载数据前显示loading
     * */
    void showLoading();

    /**
     * 隐藏loading
     * */
    void dismissLoading();

    /**
     * 显示错误
     * */
    void showError(String msg);

}
