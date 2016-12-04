package com.melolchik.networkmanager.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.melolchik.networkmanager.network.NetworkConstants;

/**
 * Created by Olga Melekhina on 12.07.2016.
 */
public class BaseCollegeResponse extends BaseResponse{

    //common fields for all responses

    @SerializedName("ok")
    @Expose
    public int statusCode;

    /**
     * Gets status code.
     *
     * @return the status code
     */
    @Override
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Is successful boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean isSuccessful(){
        return statusCode == NetworkConstants.STATUS_CODE_OK;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("statusCode = ").append(getStatusCode());
        return builder.toString();
    }
}
