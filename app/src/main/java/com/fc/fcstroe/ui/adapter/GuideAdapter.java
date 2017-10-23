package com.fc.fcstroe.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class GuideAdapter extends FragmentPagerAdapter{


    private List<Fragment> frags;

    public GuideAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragments(List<Fragment> frag){

        if (frag == null) {
            frags = new ArrayList<Fragment>();

        }
        frags = frag;

    }

    @Override
    public Fragment getItem(int position) {
        return frags.get(position);
    }

    @Override
    public int getCount() {
        return frags.size();
    }
}
