package com.example.wooks.myremoteweb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.wooks.myremoteweb.MainActivity.temp;
/**
 * Created by Wooks on 2017-04-03.
 */

public class MapAcitivity extends AppCompatActivity implements OnMapReadyCallback, OnMarkerClickListener {
    static GoogleMap googleMap;
    static String results;
    private Button btn;
    LatLng selectLatlng;
    Marker selectMarker, tempMarker;
    Intent intent;
    ImageView titleView;
    private Handler mHandler;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_map);

        MapFragment mapFragment =  (MapFragment) getFragmentManager().findFragmentById(R.id.fm_map_01);
        mapFragment.getMapAsync(this);
        //ViewGroup.LayoutParams params = mapFragment.getView().getLayoutParams();
        //params.height = 1700;
        //mapFragment.getView().setLayoutParams(params);

        intent = new Intent(this, Place1Activity.class);
        titleView = (ImageView)findViewById(R.id.title_view);
        titleView.setImageResource(R.drawable.title);
        mHandler = new Handler();

        btn = (Button)findViewById(R.id.toggle2);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your code here
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        mProgressDialog = ProgressDialog.show(MapAcitivity.this,"",
                                "맵 정보를 받아오고 있습니다. 잠시만 기다려 주세요.",true);
                        mHandler.postDelayed(new Runnable() {
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
                try {
                    JSONArray jsonarray = null;
                    Log.d("resultsresults", results);
                    jsonarray = new JSONArray(results);
                    for(int i=0; i<jsonarray.length()-11; i++)
                    {
                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                                /* tv.append(jsonobject.getString("name")+"\n\n");tv.append(jsonobject.getString("time")+"\n\n");tv.append(jsonobject.getString("cost")+"\n\n");tv.append(jsonobject.getString("picture")+"\n\n");tv.append(jsonobject.getString("desc")+"\n\n");tv.append(jsonobject.getString("likes")+"\n\n");*/
                        LatLng latlng = new LatLng(jsonobject.getJSONObject("geometry").getJSONObject("location").getDouble("lat"),
                                jsonobject.getJSONObject("geometry").getJSONObject("location").getDouble("lng"));

                        if(i == jsonarray.length()-12) {
                            selectMarker = googleMap.addMarker(new MarkerOptions().position(latlng).title(jsonobject.getString("name")));
                            selectMarker.setTag(2);
                            selectLatlng = new LatLng(jsonobject.getJSONObject("geometry").getJSONObject("location").getDouble("lat"),
                                    jsonobject.getJSONObject("geometry").getJSONObject("location").getDouble("lng"));
                            selectMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                        }
                        else{
                            tempMarker = googleMap.addMarker(new MarkerOptions().position(latlng).title(jsonobject.getString("name")));
                            tempMarker.setTag(0);
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("resultsss",results);
                LatLng current = null;
                current = new LatLng(temp.getLat(), temp.getLon());
                Marker curMarker = googleMap.addMarker(new MarkerOptions().position(current).title("Current"));
                curMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                curMarker.setTag(0);
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(selectLatlng));
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
            }
        });
    }
    @Override
    protected void onDestroy() {
        //android.os.Process.killProcess(android.os.Process.myPid());
        super.onDestroy();
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount == 0) {
            Toast.makeText(this,"주황 마커를 더블 클릭 해주세요!", Toast.LENGTH_SHORT).show();
        }

        else if(clickCount == 2){
            clickCount = clickCount - 1;
            marker.setTag(clickCount);
            Toast.makeText(this,"한번 더 누르시면 화면이 이동됩니다", Toast.LENGTH_SHORT).show();
        }
        else if(clickCount == 1){
            startActivity(intent);
        }
        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(37.602910, 126.955145)));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10));
        googleMap.setOnMarkerClickListener(this);
    }
}
