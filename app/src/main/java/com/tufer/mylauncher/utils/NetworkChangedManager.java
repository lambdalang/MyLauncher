package com.tufer.mylauncher.utils;

import java.util.ArrayList;

import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;

import com.tufer.mylauncher.MainActivity;


public class NetworkChangedManager {

	private static NetworkChangedManager instance;
	
	private ArrayList<NetworkChengedListener> listeners;
	
	private Handler handler;
	
	private NetworkChangedManager(){
	}
	
	public static NetworkChangedManager getInstence(){
		if(instance==null){
			instance = new NetworkChangedManager();
		}
		return instance;
	}
	

	public void init(){
		listeners = new ArrayList<NetworkChengedListener>();
		handler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				int type = msg.what;
				NetworkInfo info = (NetworkInfo) msg.obj;
				if(listeners!=null&&!listeners.isEmpty()){
					for(NetworkChengedListener l:listeners){
						if(l!=null){
							l.onNetworkChenged(info, type);
						}
					}
				}
				super.handleMessage(msg);
			}
		};
	}
	

	public void addNetworkListener(NetworkChengedListener l){
		if(listeners==null){
			listeners = new ArrayList<NetworkChengedListener>();
		}
		for(NetworkChengedListener tmp:listeners){
			if(tmp==l){
				return;
			}
		}
		listeners.add(l);
	}
	

	public void removeNetworkListener(MainActivity mainActivity){
		if(listeners==null){
			return;
		}
		for(NetworkChengedListener tmp:listeners){
			if(tmp==mainActivity){
				listeners.remove(tmp);
				return;
			}
		}
	}
	
	public Handler getHandler() {
		return handler;
	}


	public static interface NetworkChengedListener{

		public abstract void onNetworkChenged(NetworkInfo netInfo, int netWorkType);
	}

}
