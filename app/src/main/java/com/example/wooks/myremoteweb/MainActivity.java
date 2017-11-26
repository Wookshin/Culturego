package com.example.wooks.myremoteweb;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    String urlAddr = "http://13.124.85.66:2300/";
    Intent intent, intent2;
    static LatLon temp = new LatLon(37.602910, 126.955145);
    Button btn1;
    ToggleButton tb1;
    ImageView list1, list2;
    private Handler mHandler;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button)findViewById(R.id.button1);
        tb1 = (ToggleButton)findViewById(R.id.tb1);
        list1 = (ImageView)findViewById(R.id.list1);
        list2 = (ImageView)findViewById(R.id.list2);
        intent = new Intent(this, MapAcitivity.class);
        intent2 = new Intent(this, SelectActivity.class);
        mHandler = new Handler();

        list1.setImageResource(R.drawable.museum);
        list2.setImageResource(R.drawable.gallery);

        list1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your code here
                urlAddr = urlAddr + "museum";
                Log.d("urlAddr",urlAddr);
                NetWorkAsyncTask netWorkAsyncTask = new NetWorkAsyncTask(MainActivity.this, urlAddr);
                netWorkAsyncTask.execute(temp);
                urlAddr = "http://13.124.85.66:2300/";
                startActivity(intent);
            }
        });

        list2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your code here
                urlAddr = urlAddr + "art";
                Log.d("urlAddr",urlAddr);
                NetWorkAsyncTask netWorkAsyncTask = new NetWorkAsyncTask(MainActivity.this, urlAddr);
                netWorkAsyncTask.execute(temp);
                urlAddr = "http://13.124.85.66:2300/";
                startActivity(intent);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your code here
                startActivity(intent2);
            }
        });

        // LocationManager 객체를 얻어온다
        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        tb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tb1.isChecked()) {
                    try {
                        // GPS 제공자의 정보가 바뀌면 콜백하도록 리스너 등록하기~!!!
                        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, // 등록할 위치제공자
                                100, // 통지사이의 최소 시간간격 (miliSecond)
                                1, // 통지사이의 최소 변경거리 (m)
                                mLocationListener);
                        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, // 등록할 위치제공자
                                100, // 통지사이의 최소 시간간격 (miliSecond)
                                1, // 통지사이의 최소 변경거리 (m)
                                mLocationListener);
                        runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                mProgressDialog = ProgressDialog.show(MainActivity.this,"",
                                        "GPS를 받아오고 있습니다. 잠시만 기다려 주세요.",true);
                                mHandler.postDelayed( new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            if (mProgressDialog!=null&&mProgressDialog.isShowing()){
                                                mProgressDialog.dismiss();
                                            }
                                        }
                                        catch ( Exception e ) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, 5000);
                            }
                        } );
                    }catch (SecurityException ex) {}
                }
                else {
                    try {
                        lm.removeUpdates(mLocationListener);  //  미수신할때는 반드시 자원해체를 해주어야 한다.
                    }catch (SecurityException ex) {}
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        //android.os.Process.killProcess(android.os.Process.myPid());
        super.onDestroy();
    }

    private final LocationListener mLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            //여기서 위치값이 갱신되면 이벤트가 발생한다.
            //값은 Location 형태로 리턴되며 좌표 출력 방법은 다음과 같다.
            Log.d("test", "onLocationChanged, location:" + location);
            double longitude = location.getLongitude(); //경도
            double latitude = location.getLatitude();   //위도
            double altitude = location.getAltitude();   //고도
            float accuracy = location.getAccuracy();    //정확도
            String provider = location.getProvider();   //위치제공자
            //Gps 위치제공자에 의한 위치변화. 오차범위가 좁다.
            //Network 위치제공자에 의한 위치변화
            //Network 위치는 Gps에 비해 정확도가 많이 떨어진다.
            temp.setLat(latitude);
            temp.setLon(longitude);
        }
        public void onProviderDisabled(String provider) {
            // Disabled시
            Log.d("test", "onProviderDisabled, provider:" + provider);
        }

        public void onProviderEnabled(String provider) {
            // Enabled시
            Log.d("test", "onProviderEnabled, provider:" + provider);
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            // 변경시
            Log.d("test", "onStatusChanged, provider:" + provider + ", status:" + status + " ,Bundle:" + extras);
        }
    };
}
