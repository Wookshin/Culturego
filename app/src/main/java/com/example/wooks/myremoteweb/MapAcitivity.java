package com.example.wooks.myremoteweb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
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

public class MapAcitivity extends AppCompatActivity implements OnMapReadyCallback {
    static GoogleMap googleMap;
    static String results;
    private ToggleButton tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_map);

        MapFragment mapFragment =  (MapFragment) getFragmentManager().findFragmentById(R.id.fm_map_01);
        mapFragment.getMapAsync(this);
        ViewGroup.LayoutParams params = mapFragment.getView().getLayoutParams();
        params.height = 1400;
        mapFragment.getView().setLayoutParams(params);

        tb = (ToggleButton)findViewById(R.id.toggle2);
        tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(tb.isChecked()){

                        try {
                            JSONArray jsonarray = null;
                            jsonarray = new JSONArray(results);
                            for(int i=0; i<jsonarray.length(); i++)
                            {
                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                LatLng latlng = new LatLng(jsonobject.getJSONObject("geometry").getJSONObject("location").getDouble("lat"),
                                        jsonobject.getJSONObject("geometry").getJSONObject("location").getDouble("lng"));
                                googleMap.addMarker(new MarkerOptions().position(latlng).title(jsonobject.getString("name")));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        LatLng current = null;
                        current = new LatLng(temp.getLat(), temp.getLon());
                        Marker curMarker = googleMap.addMarker(new MarkerOptions().position(current).title("Current"));
                        curMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(current));
                        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
                    }else{

                    }
                }catch(SecurityException ex){
                }
            }
        });
    }



    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(37.602910, 126.955145)));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10));
    }
}
