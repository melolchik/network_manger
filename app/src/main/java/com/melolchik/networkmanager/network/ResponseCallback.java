package com.melolchik.networkmanager.network;

import com.melolchik.networkmanager.network.responses.BaseResponse;

/**
 * Created by Olga Melekhina on 01.03.2016.
 *
 */
public interface ResponseCallback<Token extends BaseResponse>{

    /**
     * When the API has been called
     */
    void onStartLoad();

    /**
     * When request successful and statuscode == 1
     *
     * @param response - base class of all responses
     */
    void onSuccess(Token response);

    /**
     * When request not successful on server
     *
     * @param statusCode - code of error
     * @param message    - message of error
     */
    void onError(int statusCode, String message);

    /**
     * Network error, parsing error, or other error
     * We can add parameter here in future
     *
     * @param statusCode    - code of error, reserved
     * @param message - message of error,reserved
     */
    void onFailure(int statusCode, String message);
}
