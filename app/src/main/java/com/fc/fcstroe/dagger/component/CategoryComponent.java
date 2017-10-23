package com.fc.fcstroe.dagger.component;

import com.fc.fcstroe.dagger.annotation.FragmentScope;
import com.fc.fcstroe.dagger.module.CategoryModule;
import com.fc.fcstroe.ui.fragment.CategroyFragment;

import dagger.Component;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */
@FragmentScope
@Component(modules = CategoryModule.class,dependencies = ApComponent.class)
public interface CategoryComponent {

    void inject(CategroyFragment fragment);
}
