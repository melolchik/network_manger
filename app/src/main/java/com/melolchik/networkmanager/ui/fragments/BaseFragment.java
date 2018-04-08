package com.melolchik.networkmanager.ui.fragments;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import java.io.File;


/**
 * Created by Olga Melekhina on 03.06.2016.
 */
public abstract class BaseFragment extends Fragment {

    /**
     * Gets view layout id.
     *
     * @return the view layout id
     */
    protected abstract int getViewLayoutID();

    /**
     * On create view.
     *
     * @param rootView the root view
     */
    protected abstract void onCreateView(View rootView, Bundle savedInstanceState);

    /**
     * Is nested fragment boolean.
     *
     * @return the boolean
     */
    protected boolean isNestedFragment() {
        return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!isNestedFragment()) {
            setRetainInstance(true);
        }
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getViewLayoutID(), container, false);
        view.setClickable(true);
        onCreateView(view,savedInstanceState);
        //initBroadcastReceiver();
        return view;
    }
    /**
     * Network available.
     */

    public void networkAvailable() {

    }

    /**
     * Network unavailable.
     */
    public void networkUnavailable() {

    }


    /**
     * Gets tag for stack.
     *
     * @return the tag for stack
     */
    public String getTagForStack() {
        return this.getClass().getCanonicalName();
    }

    /**
     * Show error toast.
     *
     * @param message the message
     */
    public void showErrorToast(String message) {
        if (getActivity() == null)
            return;
        if (!isVisible())
            return;
        Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_LONG);
        toast.show();
    }

    /**
     * Show error toast.
     *
     * @param message the message
     */
    public void showErrorToast(@StringRes int message) {
        if (getActivity() == null)
            return;
        if (!isVisible())
            return;
        Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_LONG);
        toast.show();
    }


    /**
     * On back pressed boolean.
     *
     * @return the boolean
     */
    public boolean onBackPressed() {
        return false;
    }





}
