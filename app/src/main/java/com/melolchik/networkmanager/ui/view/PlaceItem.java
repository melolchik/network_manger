package com.melolchik.networkmanager.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.melolchik.networkmanager.network.objects.JsonCollege;
import com.melolchik.networkmanager.network.objects.PlaceInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.melolchik.networkmanager.R;

/**
 * Created by Olga Melekhina on 11.10.2016.
 */
public class PlaceItem extends FrameLayout implements Checkable {

    protected @BindView(R.id.place_name)
    TextView mNameTextView;

    protected @BindView(R.id.place_address)
    TextView mAddressTextView;

    protected @BindView(R.id.place_image_check)
    ImageView mCheckImageView;

    protected PlaceInfo mPlaceInfo;
    protected boolean isChecked = false;
    protected JsonCollege mCollegeInfo;


    public PlaceItem(Context context) {
        this(context,null);
    }

    public PlaceItem(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PlaceItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.view_place_item, this);
        ButterKnife.bind(this, this);
    }

    public void setPlaceInfo(PlaceInfo placeInfo){
        mPlaceInfo = placeInfo;
       // log("setAddress = " + mAddress);
        mNameTextView.setText(placeInfo.getPrimaryText());
        mAddressTextView.setText(placeInfo.getSecondaryText());
    }

    public void setCollegeInfo(JsonCollege college){
        mCollegeInfo = college;
        // log("setAddress = " + mAddress);
        mNameTextView.setText(college.getName());
        mAddressTextView.setText(college.getState());
    }

    protected void log(String message) {
        //AppLogger.log(this.getClass().getSimpleName() + " " + message);
    }


    @Override
    public void setChecked(boolean checked) {
        isChecked = checked;
        mCheckImageView.setVisibility(checked ? VISIBLE : GONE);
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {

    }
}
