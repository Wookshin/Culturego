package com.example.wooks.myremoteweb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.ViewFlipper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Wooks on 2017-07-05.
 */

public class SelectActivity extends AppCompatActivity {
    Button btn1, btn2;
    String urlAddr = "http://13.124.85.66:2300/";
    Intent intent;
    String temp;
    JSONObject parent;
    JSONObject child;
    ImageView museumView, galleryView;
    ArrayList<ImageView> imageViews;
    ViewFlipper VF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        imageViews = new ArrayList<ImageView>();
        imageViews.add((ImageView)findViewById(R.id.image_question1));
        imageViews.add((ImageView)findViewById(R.id.image_question2));
        imageViews.add((ImageView)findViewById(R.id.image_question3));
        imageViews.add((ImageView)findViewById(R.id.image_question4));
        imageViews.add((ImageView)findViewById(R.id.image_question5));
        imageViews.add((ImageView)findViewById(R.id.image_question6));
        imageViews.add((ImageView)findViewById(R.id.image_question7));
        imageViews.add((ImageView)findViewById(R.id.image_question8));
        imageViews.add((ImageView)findViewById(R.id.image_question9));
        imageViews.add((ImageView)findViewById(R.id.image_question10));
        imageViews.add((ImageView)findViewById(R.id.image_question11));
        imageViews.add((ImageView)findViewById(R.id.image_question12));
        btn1 = (Button)findViewById(R.id.cancel);
        btn2 = (Button)findViewById(R.id.ok);
        intent = new Intent(this, MainActivity.class);
        parent = new JSONObject();
        child = new JSONObject();
        museumView = (ImageView) findViewById(R.id.select_museum);
        galleryView = (ImageView)findViewById(R.id.select_gallery);
        VF = (ViewFlipper) findViewById(R.id.viewFlipper);

