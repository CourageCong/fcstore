package com.fc.fcstroe.data.model;

import com.fc.fcstroe.data.bean.BaseBean;
import com.fc.fcstroe.data.bean.Category;
import com.fc.fcstroe.data.net.ApiServer;
import com.fc.fcstroe.presenter.contract.CategoryContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class CategoryModel implements CategoryContract.ICagegoryModel{
    private ApiServer mApiService;

    public CategoryModel(ApiServer apiService){
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<List<Category>>> getCategories() {
        return mApiService.getCategories();
    }
}
