package com.melolchik.networkmanager.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.annotation.StringRes;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;

import android.widget.Toast;
import com.google.android.gms.maps.model.LatLng;
import com.melolchik.networkmanager.R;

/**
 * Created by Olga Melekhina on 01.07.2016.
 */
public class Util {
    /**
     * The constant context.
     */
    public static Context mContext;
    
    /**
     * Init.
     *
     * @param context the context
     */
    public static void init(Context context) {
        mContext = context;
    }
    
    /**
     * Gets context.
     *
     * @return the context
     */
    public static Context getApplicationContext() {
        return mContext;
    }

    public static String notEmptyString(String origin) {
        return origin == null ? "" : origin.trim();
    }

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    public  static boolean isEmailValid(String email){
        boolean valid = true;

        if(email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            valid = false;
        }

        return valid;

    }


    public static boolean isNetworkAvailable(Context context) {
        if (context == null)
            return false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public static void hideSoftKeyboard(Activity activity) {
        if (activity != null && activity.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager
                    = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static void showSoftKeyboard(Activity activity) {
        if (activity != null/* && activity.getCurrentFocus() != null*/) {
            InputMethodManager inputMethodManager
                    = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
    }

    public static int getScreenWidth(Context context) {
        if (context == null) return 0;
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        if (context == null) return 0;
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int getActionBarHeight(Context context) {
       /* TypedValue tv = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true);*/
        return context.getResources().getDimensionPixelSize(R.dimen.height_action_bar);

    }


    public static int getFragmentHeight(Context context) {
        return getScreenHeight(context) - getStatusBarHeight(context);
    }


    public static double computeDistanceBetween(LatLng from, LatLng to){
        if(from == null && to == null) return 0f;
        double R = 6371000.0D; // earth’s radius (mean radius = 6 371km)
        double fi1 = Math.toRadians(from.latitude); //self conVertDegreeToRadians:firstPoint.latitude];
        double fi2 = Math.toRadians(to.latitude);// [self conVertDegreeToRadians:secondPoint.latitude];
        double deltaFi = Math.toRadians(to.latitude - from.latitude);
        double deltaLambda =  Math.toRadians(to.longitude - from.longitude);

        double x = deltaLambda * Math.cos((fi1 + fi2) * 0.5);
        double y = deltaFi;
        double distance = Math.sqrt(x * x + y * y) * R;
        return distance;
    }

    public static double computeDistanceBetweenInMiles(LatLng from, LatLng to){
        return Util.transformMetersToMiles(computeDistanceBetween(from, to));
    }

    public static double transformMetersToMiles(double meterValue){
        return meterValue/1609;
    }

    public static LatLng locationToLatLng(Location location){
        if (location == null) return null;
        return new LatLng(location.getLatitude(),location.getLongitude());
    }



    public static boolean equalLocations(Location one, Location two){
        if(one == null || two == null) return false;
        String oneString = String.format( "%.3f_%.3f", one.getLatitude(),one.getLongitude());
        String twoString = String.format( "%.3f_%.3f", two.getLatitude(),two.getLongitude());
        return oneString.equals(twoString);
    }
    
    /**
     * Return a localized string from the application's package's
     * default string table.
     *
     * @param resId Resource id for the string
     */
    public static final String getString(@StringRes int resId) {
        if (mContext == null) return "";
        return mContext.getString(resId);
    }
    
    /**
     * Return a localized formatted string from the application's package's
     * default string table, substituting the format arguments as defined in
     * {@link java.util.Formatter} and {@link java.lang.String#format}.
     *
     * @param resId      Resource id for the format string
     * @param formatArgs The format arguments that will be used for substitution.
     */
    
    public static final String getString(@StringRes int resId, Object... formatArgs) {
        if (mContext == null) return "";
        return mContext.getString(resId, formatArgs);
    }
    
    /**
     * Show error toast.
     *
     * @param message the message
     */
    public static void showErrorToast(String message) {
        if (mContext == null)
            return;
        Toast toast = Toast.makeText(mContext, message, Toast.LENGTH_LONG);
        toast.show();
    }
    
    /**
     * Show error toast.
     *
     * @param message the message
     */
    public static void showErrorToast(@StringRes int message) {
        if (mContext == null)
            return;
        Toast toast = Toast.makeText(mContext, message, Toast.LENGTH_LONG);
        toast.show();
    }
    protected static void log(String message) {
       // AppLogger.log(Util.class.getSimpleName() + " " + message);
    }

}