        imageViews.get(0).setImageResource(R.drawable.question_1);
        imageViews.get(1).setImageResource(R.drawable.question_2);
        imageViews.get(2).setImageResource(R.drawable.question_3);
        imageViews.get(3).setImageResource(R.drawable.question_4);
        imageViews.get(4).setImageResource(R.drawable.question_5);
        imageViews.get(5).setImageResource(R.drawable.question_6);
        imageViews.get(6).setImageResource(R.drawable.question_7);
        imageViews.get(7).setImageResource(R.drawable.question_8);
        imageViews.get(8).setImageResource(R.drawable.question_9);
        imageViews.get(9).setImageResource(R.drawable.question_10);
        imageViews.get(10).setImageResource(R.drawable.question_11);
        imageViews.get(11).setImageResource(R.drawable.question_12);
        museumView.setImageResource(R.drawable.select_museum);
        galleryView.setImageResource(R.drawable.select_gallery);


        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                urlAddr = urlAddr + "select";
                try {
                    parent.put("sinabro",child);
                    Log.d("output", parent.toString(2));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                NetWorkAsyncTaskSelect netWorkAsyncTask = new NetWorkAsyncTaskSelect(SelectActivity.this, urlAddr);
                netWorkAsyncTask.execute(parent);
                urlAddr = "http://13.124.85.66:2300/";
                startActivity(intent);
            }
        });

        museumView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VF.setDisplayedChild(0);
            }
        });
        galleryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VF.setDisplayedChild(1);
            }
        });
    }

    @Override
    protected void onDestroy() {
        //android.os.Process.killProcess(android.os.Process.myPid());
        super.onDestroy();
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_1_1:
                if (checked)
                    try {
                        child.put("question1", 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_1_2:
                if (checked)
                    try {
                        child.put("question1", 2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            case R.id.radio_1_3:
                if (checked)
                    try {
                        child.put("question1", 3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            case R.id.radio_1_4:
                if (checked)
                    try {
                        child.put("question1", 4);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            case R.id.radio_1_5:
                if (checked)
                    try {
                        child.put("question1", 5);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            case R.id.radio_2_1:
                if (checked)
                    try {
                        child.put("question2", 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            case R.id.radio_2_2:
                if (checked)
                    try {
                        child.put("question2", 2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            case R.id.radio_2_3:
                if (checked)
                    try {
                        child.put("question2", 3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            case R.id.radio_2_4:
                if (checked)
                    try {
                        child.put("question2", 4);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            case R.id.radio_2_5:
                if (checked)
                    try {
                        child.put("question2", 5);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            case R.id.radio_3_1:
                if (checked)
                    try {
                        child.put("question3", 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            case R.id.radio_3_2:
                if (checked)
                    try {
                        child.put("question3", 2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            case R.id.radio_3_3:
                if (checked)
                    try {
                        child.put("question3", 3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            case R.id.radio_3_4:
                if (checked)
                    try {
                        child.put("question3", 4);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            case R.id.radio_3_5:
                if (checked)
                    try {
                        child.put("question3", 5);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            case R.id.radio_4_1:
                if (checked)
                    try {
                        child.put("question4", 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            case R.id.radio_4_2:
                if (checked)
                    try {
                        child.put("question4", 2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            case R.id.radio_4_3:
                if (checked)
                    try {
                        child.put("question4", 3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            case R.id.radio_4_4:
                if (checked)
                    try {
                        child.put("question4", 4);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            case R.id.radio_4_5:
                if (checked)
                    try {
                        child.put("question4", 5);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            case R.id.radio_5_1:
                if (checked)
                    try {
                        child.put("question5", 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            case R.id.radio_5_2:
                if (checked)
                    try {
                        child.put("question5", 2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            case R.id.radio_5_3:
                if (checked)
                    try {
                        child.put("question5", 3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            case R.id.radio_5_4:
                if (checked)
                    try {
                        child.put("question5", 4);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            case R.id.radio_5_5:
                if (checked)
                    try {
                        child.put("question5", 5);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            case R.id.radio_6_1:
                if (checked)
                    try {
                        child.put("question6", 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_6_2:
                if (checked)
                    try {
                        child.put("question6", 2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_6_3:
                if (checked)
                    try {
                        child.put("question6", 3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_6_4:
                if (checked)
                    try {
                        child.put("question6", 4);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_6_5:
                if (checked)
                    try {
                        child.put("question6", 5);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_7_1:
                if (checked)
                    try {
                        child.put("question7", 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_7_2:
                if (checked)
                    try {
                        child.put("question7", 2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_7_3:
                if (checked)
                    try {
                        child.put("question7", 3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_7_4:
                if (checked)
                    try {
                        child.put("question7", 4);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_7_5:
                if (checked)
                    try {
                        child.put("question7", 5);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_8_1:
                if (checked)
                    try {
                        child.put("question8", 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_8_2:
                if (checked)
                    try {
                        child.put("question8", 2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_8_3:
                if (checked)
                    try {
                        child.put("question8", 3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_8_4:
                if (checked)
                    try {
                        child.put("question8", 4);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_8_5:
                if (checked)
                    try {
                        child.put("question8", 5);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_9_1:
                if (checked)
                    try {
                        child.put("question9", 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_9_2:
                if (checked)
                    try {
                        child.put("question9", 2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_9_3:
                if (checked)
                    try {
                        child.put("question9", 3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_9_4:
                if (checked)
                    try {
                        child.put("question9", 4);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_9_5:
                if (checked)
                    try {
                        child.put("question9", 5);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_10_1:
                if (checked)
                    try {
                        child.put("question10", 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_10_2:
                if (checked)
                    try {
                        child.put("question10", 2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_10_3:
                if (checked)
                    try {
                        child.put("question10", 3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_10_4:
                if (checked)
                    try {
                        child.put("question10", 4);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_10_5:
                if (checked)
                    try {
                        child.put("question10", 5);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_11_1:
                if (checked)
                    try {
                        child.put("question11", 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_11_2:
                if (checked)
                    try {
                        child.put("question11", 2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_11_3:
                if (checked)
                    try {
                        child.put("question11", 3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_11_4:
                if (checked)
                    try {
                        child.put("question11", 4);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_11_5:
                if (checked)
                    try {
                        child.put("question11", 5);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_12_1:
                if (checked)
                    try {
                        child.put("question12", 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_12_2:
                if (checked)
                    try {
                        child.put("question12", 2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_12_3:
                if (checked)
                    try {
                        child.put("question12", 3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_12_4:
                if (checked)
                    try {
                        child.put("question12", 4);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_12_5:
                if (checked)
                    try {
                        child.put("question12", 5);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_21_1:
                if (checked)
                    try {
                        child.put("question1", 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_21_2:
                if (checked)
                    try {
                        child.put("question1", 2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_21_3:
                if (checked)
                    try {
                        child.put("question1", 3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_21_4:
                if (checked)
                    try {
                        child.put("question1", 4);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_21_5:
                if (checked)
                    try {
                        child.put("question1", 5);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_22_1:
                if (checked)
                    try {
                        child.put("question2", 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_22_2:
                if (checked)
                    try {
                        child.put("question2", 2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_22_3:
                if (checked)
                    try {
                        child.put("question2", 3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_22_4:
                if (checked)
                    try {
                        child.put("question2", 4);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_22_5:
                if (checked)
                    try {
                        child.put("question2", 5);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_23_1:
                if (checked)
                    try {
                        child.put("question3", 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_23_2:
                if (checked)
                    try {
                        child.put("question3", 2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_23_3:
                if (checked)
                    try {
                        child.put("question3", 3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_23_4:
                if (checked)
                    try {
                        child.put("question3", 4);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_23_5:
                if (checked)
                    try {
                        child.put("question3", 5);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_24_1:
                if (checked)
                    try {
                        child.put("question4", 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_24_2:
                if (checked)
                    try {
                        child.put("question4", 2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_24_3:
                if (checked)
                    try {
                        child.put("question4", 3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_24_4:
                if (checked)
                    try {
                        child.put("question4", 4);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_24_5:
                if (checked)
                    try {
                        child.put("question4", 5);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_25_1:
                if (checked)
                    try {
                        child.put("question5", 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_25_2:
                if (checked)
                    try {
                        child.put("question5", 2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_25_3:
                if (checked)
                    try {
                        child.put("question5", 3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_25_4:
                if (checked)
                    try {
                        child.put("question5", 4);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_25_5:
                if (checked)
                    try {
                        child.put("question5", 5);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_26_1:
                if (checked)
                    try {
                        child.put("question6", 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_26_2:
                if (checked)
                    try {
                        child.put("question6", 2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_26_3:
                if (checked)
                    try {
                        child.put("question6", 3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_26_4:
                if (checked)
                    try {
                        child.put("question6", 4);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_26_5:
                if (checked)
                    try {
                        child.put("question6", 5);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_27_1:
                if (checked)
                    try {
                        child.put("question7", 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_27_2:
                if (checked)
                    try {
                        child.put("question7", 2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_27_3:
                if (checked)
                    try {
                        child.put("question7", 3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_27_4:
                if (checked)
                    try {
                        child.put("question7", 4);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_27_5:
                if (checked)
                    try {
                        child.put("question7", 5);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_28_1:
                if (checked)
                    try {
                        child.put("question8", 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_28_2:
                if (checked)
                    try {
                        child.put("question8", 2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_28_3:
                if (checked)
                    try {
                        child.put("question8", 3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_28_4:
                if (checked)
                    try {
                        child.put("question8", 4);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_28_5:
                if (checked)
                    try {
                        child.put("question8", 5);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_29_1:
                if (checked)
                    try {
                        child.put("question9", 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_29_2:
                if (checked)
                    try {
                        child.put("question9", 2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_29_3:
                if (checked)
                    try {
                        child.put("question9", 3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_29_4:
                if (checked)
                    try {
                        child.put("question9", 4);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_29_5:
                if (checked)
                    try {
                        child.put("question9", 5);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_30_1:
                if (checked)
                    try {
                        child.put("question10", 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_30_2:
                if (checked)
                    try {
                        child.put("question10", 2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_30_3:
                if (checked)
                    try {
                        child.put("question10", 3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_30_4:
                if (checked)
                    try {
                        child.put("question10", 4);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_30_5:
                if (checked)
                    try {
                        child.put("question10", 5);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_31_1:
                if (checked)
                    try {
                        child.put("question11", 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_31_2:
                if (checked)
                    try {
                        child.put("question11", 2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_31_3:
                if (checked)
                    try {
                        child.put("question11", 3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_31_4:
                if (checked)
                    try {
                        child.put("question11", 4);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_31_5:
                if (checked)
                    try {
                        child.put("question11", 5);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_32_1:
                if (checked)
                    try {
                        child.put("question12", 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_32_2:
                if (checked)
                    try {
                        child.put("question12", 2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_32_3:
                if (checked)
                    try {
                        child.put("question12", 3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_32_4:
                if (checked)
                    try {
                        child.put("question12", 4);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
            case R.id.radio_32_5:
                if (checked)
                    try {
                        child.put("question12", 5);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                break;
        }
    }
}

