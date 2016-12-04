package com.melolchik.networkmanager.network.responses;


import com.melolchik.networkmanager.network.objects.JsonCollege;

/**
 * Created by Olga Melekhina on 13.10.2016.
 */
public class CollegeLocationResponse extends BaseCollegeResponse{
    protected JsonCollege result;

    public JsonCollege getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "result = " + getResult();
    }
}
