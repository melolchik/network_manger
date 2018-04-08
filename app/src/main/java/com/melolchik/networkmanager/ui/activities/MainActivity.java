package com.melolchik.networkmanager.ui.activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.melolchik.networkmanager.R;
import com.melolchik.networkmanager.network.NetworkManager;
import com.melolchik.networkmanager.ui.fragments.BaseFragment;
import com.melolchik.networkmanager.ui.fragments.SelectCollegeFragment;

public class MainActivity extends AppCompatActivity {


    /**
     * The constant ID_FRAGMENT_CONTAINER.
     */
    public final static int ID_FRAGMENT_CONTAINER = R.id.fragment_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showFragment(SelectCollegeFragment.createInstance(R.string.select_place, R.string.select_place, null), false, false);
    }

    /**
     * Show fragment.
     *
     * @param newFragment       the new fragment
     * @param addToBackStack    the add to back stack
     * @param withFadeAnimation the with fade animation
     */
    public void showFragment(BaseFragment newFragment, boolean addToBackStack, boolean withFadeAnimation) {
        if (newFragment == null) return;
        FragmentManager fm = getSupportFragmentManager();
        // BaseFragment currentFragment = (BaseFragment) fm.findFragmentById(ID_FRAGMENT_CONTAINER);
        //log("changeFragment:currentFragment = " + currentFragment);
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        if (withFadeAnimation) {
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        }
        if (addToBackStack) {
            fragmentTransaction.add(ID_FRAGMENT_CONTAINER, newFragment);
            fragmentTransaction.addToBackStack(newFragment.getTagForStack());
        } else {
            fragmentTransaction.replace(ID_FRAGMENT_CONTAINER, newFragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }


}
