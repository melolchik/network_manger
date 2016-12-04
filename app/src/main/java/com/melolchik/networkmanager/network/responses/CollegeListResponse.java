package com.melolchik.networkmanager.network.responses;


import com.melolchik.networkmanager.network.objects.JsonCollege;

import java.util.Collections;
import java.util.List;

/**
 * Created by Olga Melekhina on 13.10.2016.
 */
public class CollegeListResponse extends BaseCollegeResponse{
    protected List<JsonCollege> result;

    public List<JsonCollege> getResult() {
        return result == null ? Collections.<JsonCollege>emptyList() : result;
    }

    @Override
    public String toString() {
        return "result = " + getResult();
    }
}
