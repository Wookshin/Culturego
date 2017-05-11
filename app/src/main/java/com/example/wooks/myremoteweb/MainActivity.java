package com.example.wooks.myremoteweb;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity {
    String urlAddr = "http://192.168.0.11:2323/";
    static GoogleMap googleMap;
    String results;
    EditText editText;
    LatLng first;
    LatLng second;
    Intent intent;
    static LatLon temp = new LatLon(37.602910, 126.955145);
    TextView tv;
    ToggleButton tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText1);
        tv = (TextView) findViewById(R.id.textView2);
        tv.setText("위치정보 미수신중");
        tb = (ToggleButton)findViewById(R.id.toggle1);
        intent = new Intent(this, MapAcitivity.class);
        ListView listview ;
        ListViewAdaptor adapter;
        // Adapter 생성
        adapter = new ListViewAdaptor() ;
        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        // 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.museum),
                "Museum", "문화활동은 역시 박물관에서!") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.art),
                "Art", "미술의 세계로 떠나볼까요?") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.park),
                "Park", "공원에서 여유를 만끽!") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.spa),
                "Spa", "스파에서 스트레스를 스팍!") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.movie),
                "Movie", "심심할땐 역시 영화지!") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.bar),
                "Bar", "모히또에서 몰디브 한잔?") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.cafe),
                "Cafe", "커피 한잔의 여유!") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.amusement),
                "Amusement", "떠나자! 에버~~랜드!") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.zoo),
                "Zoo", "동물 안 좋아하는 사람 있나요?") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.aquarium),
                "Aquarium", "신비롭고 놀라운 물 속의 세계!") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.shopping),
                "Shopping", "월급 받으면 뭐?? 쇼핑!!!!") ;

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;
                String titleStr = item.getTitle() ;
                String descStr = item.getDesc() ;
                Drawable iconDrawable = item.getIcon() ;

                // TODO : use item data.
                urlAddr = editText.getText().toString();
                urlAddr = urlAddr + titleStr;
                Log.d("urlAddr",urlAddr);
                NetWorkAsyncTask netWorkAsyncTask = new NetWorkAsyncTask(MainActivity.this, urlAddr);
                netWorkAsyncTask.execute(temp);
                urlAddr = editText.getText().toString();
                startActivity(intent);
            }
        }) ;

        // LocationManager 객체를 얻어온다
        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(tb.isChecked()){
                        tv.setText("수신중..");
                        // GPS 제공자의 정보가 바뀌면 콜백하도록 리스너 등록하기~!!!
                        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, // 등록할 위치제공자
                                100, // 통지사이의 최소 시간간격 (miliSecond)
                                1, // 통지사이의 최소 변경거리 (m)
                                mLocationListener);
                        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, // 등록할 위치제공자
                                100, // 통지사이의 최소 시간간격 (miliSecond)
                                1, // 통지사이의 최소 변경거리 (m)
                                mLocationListener);
                    }else{
                        tv.setText("위치정보 확인완료!");
                        lm.removeUpdates(mLocationListener);  //  미수신할때는 반드시 자원해체를 해주어야 한다.
                    }
                }catch(SecurityException ex){
                }
            }
        });
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
            tv.setText("위치정보 확인완료!\n위도 : " + longitude + "\n경도 : " + latitude+"");
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
