package com.tufer.mylauncher.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.tufer.mylauncher.MainActivity;

/**
 * Created by Administrator on 2017/9/28 0028.
 */

public class SDcardBroadcastReceiver extends BroadcastReceiver {

    public static boolean isVisible=false;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_MEDIA_EJECT)) {
            //launcherUsb.setVisibility(View.GONE);
            if (MainActivity.mainActivity!=null){
                MainActivity.mainActivity.handler.sendEmptyMessage(MainActivity.UPDATA_USB_GONE);
            }
            isVisible=false;
        } else if (intent.getAction().equals(Intent.ACTION_MEDIA_MOUNTED)) {
            //USB设备挂载，更新UI
            //launcherUsb.setVisibility(View.VISIBLE);
            if (MainActivity.mainActivity!=null){
                MainActivity.mainActivity.handler.sendEmptyMessage(MainActivity.UPDATA_USB_VISIBLE);
            }
            isVisible=true;
        }
    }
}
