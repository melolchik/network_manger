package com.melolchik.networkmanager.network.objects;

import android.text.TextUtils;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Olga Melekhina on 12.10.2016.
 */
public class PlaceInfo {

    protected String mPlaceId;
    protected String mFullText;
    protected String mPrimaryText;
    protected String mSecondaryText;
    protected String mFullCityText;
    protected LatLng mLatLng;

    public PlaceInfo(String placeId) {
        mPlaceId = placeId;
    }

    public String getPlaceId() {
        return mPlaceId;
    }

    public void setPlaceId(String placeId) {
        mPlaceId = placeId;
    }

    public String getFullText() {
        if (TextUtils.isEmpty(mFullText)) {
            StringBuilder builder = new StringBuilder();
            builder.append(mPrimaryText);
            if (!TextUtils.isEmpty(mSecondaryText)) {
                builder.append(", ").append(mSecondaryText);
            }
            mFullText = builder.toString();
        }
        return mFullText;

    }

    public void setFullText(String fullText) {
        mFullText = fullText;
    }

    public String getPrimaryText() {
        return mPrimaryText;
    }

    public void setPrimaryText(String primaryText) {
        mPrimaryText = primaryText;
    }

    public String getSecondaryText() {
        return mSecondaryText;
    }

    public void setSecondaryText(String secondaryText) {
        mSecondaryText = secondaryText;
    }

    public LatLng getLatLng() {
        return mLatLng;
    }

    public void setLatLng(LatLng latLng) {
        mLatLng = latLng;
    }

    public String getFullCityText() {
        return mFullCityText;
    }

    public void setFullCityText(String fullCityText) {
        mFullCityText = fullCityText;
    }

    @Override
    public String toString() {
       StringBuilder builder = new StringBuilder();
        builder.append("mFullText = ").append(mFullText)
                .append(" mFullCityText = ").append(mFullCityText)
                .append(" mPrimaryText = ").append(mPrimaryText)
                .append(" mSecondaryText = ").append(mSecondaryText);
        return builder.toString();
    }
}
