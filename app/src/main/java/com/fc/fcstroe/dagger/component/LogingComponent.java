package com.fc.fcstroe.dagger.component;

import com.fc.fcstroe.dagger.annotation.FragmentScope;
import com.fc.fcstroe.dagger.module.LoginModule;
import com.fc.fcstroe.ui.activity.LoginActivity;

import dagger.Component;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

@FragmentScope
@Component(modules = LoginModule.class,dependencies = ApComponent.class)
public interface LogingComponent {

    void inject(LoginActivity activity);

}
