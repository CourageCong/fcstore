package com.fc.fcstroe.dagger.component;

import com.fc.fcstroe.dagger.annotation.FragmentScope;
import com.fc.fcstroe.dagger.module.RecommendModule;
import com.fc.fcstroe.ui.fragment.RecommendFragment;

import dagger.Component;

/**
 * 对应RecommendFragment
 *
 * @author fucong
 * @version 1.0.0
 * @see {@link com.fc.fcstroe.ui.fragment.RecommendFragment}
 */

@FragmentScope
@Component(modules = {RecommendModule.class},dependencies = ApComponent.class)
public interface RecommendCoponent {

    void inject(RecommendFragment fragment);
}
