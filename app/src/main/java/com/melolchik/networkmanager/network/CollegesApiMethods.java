package com.melolchik.networkmanager.network;

import com.melolchik.networkmanager.network.responses.CollegeListResponse;
import com.melolchik.networkmanager.network.responses.CollegeLocationResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Olga Melekhina on 12.07.2016.
 */
public interface CollegesApiMethods {


    @GET("autocomplete")
    Call<CollegeListResponse> list(
            @Query("q") String query,
            @Query("limit") int limit
    );

    @GET("locations/{unitId}")
    Call<CollegeLocationResponse> collegeSpecification(
            @Path("unitId") String userId
    );

}
