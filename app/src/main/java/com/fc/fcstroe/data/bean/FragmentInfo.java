package com.fc.fcstroe.data.bean;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class FragmentInfo {

    private  String title;

    private Class fragment;

    public FragmentInfo(String title, Class fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class getFragment() {
        return fragment;
    }

    public void setFragment(Class fragment) {
        this.fragment = fragment;
    }

}
