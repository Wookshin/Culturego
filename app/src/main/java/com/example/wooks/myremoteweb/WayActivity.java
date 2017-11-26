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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.example.wooks.myremoteweb.MapAcitivity.results;

/**
 * Created by Wooks on 2017-07-06.
 */

public class WayActivity extends AppCompatActivity {
    JSONArray jsonarray = null;
    JSONObject jsonobject;
    String encodedImage;
    ImageView titleView;
    ImageView wayView;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_way);

        try {
            jsonarray = new JSONArray(results);
            jsonobject = jsonarray.getJSONObject(jsonarray.length()-9);
            encodedImage = String.valueOf(jsonobject.getString("museum_1_3"));
            Log.e("images", encodedImage);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        InputStream stream = new ByteArrayInputStream(Base64.decode(encodedImage, Base64.DEFAULT));
        bitmap = BitmapFactory.decodeStream(stream);

        titleView = (ImageView)findViewById(R.id.title_view);
        wayView = (ImageView)findViewById(R.id.way_view);
        titleView.setImageResource(R.drawable.title);
        wayView.setImageBitmap(bitmap);
    }

    private Bitmap decodeFile(File f) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            // The new size we want to scale to
            final int REQUIRED_SIZE=70;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while(o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {}
        return null;
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


