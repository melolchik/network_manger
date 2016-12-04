package com.melolchik.networkmanager.network.resquests;

import com.melolchik.networkmanager.network.ResponseCallback;
import com.melolchik.networkmanager.network.responses.CollegeListResponse;
import com.melolchik.networkmanager.network.resquests.builder.HashMapBuilder;
import com.melolchik.networkmanager.network.resquests.builder.RequestBuilderInterface;

import java.util.HashMap;

import retrofit2.Call;

/**
 * Created by Olga Melekhina on 26.09.2016.
 */
public class CollegeListRequest extends BaseRequest {

    public static final String FIELD_QUERY = "q";//(*)
    /**
     * The constant FIELD_PASSWORD.
     */
    public static final String FIELD_LIMIT = "limit";

    protected String mQuery;
    protected int mLimit;


    /**
     * The M response callback.
     */
    protected final ResponseCallback<CollegeListResponse> mResponseCallback;

    /**
     * Instantiates a new Sign up request.
     *
     * @param responseCallback the response callback
     */
    public CollegeListRequest(ResponseCallback<CollegeListResponse> responseCallback) {
        super();
        mResponseCallback = responseCallback;
    }

    @Override
    protected RequestBuilderInterface createBuilderInterface() {
        return new HashMapBuilder();
    }

    @Override
    public HashMap getHashMap() {
        return super.getHashMap();
    }

    public void setQuery(String query) {
        mQuery = query;
    }

    public void setLimit(int limit) {
        mLimit = limit;
    }

    public String getQuery() {
        return mQuery;
    }

    public int getLimit() {
        return mLimit;
    }

    public void execute() {
        Runnable runnableTask = new Runnable() {
            @Override
            public void run() {
                if (mResponseCallback != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mResponseCallback.onStartLoad();
                        }
                    });

                }
                Call<CollegeListResponse> call = getNetworkManager().getCollegesApiMethods().list(getQuery(),getLimit());
                call.enqueue(new CallbackWrapper(mResponseCallback));
            }
        };
        getNetworkManager().executeTask(runnableTask);
    }
}
