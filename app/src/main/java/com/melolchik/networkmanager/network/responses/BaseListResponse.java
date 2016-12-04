package com.melolchik.networkmanager.network.responses;

import java.util.List;

/**
 * Created by Olga Melekhina on 26.09.2016.
 */
public abstract class BaseListResponse<ItemType> extends BaseResponse {

    public abstract List<ItemType> getData();

    public abstract String getMaxDataDate();

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("data = ").append(getData())
                .append(" maxDataDate = ").append(getMaxDataDate());
        return builder.toString();
    }

}
