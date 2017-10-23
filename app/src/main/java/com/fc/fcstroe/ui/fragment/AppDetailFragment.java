package com.fc.fcstroe.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fc.fcstroe.R;
import com.fc.fcstroe.common.imageloader.ImageLoader;
import com.fc.fcstroe.common.util.UT;
import com.fc.fcstroe.dagger.component.ApComponent;
import com.fc.fcstroe.dagger.component.DaggerAppDetailComponent;
import com.fc.fcstroe.dagger.module.AppDetailModule;
import com.fc.fcstroe.data.bean.AppInfo;
import com.fc.fcstroe.data.net.ApiServer;
import com.fc.fcstroe.presenter.AppDetailPresenter;
import com.fc.fcstroe.presenter.contract.IAppInfoConract;
import com.fc.fcstroe.ui.fragment.super_fragment.ProgressFragment;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class AppDetailFragment extends ProgressFragment<AppDetailPresenter> implements IAppInfoConract.AppDetailView {

    @BindView(R.id.view_gallery)
    LinearLayout mViewGallery;
    private int id;

    private LayoutInflater mLayoutInflater;

    public AppDetailFragment(int id) {
        this.id = id;
    }

    @Override
    public void onEmptyViewClick() {

    }

    @Override
    public void init() {
        mLayoutInflater = LayoutInflater.from(getActivity());
        presenter.getAppDetail(id);
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_app_detail;
    }

    @Override
    public void setComponent(ApComponent appComponent) {
        DaggerAppDetailComponent.builder().apComponent(appComponent)
                .appDetailModule(new AppDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void showAppDetail(AppInfo appInfo) {
        showScreenshot(appInfo.getScreenshot());

        UT.show("" + appInfo.getDisplayName());
    }

    private void showScreenshot(String screenshot) {

        List<String> url = Arrays.asList(screenshot.split(","));

        for(int i = 0 ; i < url.size() ; i++) {

            ImageView imageView = (ImageView) mLayoutInflater.inflate(R.layout.template_imageview, mViewGallery, false);
            ImageLoader.load(ApiServer.IMAGE_URL + url.get(i), imageView);
            mViewGallery.addView(imageView);

        }

    }

}
