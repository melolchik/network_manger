package com.melolchik.networkmanager.network.resquests;

import com.melolchik.networkmanager.network.ResponseCallback;
import com.melolchik.networkmanager.network.responses.CollegeLocationResponse;
import com.melolchik.networkmanager.network.resquests.builder.HashMapBuilder;
import com.melolchik.networkmanager.network.resquests.builder.RequestBuilderInterface;

import java.util.HashMap;

import retrofit2.Call;

/**
 * Created by Olga Melekhina on 26.09.2016.
 */
public class CollegeLocationRequest extends BaseRequest {

    protected String mUnitId;


    /**
     * The M response callback.
     */
    protected final ResponseCallback<CollegeLocationResponse> mResponseCallback;

    /**
     * Instantiates a new Sign up request.
     *
     * @param responseCallback the response callback
     */
    public CollegeLocationRequest(ResponseCallback<CollegeLocationResponse> responseCallback) {
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

    public String getUnitId() {
        return mUnitId;
    }

    public void setUnitId(String unitId) {
        mUnitId = unitId;
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
                Call<CollegeLocationResponse> call = getNetworkManager().getCollegesApiMethods().collegeSpecification(getUnitId());
                call.enqueue(new CallbackWrapper(mResponseCallback));
            }
        };
        getNetworkManager().executeTask(runnableTask);
    }
}
