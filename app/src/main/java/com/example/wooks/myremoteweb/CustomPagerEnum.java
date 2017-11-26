package com.example.wooks.myremoteweb;

/**
 * Created by Wooks on 2017-07-11.
 */

public enum CustomPagerEnum {

    RED(R.string.app_name, R.layout.viewpager_pic1),
    BLUE(R.string.app_name, R.layout.activity_info),
    ORANGE(R.string.app_name, R.layout.activity_place1);

    private int mTitleResId;
    private int mLayoutResId;

    CustomPagerEnum(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }

}