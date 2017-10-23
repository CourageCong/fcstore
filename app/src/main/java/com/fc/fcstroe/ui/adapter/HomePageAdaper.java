package com.fc.fcstroe.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.fc.fcstroe.data.bean.FragmentInfo;
import com.fc.fcstroe.ui.fragment.CategroyFragment;
import com.fc.fcstroe.ui.fragment.GameFragment;
import com.fc.fcstroe.ui.fragment.RankingFragment;
import com.fc.fcstroe.ui.fragment.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class HomePageAdaper extends FragmentPagerAdapter {

    private List<FragmentInfo> mFragments = new ArrayList<FragmentInfo>();


    public HomePageAdaper(FragmentManager fm) {
        super(fm);
        initFragments();
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        try {
            fragment= (Fragment) mFragments.get(position).getFragment().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fragment;
    }

    private void initFragments(){
        mFragments.add(new FragmentInfo("推荐",RecommendFragment.class));
        mFragments.add(new FragmentInfo("排行",RankingFragment.class));
        mFragments.add(new FragmentInfo("游戏",GameFragment.class));
        mFragments.add(new FragmentInfo("分类",CategroyFragment.class));

    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }

}
