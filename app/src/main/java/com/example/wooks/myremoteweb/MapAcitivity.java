package com.example.wooks.myremoteweb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Wooks on 2017-04-03.
 */

public class MapAcitivity extends AppCompatActivity implements OnMapReadyCallback {
    static GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_map);

        MapFragment mapFragment =  (MapFragment) getFragmentManager().findFragmentById(R.id.fm_map_01);
        mapFragment.getMapAsync(this);
        ViewGroup.LayoutParams params = mapFragment.getView().getLayoutParams();
        params.height = 1400;
        mapFragment.getView().setLayoutParams(params);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(37.602910, 126.955145)));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10));
    }
}
