package com.example.wooks.myremoteweb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.wooks.myremoteweb.MapAcitivity.results;

/**
 * Created by Wooks on 2017-07-06.
 */

public class ReviewActivity extends AppCompatActivity {
    ImageView titleView;
    JSONArray jsonarray = null;
    JSONObject jsonobject;
    List<String> reviewList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        titleView = (ImageView)findViewById(R.id.title_view);
        titleView.setImageResource(R.drawable.title);
        try {
            jsonarray = new JSONArray(results);
            jsonobject = jsonarray.getJSONObject(jsonarray.length()-1);
            JSONArray arr = jsonobject.getJSONArray("reviews");
            for(int i=0; i < arr.length(); i++)
                reviewList.add(arr.getString(i));
            Log.e("ReviewReview", String.valueOf(reviewList));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListView listview ;
        ListViewAdaptor adapter;
        // Adapter 생성
        adapter = new ListViewAdaptor() ;
        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView)findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        // 아이템 추가.
        for(int i=0; i<reviewList.size(); i += 2) {
            adapter.addItem(reviewList.get(i), reviewList.get(i+1));
            //ContextCompat.getDrawable(this, R.drawable.art) (위에 매개변수)
        }
    }

}