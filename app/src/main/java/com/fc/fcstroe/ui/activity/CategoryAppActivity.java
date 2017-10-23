package com.fc.fcstroe.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.fc.fcstroe.R;
import com.fc.fcstroe.common.constant.Constant;
import com.fc.fcstroe.dagger.component.ApComponent;
import com.fc.fcstroe.data.bean.Category;
import com.fc.fcstroe.ui.adapter.CategoryAppViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.category;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class CategoryAppActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.activity_cateogry_app)
    LinearLayout mActivityCateogryApp;


    private Category mCategory;

    @Override
    public int setLayoutId() {
        return R.layout.activity_cateogry_app;
    }

    @Override
    public void setComponent(ApComponent component) {

    }

    @Override
    public void init() {
        getData();
        initViewPager();
    }

    private void initViewPager() {
        mToolBar.setTitle(mCategory.getName());

        // TODO: 2017-10-11 返回按钮
        /*mToolBar.setNavigationIcon(
                new IconicsDrawable(this)
                        .icon(Ionicons.Icon.ion_ios_arrow_back)
                        .sizeDp(16)
                        .color(getResources().getColor(R.color.md_white_1000)
                        )
        );*/

        /*mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/

        CategoryAppViewPagerAdapter adapter = new CategoryAppViewPagerAdapter(getSupportFragmentManager(), mCategory.getId());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(adapter);

        mTabLayout.setupWithViewPager(mViewPager);

    }

    public void getData() {

        mCategory = (Category) getIntent().getSerializableExtra(Constant.CATEGORY);

    }

}
