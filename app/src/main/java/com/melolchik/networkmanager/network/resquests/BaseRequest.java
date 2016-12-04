package com.melolchik.networkmanager.network.resquests;

import android.os.Handler;

import com.melolchik.networkmanager.network.NetworkConstants;
import com.melolchik.networkmanager.network.NetworkManager;
import com.melolchik.networkmanager.network.ResponseCallback;
import com.melolchik.networkmanager.network.responses.BaseResponse;
import com.melolchik.networkmanager.network.resquests.builder.RequestBuilderInterface;

import java.util.Date;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Olga Melekhina on 12.07.2016.
 */
public abstract class BaseRequest {

    /**
     * The constant DEVICE_TYPE.
     */
    public static final String DEVICE_TYPE = "android";//Android

    public final static String FIELD_LATITUDE = "latitude";

    public final static String FIELD_LONGITUDE = "longitude";

    /**
     * The M ui handler.
     */
    protected Handler mUiHandler;
    protected RequestBuilderInterface mBuilder;

    /**
     * Instantiates a new Base request.
     */
    protected BaseRequest() {
        mUiHandler = new Handler();
        mBuilder = createBuilderInterface();
    }

    protected abstract RequestBuilderInterface createBuilderInterface();

    /**
     * Add param base request.
     *
     * @param key   the key
     * @param value the value
     * @return the base request
     */
    public BaseRequest addParam(String key, String value) {
        //String param = value.replace("'","\\'").replace("`","\\`").replace("\\","\\\\");
        mBuilder.addParam(key, value);
        return this;
    }

    /**
     * Add param base request.
     *
     * @param key   the key
     * @param value the value
     * @return the base request
     */
    public BaseRequest addParam(String key, int value) {
        mBuilder.addParam(key, value);
        return this;
    }

    /**
     * Add param base request.
     *
     * @param key   the key
     * @param value the value
     * @return the base request
     */
    public BaseRequest addParam(String key, double value) {
        mBuilder.addParam(key, value);
        return this;
    }

    public BaseRequest addParam(String key, boolean value) {
        mBuilder.addParam(key, value);
        return this;
    }

     /**
     * Add param base request.
     *
     * @param key   the key
     * @param value the value
     * @return the base request
     */
    public BaseRequest addParam(String key, Date value) {
        if (value == null) return this;
        mBuilder.addParam(key, value,"yyyy-MM-dd");
        return this;
    }

    public HashMap getHashMap(){
        return mBuilder.getHashMap();
    }
    /**
     * Execute.
     */
    public abstract void execute();

    /**
     * Get network manager network manager.
     *
     * @return the network manager
     */
    public static NetworkManager getNetworkManager() {
        return NetworkManager.getInstance();
    }

    /**
     * The type Callback wrapper.
     *
     * @param <Token> the type parameter
     */
    protected class CallbackWrapper<Token extends BaseResponse> implements Callback<ResponseCallback<Token>> {

        /**
         * The M response callback.
         */
        protected final ResponseCallback mResponseCallback;

        /**
         * Instantiates a new Callback wrapper.
         *
         * @param callback the callback
         */
        public CallbackWrapper(ResponseCallback callback) {
            mResponseCallback = callback;
        }

        @Override
        public void onResponse(Call<ResponseCallback<Token>> call, Response<ResponseCallback<Token>> response) {
            if (mResponseCallback == null) return;
            @SuppressWarnings("unchecked")
            final Token jsonResponse = (Token) response.body();
            //log("callback respond = " + response.body());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (jsonResponse == null) {
                        mResponseCallback.onFailure(NetworkConstants.FAIL_CODE_SERVER_ERROR, "Null response");
                    } else if (jsonResponse.isSuccessful()) {
                        mResponseCallback.onSuccess(jsonResponse);
                    } else {
                        mResponseCallback.onError(jsonResponse.getStatusCode(), jsonResponse.getMessage());
                    }
                }
            });
        }

        @Override
        public void onFailure(Call<ResponseCallback<Token>> call, final Throwable t) {
            if (mResponseCallback == null) return;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mResponseCallback.onFailure(NetworkConstants.FAIL_CODE_NETWORK_ERROR, t.getMessage());
                }
            });
        }

    }

    /**
     * Run on ui thread.
     *
     * @param runnable the runnable
     */
    protected void runOnUiThread(Runnable runnable) {
        if (runnable == null) return;
        if (mUiHandler == null) return;
        mUiHandler.post(runnable);
    }

    /**
     * Log.
     *
     * @param message the message
     */
    protected void log(String message) {
        //AppLogger.log(this.getClass().getSimpleName() + " " + message);
    }

    @Override
    public String toString() {
        if(mBuilder != null){
            return mBuilder.toString();
        }else {
            return "";
        }

    }
}
