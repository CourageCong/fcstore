package com.fc.fcstroe.dagger.module;

import com.fc.fcstroe.data.model.CategoryModel;
import com.fc.fcstroe.data.net.ApiServer;
import com.fc.fcstroe.presenter.CategoryPresenter;
import com.fc.fcstroe.presenter.contract.CategoryContract.*;

import dagger.Module;
import dagger.Provides;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

@Module
public class CategoryModule {

    private CategoryView mView;

    public CategoryModule(CategoryView mView) {
        this.mView = mView;
    }

    @Provides
    public CategoryView provideCategoryView(){
        return mView;
    }

    @Provides
    public CategoryPresenter providCategoryPresenter(CategoryView mView, CategoryModel module){
        return new CategoryPresenter(mView, module);
    }

    @Provides
    public CategoryModel provideCategoryModel(ApiServer apiServer){
        return new CategoryModel(apiServer);
    }

}
