package com.melolchik.networkmanager;

import android.support.multidex.MultiDexApplication;
import com.melolchik.networkmanager.network.NetworkManager;
import com.melolchik.networkmanager.utils.Util;

/**
 * Created by melolchik on 08.04.2018.
 */

public class AppBase extends MultiDexApplication {
	
	@Override public void onCreate() {
		super.onCreate();
		Util.init(getApplicationContext());
		NetworkManager.createInstance(getApplicationContext());
	}
}
