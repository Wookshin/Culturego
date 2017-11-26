package com.example.wooks.myremoteweb;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static com.example.wooks.myremoteweb.MapAcitivity.results;

/**
 * Created by Wooks on 2017-07-06.
 */

public class InfoActivity extends AppCompatActivity {
    JSONArray jsonarray = null;
    JSONObject jsonobject;
    String encodedImage;
    ImageView titleView;
    ImageView infoView;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        try {
            jsonarray = new JSONArray(results);
            jsonobject = jsonarray.getJSONObject(jsonarray.length()-10);
            encodedImage = String.valueOf(jsonobject.getString("museum_1_2"));
            Log.e("images", encodedImage);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        InputStream stream = new ByteArrayInputStream(Base64.decode(encodedImage, Base64.DEFAULT));
        bitmap = BitmapFactory.decodeStream(stream);

        titleView = (ImageView)findViewById(R.id.title_view);
        infoView = (ImageView)findViewById(R.id.info_view);
        titleView.setImageResource(R.drawable.title);
        infoView.setImageBitmap(bitmap);
    }
    @Override
    protected void onDestroy() {
        //android.os.Process.killProcess(android.os.Process.myPid());

        super.onDestroy();
        if(bitmap!=null)
        {
            bitmap.recycle();
            bitmap=null;
        }
    }
}
