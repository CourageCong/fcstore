package com.fc.fcstroe.ui.fragment;

import com.fc.fcstroe.dagger.component.ApComponent;
import com.fc.fcstroe.dagger.component.DaggerAppInfoComponent;
import com.fc.fcstroe.dagger.module.AppInfoModule;
import com.fc.fcstroe.presenter.AppInfoPresenter;
import com.fc.fcstroe.ui.adapter.AppInfoAdapter;
import com.fc.fcstroe.ui.fragment.super_fragment.BaseAppInfoFragment;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class RankingFragment extends BaseAppInfoFragment {




    @Override
    public void setComponent(ApComponent appComponent) {
        DaggerAppInfoComponent.builder()
                .apComponent(appComponent)
                .appInfoModule(new AppInfoModule(this))
                .build()
                .inject(this);

    }




    @Override
    public int setType() {
        return AppInfoPresenter.FirstPage;
    }

    @Override
    public AppInfoAdapter buildAdapter() {
        return   AppInfoAdapter.builder().showBrief(false)
                .showCategoryName(true)
                .showPosition(true)
                .build();
    }
}
