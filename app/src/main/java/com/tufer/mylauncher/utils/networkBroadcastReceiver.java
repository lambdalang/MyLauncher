package com.tufer.mylauncher.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Message;
import android.util.Log;

public class networkBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int networkTtpe = 0;
        NetworkInfo netInfo;
        String action = intent.getAction();

        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        netInfo = mConnectivityManager.getActiveNetworkInfo();
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {

            if (netInfo != null && netInfo.isAvailable()) {
//	                 /**network connect **/
//	                String name = netInfo.getTypeName(); 

                if (netInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    /**WiFi network**/
                    networkTtpe = 1;
                    Log.i("setNetworkTtpe", "WiFi network:" + networkTtpe);
                } else if (netInfo.getType() == ConnectivityManager.TYPE_ETHERNET) {
                    /**Ethernet network**/
                    networkTtpe = 2;
                    Log.i("setNetworkTtpe", "getBackgroundDataSetting:" + mConnectivityManager.getBackgroundDataSetting());
                    Log.i("setNetworkTtpe", "Ethernet network:" + networkTtpe);
                } else if (netInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                    /**3G network **/
                    networkTtpe = 3;
                    Log.i("setNetworkTtpe", "3G network:" + networkTtpe);
                }
            } else {
                /**network disconnect **/
                networkTtpe = 0;
                //Log.i("setNetworkTtpe", "network disconnect:"  +networkTtpe);
            }
            Message msg = new Message();
            msg.what = networkTtpe;
            msg.obj = netInfo;
            NetworkChangedManager.getInstence().getHandler().sendMessage(msg);
        }

    }

}
