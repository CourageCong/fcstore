package com.fc.fcstroe.presenter.contract;

import com.fc.fcstroe.data.bean.BaseBean;
import com.fc.fcstroe.data.bean.Category;
import com.fc.fcstroe.ui.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public interface CategoryContract {
    public interface  ICagegoryModel{


        Observable<BaseBean<List<Category>>> getCategories();

    }


    public interface  CategoryView extends BaseView {



        public void showData(List<Category> categories);


    }
}
