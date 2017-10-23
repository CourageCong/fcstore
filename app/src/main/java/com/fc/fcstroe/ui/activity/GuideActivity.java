package com.fc.fcstroe.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.fc.fcstroe.R;
import com.fc.fcstroe.common.util.ACache;
import com.fc.fcstroe.ui.adapter.GuideAdapter;
import com.fc.fcstroe.ui.fragment.GuideFragment;
import com.fc.fcstroe.ui.widget.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 引导页
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */
public class GuideActivity extends AppCompatActivity{

    @BindView(R.id.guide_view_pager)
    ViewPager mGuideViewPager;
    @BindView(R.id.guide_indicator)
    CircleIndicator mGuideIndicator;


    private GuideAdapter mGuideAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {

        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(GuideFragment.getInstance(R.mipmap.guide_1, R.color.color_bg_guide1, R.string.guide_1, false));
        fragments.add(GuideFragment.getInstance(R.mipmap.guide_2, R.color.color_bg_guide2, R.string.guide_2, false));
        fragments.add(GuideFragment.getInstance(R.mipmap.guide_3, R.color.color_bg_guide3, R.string.guide_3, true));

        mGuideViewPager.setCurrentItem(0);

        mGuideAdapter = new GuideAdapter(getSupportFragmentManager());

        mGuideAdapter.setFragments(fragments);

        mGuideViewPager.setOffscreenPageLimit(mGuideAdapter.getCount());

        mGuideViewPager.setAdapter(mGuideAdapter);

        mGuideIndicator.setViewPager(mGuideViewPager);

    }



}
