package com.tufer.mylauncher;

import android.app.Application;

import com.tufer.mylauncher.utils.NetworkChangedManager;


public class Appliction extends Application{
	  @Override
	    public void onCreate() {
	        super.onCreate();
	        
	        NetworkChangedManager.getInstence().init();
	  
	    }

}
