package com.melolchik.networkmanager.network.resquests.builder;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Olga Melekhina on 26.09.2016.
 */
public interface RequestBuilderInterface {

    public HashMap getHashMap();

    public void addParam(String key, String value);

    public void addParam(String key, int value);

    public void addParam(String key, double value);

    public void addParam(String key, boolean value);

    public void addParam(String key, Date value, String format);

}
