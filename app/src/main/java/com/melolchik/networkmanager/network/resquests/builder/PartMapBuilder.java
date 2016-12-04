package com.melolchik.networkmanager.network.resquests.builder;

import android.support.annotation.NonNull;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Olga Melekhina on 26.09.2016.
 */
public class PartMapBuilder implements RequestBuilderInterface {

    /**
     * The constant MULTIPART_FORM_DATA.
     */
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";

    /**
     * The M hash map.
     */
    protected HashMap<String, RequestBody> mHashMap;

    protected HashMap<String, String> mHashMapTest;

    /**
     * Instantiates a new Part map builder.
     */
    public PartMapBuilder() {
        mHashMap = new LinkedHashMap<>();
        mHashMapTest = new LinkedHashMap<>();
    }

    public HashMap<String, RequestBody> getHashMap() {
        return mHashMap;
    }

    @Override
    public void addParam(String key, String value) {
        mHashMap.put(key, createPartFromString(value));
        mHashMapTest.put(key,value);
    }

    @Override
    public void addParam(String key, int value) {
        addParam(key, String.valueOf(value));
    }

    @Override
    public void addParam(String key, double value) {
        addParam(key, String.valueOf(value));
    }

    @Override
    public void addParam(String key, boolean value) {
        //addParam(key, String.valueOf(value));
        addParam(key, value ? 1 : 0);
    }

    @Override
    public void addParam(String key, Date value, String format) {
        if (value == null) return;
        String str_value = convertDate(value, format);
        addParam(key, str_value);
    }

    /**
     * Convert date string.
     *
     * @param date   the date
     * @param format the format
     * @return the string
     */
    protected String convertDate(Date date, final String format) {
        if (date == null)
            return "";
        //final String DATE_FORMAT = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.ENGLISH);
        return formatter.format(date);
    }

    /**
     * Create part from string request body.
     *
     * @param descriptionString the description string
     * @return the request body
     */
    @NonNull
    public static RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MediaType.parse(MULTIPART_FORM_DATA), descriptionString);
    }

    /**
     * Create multi part body multipart body . part.
     *
     * @param field the field
     * @param file  the file
     * @return the multipart body . part
     */
    public static MultipartBody.Part createMultiPartBody(String field, File file) {

        if (file == null) return null;
        RequestBody requestBody = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), file);
        return MultipartBody.Part.createFormData(field, file.getName(), requestBody);
    }

    @Override
    public String toString() {
        return mHashMapTest.toString();
    }
}
