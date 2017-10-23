package com.fc.fcstroe.dagger.component;

import com.fc.fcstroe.dagger.annotation.FragmentScope;
import com.fc.fcstroe.dagger.module.AppInfoModule;
import com.fc.fcstroe.ui.fragment.CategoryAppFragment;
import com.fc.fcstroe.ui.fragment.GameFragment;
import com.fc.fcstroe.ui.fragment.RankingFragment;
import com.fc.fcstroe.ui.fragment.RecommendFragment;
import com.fc.fcstroe.ui.fragment.super_fragment.BaseAppInfoFragment;

import dagger.Component;

/**
 * 对应RankingFragment
 *
 * @author fucong
 * @version 1.0.0
 * @see {@link RecommendFragment}
 */

@FragmentScope
@Component(modules = {AppInfoModule.class},dependencies = ApComponent.class)
public interface AppInfoComponent {

    void inject(RankingFragment fragment);
    void inject(GameFragment fragment);
    void inject(CategoryAppFragment fragment);
}
