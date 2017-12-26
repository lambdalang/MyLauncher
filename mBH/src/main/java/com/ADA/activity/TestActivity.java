package com.ADA.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;

import com.ADA.mcu.service.ServiceRepliedData;
import com.ADA.mcu.service.SettingData;
import com.ADA.mcu.service.IContactUi;
import com.ADA.mcu.service.IServiceCallback;

/**
 * Created by Administrator on 2017/11/27 0027.
 */

public class TestActivity extends Activity {

    private String TAG = "TestActivity";

    private IContactUi mService;
    private IServiceCallback mCallback;
    private ServiceConnection mConnection;

    /*
	 * 连接后台
	 */
    private void initConnection() {
        mCallback = new IServiceCallback.Stub(){

            @Override
            public void sendSettingData(SettingData data) throws RemoteException {

            }

            @Override
            public void responseToUi(ServiceRepliedData data) throws RemoteException {

            }
        };

        mConnection = new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                // TODO Auto-generated method stub
                mService = IContactUi.Stub.asInterface(service);
                try {
					/*
					 * if(mService.isInited()) {
					 * mService.registerCallback(mCallback); } else {
					 * exitService(); }
					 */

                    Log.i(TAG, "onServiceConnected");
                    mService.registerCallback("com.ADA.mbh", mCallback);

                } catch (RemoteException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mService = null;
            }
        };
    }



}
