package com.example.wooks.myremoteweb;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import static com.example.wooks.myremoteweb.Place1Activity.bitmapArray;

/**
 * Created by Wooks on 2017-07-11.
 */

public class CustomPagerAdapter extends PagerAdapter {

    private Context mContext;
    public CustomPagerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        LinearLayout v0 = (LinearLayout) inflater.inflate (R.layout.viewpager_pic1, null);
        ImageView imageView = (ImageView) v0.findViewById(R.id.image_view1);
        int padding = 0;
        imageView.setPadding(padding, padding, padding, padding);
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
        imageView.setImageBitmap(bitmapArray.get(position));
        collection.addView(v0, 0);
        return v0;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
        return mContext.getString(customPagerEnum.getTitleResId());
    }

}