package com.fc.fcstroe.ui.fragment;


import android.annotation.SuppressLint;

import com.fc.fcstroe.dagger.component.ApComponent;
import com.fc.fcstroe.dagger.component.DaggerAppInfoComponent;
import com.fc.fcstroe.dagger.module.AppInfoModule;
import com.fc.fcstroe.presenter.AppInfoPresenter;
import com.fc.fcstroe.ui.adapter.AppInfoAdapter;
import com.fc.fcstroe.ui.fragment.super_fragment.BaseAppInfoFragment;


@SuppressLint("ValidFragment")
public class CategoryAppFragment extends BaseAppInfoFragment {

    private int categoryId;
    private int mFlagType;

    public CategoryAppFragment(int categoryId, int flagType){

        this.categoryId = categoryId;
        this.mFlagType = flagType;

    }


    @Override
    public void init() {

        presenter.requestData(setType(),categoryId,page,mFlagType);
        initRecyclerView();

    }

    @Override
    public void onEmptyViewClick() {
        presenter.requestData(setType(), page,categoryId,mFlagType);
    }

    @Override
    public void onLoadMoreRequested() {
        presenter.requestData(setType(), page,categoryId,mFlagType);
    }

    @Override
    public void setComponent(ApComponent appComponent) {
        DaggerAppInfoComponent.builder().apComponent(appComponent).appInfoModule(new AppInfoModule(this))
                .build().inject(this);
    }

    @Override
    public int setType() {
        return AppInfoPresenter.CATEGORY;
    }


    @Override
    public AppInfoAdapter buildAdapter() {
        return  AppInfoAdapter.builder().showPosition(false).showBrief(true).showCategoryName(false).build();
    }

}
