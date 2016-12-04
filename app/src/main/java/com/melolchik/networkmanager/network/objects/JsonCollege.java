package com.melolchik.networkmanager.network.objects;

import android.text.TextUtils;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Olga Melekhina on 13.10.2016.
 */
public class JsonCollege {
   // protected  int "irregular": 0,
    protected String name;
    protected String state;
    protected String unitid;

    protected String city;
    protected double lat;
    protected double lng;

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public String getUnitid() {
        return unitid;
    }

    public String getCity() {
        return city;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getAddress(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getState());
        if(!TextUtils.isEmpty(getCity())){
            stringBuilder.append(", ").append(getCity());
        }
        return stringBuilder.toString();
    }

    public String getFullName(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getName()).append(", ");
        stringBuilder.append(getState());
        if(!TextUtils.isEmpty(getCity())){
            stringBuilder.append(", ").append(getCity());
        }
        return stringBuilder.toString();

    }
    public LatLng getLatLng(){
        return new LatLng(lat,lng);
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("unitid = ").append(getUnitid())
                .append(" name = ").append(getName())
                .append(" state = ").append(getState())
                .append(" city = ").append(getCity())
                .append(" latlng = ").append(getLatLng());
        return stringBuilder.toString();
    }
}
