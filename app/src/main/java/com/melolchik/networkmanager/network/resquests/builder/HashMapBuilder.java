package com.melolchik.networkmanager.network.resquests.builder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;

/**
 * Created by Olga Melekhina on 26.09.2016.
 */
public class HashMapBuilder implements RequestBuilderInterface {

    protected HashMap<String, String> mHashMap;

    public HashMapBuilder(){
        mHashMap = new LinkedHashMap<>();
    }

    public HashMap<String, String> getHashMap() {
        return mHashMap;
    }

    @Override
    public void addParam(String key, String value) {
        mHashMap.put(key, value);
    }

    @Override
    public void addParam(String key, int value) {
        mHashMap.put(key, String.valueOf(value));
    }

    @Override
    public void addParam(String key, double value) {
        mHashMap.put(key, String.valueOf(value));
    }

    @Override
    public void addParam(String key, boolean value) {
        //mHashMap.put(key, String.valueOf(value));
        addParam(key, value ? 1 : 0);
    }



    @Override
    public void addParam(String key, Date value, String format) {
        mHashMap.put(key, convertDate(value,format));
    }

    /**
     * Convert date string.
     *
     * @param date the date
     * @return the string
     */
    protected String convertDate(Date date, final String format) {
        if (date == null)
            return "";
        //final String DATE_FORMAT = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.ENGLISH);
        return formatter.format(date);
    }

    @Override
    public String toString() {
        return mHashMap.toString();
    }
}
