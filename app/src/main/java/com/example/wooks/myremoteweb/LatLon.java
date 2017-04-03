package com.example.wooks.myremoteweb;

/**
 * Created by Wooks on 2017-02-28.
 */

public class LatLon {
    private double lat, lon;
    LatLon(double lat, double lon){
        this.lat = lat;
        this.lon = lon;
    }
    protected double getLat() {
        return lat;
    }
    protected double getLon() {
        return lon;
    }
    protected void setLat(double lat) {
        this.lat = lat;
    }
    protected void setLon(double lon) {
        this.lon = lon;
    }
}
