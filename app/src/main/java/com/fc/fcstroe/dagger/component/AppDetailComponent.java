package com.fc.fcstroe.dagger.component;

import com.fc.fcstroe.dagger.annotation.FragmentScope;
import com.fc.fcstroe.dagger.module.AppDetailModule;
import com.fc.fcstroe.ui.activity.AppDetailActivity;
import com.fc.fcstroe.ui.fragment.AppDetailFragment;

import dagger.Component;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */
@FragmentScope
@Component(modules = AppDetailModule.class,dependencies = ApComponent.class)
public interface AppDetailComponent {

    void inject(AppDetailFragment activity);

}
