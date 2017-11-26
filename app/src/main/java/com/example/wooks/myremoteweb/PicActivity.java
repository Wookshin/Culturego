package com.example.wooks.myremoteweb;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by Wooks on 2017-07-06.
 */

public class PicActivity extends AppCompatActivity {
    ImageView titleView;
    private static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        PicActivity.context = getApplicationContext();

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CustomPagerAdapter(this));

        titleView = (ImageView)findViewById(R.id.title_view);
        titleView.setImageResource(R.drawable.title);
    }

    public static Context getPicContext(){
        return PicActivity.context;
    }
}
