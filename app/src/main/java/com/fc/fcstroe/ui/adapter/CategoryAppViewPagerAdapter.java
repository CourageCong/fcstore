package com.fc.fcstroe.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.fc.fcstroe.ui.fragment.CategoryAppFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class CategoryAppViewPagerAdapter extends FragmentStatePagerAdapter{

    private int categoryId;
    private List<String> titles = new ArrayList<>(3);

    public CategoryAppViewPagerAdapter(FragmentManager fm,int categoryId) {
        super(fm);
        this.categoryId = categoryId;

        titles.add("精品");
        titles.add("排行");
        titles.add("新品");
    }

    @Override
    public Fragment getItem(int position) {
        return new CategoryAppFragment(categoryId,position);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titles.get(position);
    }
}
