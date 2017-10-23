package com.fc.fcstroe.presenter;

import com.fc.fcstroe.common.rx.RxHttpResponseCompat;
import com.fc.fcstroe.common.rx.observer.ProgressObserver;
import com.fc.fcstroe.data.bean.Category;
import com.fc.fcstroe.presenter.contract.CategoryContract;
import com.fc.fcstroe.presenter.contract.CategoryContract.*;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class CategoryPresenter extends BasePresenter<ICagegoryModel, CategoryView> {


    public CategoryPresenter(CategoryView categoryView, ICagegoryModel iCagegoryModel) {
        super(categoryView, iCagegoryModel);
    }


    public void getAllCategory() {


        mModel.getCategories().compose(RxHttpResponseCompat.<List<Category>>compatResult())
                .subscribe(new ProgressObserver<List<Category>>(mContext, mView) {

                    @Override
                    public void onNext(@NonNull List<Category> categories) {
                        mView.showData(categories);
                    }

                    @Override
                    public boolean isShowProgress() {
                        return true;
                    }
                });
    }


}
