package com.melolchik.networkmanager.network.responses;

import com.melolchik.networkmanager.network.NetworkConstants;

import java.util.List;

/**
 * Created by Olga Melekhina on 12.07.2016.
 */
public class BaseResponse {

    //common fields for all responses

    /**
     * The Status code.
     */
    public int statusCode;
    /**
     * The Errors.
     */
    public List<String> errors;
    /**
     * The Message.
     */
    public String message;

    /**
     * Gets status code.
     *
     * @return the status code
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Gets errors.
     *
     * @return the errors
     */
    public List<String> getErrors() {
        return errors;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Is successful boolean.
     *
     * @return the boolean
     */
    public boolean isSuccessful(){
        return statusCode == NetworkConstants.STATUS_CODE_OK;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("statusCode = ").append(getStatusCode())
                .append(" message = ").append(getMessage())
        .append(" errors = ").append(getErrors());
        return builder.toString();
    }
}
