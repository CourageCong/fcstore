package com.fc.fcstroe.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fc.fcstroe.R;
import com.fc.fcstroe.common.constant.Constant;
import com.fc.fcstroe.dagger.component.ApComponent;
import com.fc.fcstroe.dagger.component.DaggerCategoryComponent;
import com.fc.fcstroe.dagger.module.CategoryModule;
import com.fc.fcstroe.data.bean.Category;
import com.fc.fcstroe.presenter.CategoryPresenter;
import com.fc.fcstroe.presenter.contract.CategoryContract;
import com.fc.fcstroe.ui.activity.CategoryAppActivity;
import com.fc.fcstroe.ui.adapter.CategoryAdapter;
import com.fc.fcstroe.ui.fragment.super_fragment.ProgressFragment;

import java.util.List;

import butterknife.BindView;

/**
 * 分类fragment
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class CategroyFragment extends ProgressFragment<CategoryPresenter> implements CategoryContract.CategoryView {


    @BindView(R.id.recyleAppInfo)
    RecyclerView mRecycleView;

    private CategoryAdapter mAdapter;


    private void initRecyclerView() {


        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new CategoryAdapter();

        mRecycleView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), CategoryAppActivity.class);

                intent.putExtra(Constant.CATEGORY, mAdapter.getData().get(position));

                startActivity(intent);
            }
        });


    }


    @Override
    public void onEmptyViewClick() {
        presenter.getAllCategory();
    }

    @Override
    public void init() {
        initRecyclerView();

        presenter.getAllCategory();
    }

    @Override
    public int setLayoutId() {
        return R.layout.template_recyclerview;
    }

    @Override
    public void setComponent(ApComponent appComponent) {
//        DaggerCategoryComponent.builder().appComponent(appComponent).categoryModule(new CategoryModule(this))
//                .build().inject(this);
        DaggerCategoryComponent.builder().apComponent(appComponent)
                .categoryModule(new CategoryModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void showData(List<Category> categories) {
        mAdapter.addData(categories);
    }
}
