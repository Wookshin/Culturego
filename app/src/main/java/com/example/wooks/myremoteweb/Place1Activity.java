package com.example.wooks.myremoteweb;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static com.example.wooks.myremoteweb.MapAcitivity.results;

/**
 * Created by Wooks on 2017-07-03.
 */

public class Place1Activity extends AppCompatActivity{
    JSONArray jsonarray = null;
    JSONObject jsonobject;
    String encodedImage;
    ImageView placeView, titleView, infoView, wayView, reviewView;
    TextView picView;
    Intent intent1, intent2, intent3, intent4;
    static ArrayList<Bitmap> bitmapArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place1);

        try {
            jsonarray = new JSONArray(results);
            jsonobject = jsonarray.getJSONObject(jsonarray.length()-11);
            encodedImage = String.valueOf(jsonobject.getString("museum_1_1"));
            Log.e("images", encodedImage);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        InputStream stream = new ByteArrayInputStream(Base64.decode(encodedImage, Base64.DEFAULT));
        Bitmap bitmap = BitmapFactory.decodeStream(stream);


        intent1 = new Intent(this, InfoActivity.class);
        intent2 = new Intent(this, WayActivity.class);
        intent3 = new Intent(this, ReviewActivity.class);
        intent4 = new Intent(this, PicActivity.class);

        placeView = (ImageView)findViewById(R.id.place_view);
        titleView = (ImageView)findViewById(R.id.title_view);
        infoView = (ImageView)findViewById(R.id.info_view);
        wayView = (ImageView)findViewById(R.id.way_view);
        reviewView = (ImageView)findViewById(R.id.review_view);
        picView = (TextView)findViewById(R.id.pic_view);

        placeView.setImageBitmap(bitmap);
        titleView.setImageResource(R.drawable.title);
        infoView.setImageResource(R.drawable.info);
        wayView.setImageResource(R.drawable.way);
        reviewView.setImageResource(R.drawable.review);
        LinearLayout layout = (LinearLayout) findViewById(R.id.linear);
        bitmapArray = new ArrayList<Bitmap>();

        for (int i = 0; i < 7; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setId(i);
            imageView.setPadding(2, 2, 2, 2);
            try {
                jsonobject = jsonarray.getJSONObject(jsonarray.length()-10+i+2);
                encodedImage = String.valueOf(jsonobject.getString("museum_1_"+(i+4)));
                Log.e("images", encodedImage);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            InputStream stream1 = new ByteArrayInputStream(Base64.decode(encodedImage, Base64.DEFAULT));
            Bitmap bitmap1 = BitmapFactory.decodeStream(stream1);
            bitmapArray.add(bitmap1);
            imageView.setImageBitmap(bitmap1);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            layout.addView(imageView);
        }

        infoView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent1);
            }
        });

        wayView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent2);
            }
        });

        reviewView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent3);
            }
        });

        picView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent4);
            }
        });

    }
    @Override
    protected void onDestroy() {
        //android.os.Process.killProcess(android.os.Process.myPid());

        super.onDestroy();
        int size = bitmapArray.size();
        for(int i=0; i<size; i++) {
            if(bitmapArray.get(0)!=null)
            {
                bitmapArray.get(0).recycle();
                bitmapArray.remove(0);
                Log.e("Test","Test");
            }
        }
    }
}