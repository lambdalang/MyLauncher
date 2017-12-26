package com.lwjwork.launcher.langlangui.activity;

import java.util.ArrayList;
import java.util.List;


import com.lwjwork.launcher.langlangui.adapter.CommonGridViewAdapter;
import com.lwjwork.launcher.langlangui.adapter.LauncherGridViewAdapter;
import com.lwjwork.launcher.langlangui.util.App;
import com.lwjwork.launcher.langlangui.util.DateAndNetUtil;
import com.lwjwork.launcher.langlangui.util.LoadApp;


import com.ada.jw.R;
import com.ada.mcu.service.Beep;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AllApp extends Activity implements OnItemClickListener {
	private static final String TAG = AllApp.class.getName();
	private GridView mGridView;
	private CommonGridViewAdapter mAdapter;
//	private LauncherGridViewAdapter mGridadApter;
	private static List<App> mApps = new ArrayList<App>();
	private static List<App> wantApps = new ArrayList<App>();
	private static Resources mRes;
	private DateAndNetUtil mDateClockNetUtil;
	private ClockBroadcastReceiver mClock;
	private UninstallBroadcastReceiver mUninstallReceiver;
	private WifiBroadcastReceiver mWifi;
	private TextView mClockText;
	private TextView mDateText;
	private ImageView mNetIcon;
	private TextView TV_Back;

	public AllApp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_allapp);
		updateWallpaperVisibility(true);
		mRes = this.getResources();

		mGridView = ((GridView) findViewById(R.id.all_app_gridview));
		mAdapter = new CommonGridViewAdapter(this);
		mApps.clear();
		wantApps.clear();
		mApps.addAll((List<App>) LoadApp.getInstance(this).getAllApp());
		App temp;
		String packageName;
		for(int i = 0; i < mApps.size(); i++) {
			
			temp = mApps.get(i);
			packageName = temp.getPackageName();
			Log.i("LYJ", "packagerName:" + packageName);
			if(!packageName.equals("com.android.contacts") &&
					!packageName.equals("com.android.calculator2") &&
					!packageName.equals("com.android.browser") &&
					!packageName.equals("com.android.calendar") &&
					!packageName.equals("com.android.camera2") &&
					!packageName.equals("com.android.deskclock") &&
					!packageName.equals("com.android.email") &&
					!packageName.equals("com.android.gallery3d") &&
					!packageName.equals("com.android.music") &&
					!packageName.equals("com.android.providers.downloads.ui") &&
					!packageName.equals("com.android.wididemo") &&
					!packageName.equals("com.android.quicksearchbox") &&
					!packageName.equals("com.android.videoeditor")){
				wantApps.add(temp);
			}
		}
		// mApps.addAll(loadApps());
		
		//mAdapter.setApps(mApps);
		mAdapter.setApps(wantApps);
		mGridView.setAdapter(mAdapter);
		mGridView.setOnItemClickListener(this);

		mDateClockNetUtil = DateAndNetUtil.getInstance(this);

		findById();
		ClockAndDay();
		registerBroadcast();
		
		TV_Back.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Beep.beep(getApplicationContext());
				finish();
			}
		});
	}

	protected void onDestroy() {
		if (mClock != null) {
			unregisterReceiver(mClock);
			mClock = null;
		}
		if (mWifi != null) {
			unregisterReceiver(mWifi);
			mWifi = null;
		}
		if (mUninstallReceiver != null) {
			unregisterReceiver(mUninstallReceiver);
			mUninstallReceiver = null;
		}
		super.onDestroy();
	}

	private void findById() {
		mGridView = (GridView) this.findViewById(R.id.gridView);
		mClockText = (TextView) this.findViewById(R.id.time);
		mDateText = (TextView) this.findViewById(R.id.day);
		mNetIcon = (ImageView) this.findViewById(R.id.net_icon);
		TV_Back = (TextView)this.findViewById(R.id.TV_Back);

		DateAndNetUtil wifiUtil = DateAndNetUtil.getInstance(this);
		switch (wifiUtil.getConnType()) {
		case ConnectivityManager.TYPE_WIFI:
			mNetIcon.setImageLevel(wifiUtil.getWifiSign(5));
			break;
		case ConnectivityManager.TYPE_ETHERNET:
			mNetIcon.setImageLevel(5);
			break;
		case -1:
			mNetIcon.setImageLevel(6);
			break;
		default:
			mNetIcon.setImageLevel(6);
			break;
		}
//		updateClockAndDayVisible();
		return;
	}

	public void onClick(View v) {
//		Log.d(TAG, "----lwj--- onClick() View ID = " + v.getId());
		// this.findViewById(R.id.allapp_background).setBackgroundColor(0xaabbccdd);

		// switch (v.getId()) {
		//
		// case R.id.ic_main_1:
		// startActivity("com.jiesai.langlang",
		// "com.jiesai.langlang.MainActivity");
		// break;
		// default:
		// break;
		// }
		// return;
	}

	private List<App> loadApps() {
		mApps.clear();
		// mApps.addAll();
		App app = null;

		app = new App();
		app.setName("USB");
		app.setPackageName("net.appositedesigns.fileexplorer");
		app.setActivityName("net.appositedesigns.fileexplorer.activity.UPanChooserActivity");
		app.setIcon(mRes.getDrawable(R.drawable.icon_usb));
		mApps.add(app);

		app = new App();
		app.setName("ÎÒµÄÎÄµµ");
		app.setPackageName("net.appositedesigns.fileexplorer");
		app.setActivityName("net.appositedesigns.fileexplorer.activity.UPanChooserActivity");
		app.setIcon(mRes.getDrawable(R.drawable.icon_usb));
		mApps.add(app);

		app = new App();
		app.setName("²¥·ÅÆ÷");
		app.setPackageName("com.hrtvbic.usb.S6A801");
		app.setActivityName("com.hrtvbic.usb.S6A801.ui.main.MainActivity");
		app.setIcon(mRes.getDrawable(R.drawable.icon_music));
		mApps.add(app);

		app = new App();
		app.setName("ÉèÖÃ");
		app.setPackageName("com.android.settings");
		app.setActivityName("com.android.settings.MSettings");
		app.setIcon(mRes.getDrawable(R.drawable.icon_setting));
		mApps.add(app);

		app = new App();
		app.setName("WPS");
		app.setPackageName("cn.wps.moffice_eng");
		app.setActivityName("cn.wps.moffice.documentmanager.PreStartActivity2");
		app.setIcon(mRes.getDrawable(R.drawable.icon_office));
		mApps.add(app);

		app = new App();
		app.setName("±ÚÖ½");
		app.setPackageName("com.android.launcher");
		app.setActivityName("com.android.launcher2.WallpaperChooser");
		app.setIcon(mRes.getDrawable(R.drawable.icon_wallpaper));
		mApps.add(app);

		app = new App();
		app.setName("ä¯ÀÀÆ÷");
		app.setPackageName("com.android.browser");
		app.setActivityName("com.android.browser.BrowserActivity");
		app.setIcon(mRes.getDrawable(R.drawable.icon_browser));
		mApps.add(app);

		app = new App();
		app.setName("PPTV");
		app.setPackageName("com.pplive.androidpad");
		app.setActivityName("com.pplive.androidpad.ui.FirstActivity");
		app.setIcon(mRes.getDrawable(R.drawable.icon_music));
		mApps.add(app);

		app = new App();
		app.setName("TV");
		app.setPackageName("mstar.tvsetting.ui");
		app.setActivityName("mstar.tvsetting.ui.RootActivity");
		app.setIcon(mRes.getDrawable(R.drawable.icon_tv));
		mApps.add(app);

		return mApps;
	}

	public void registerBroadcast() {
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.setPriority(1000);
		intentFilter.addAction("android.intent.action.TIME_TICK");
		intentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
		intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
		intentFilter.addAction("android.intent.action.TIME_SET");
		intentFilter.addAction("android.intent.action.DATE_CHANGED");
		mClock = new ClockBroadcastReceiver();
		registerReceiver(mClock, intentFilter);

		IntentFilter intentFilter2 = new IntentFilter();
		intentFilter2.addAction("android.intent.action.DATE_CHANGED");
		intentFilter2.addAction("android.intent.action.TIME_SET");
		intentFilter2.addAction("android.intent.action.TIME_TICK");
		intentFilter2.addAction("android.intent.action.TIMEZONE_CHANGED");
		intentFilter2.addAction("android.net.wifi.NETWORK_IDS_CHANGED");
		intentFilter2.addAction("android.net.wifi.STATE_CHANGE");
		intentFilter2.addAction("android.net.wifi.WIFI_STATE_CHANGED");
		intentFilter2.addAction("android.net.wifi.RSSI_CHANGED");
		intentFilter2.addAction("android.net.conn.BACKGROUND_DATA_SETTING_CHANGED");
		intentFilter2.addAction("android.net.conn.CONNECTIVITY_CHANGE");
		mWifi = new WifiBroadcastReceiver();
		registerReceiver(mWifi, intentFilter2);

		IntentFilter intentFilter3 = new IntentFilter();
		intentFilter3.addAction("android.intent.action.PACKAGE_REMOVED");
		intentFilter3.addAction("android.intent.action.PACKAGE_INSTALL");
		intentFilter3.addAction("android.intent.action.PACKAGE_ADDED");// lwj
																		// mstar
		intentFilter3.addDataScheme("package");
		mUninstallReceiver = new UninstallBroadcastReceiver();
		registerReceiver(mUninstallReceiver, intentFilter3);
	}

	private class ClockBroadcastReceiver extends BroadcastReceiver {
		private ClockBroadcastReceiver() {
		}

		public void onReceive(Context paramContext, Intent paramIntent) {
			String str = paramIntent.getAction();
			if ((str.equals("android.intent.action.TIME_TICK"))
					|| (str.equals("android.intent.action.DATE_CHANGED"))
					|| (str.equals("android.intent.action.CONFIGURATION_CHANGED"))
					|| (str.equals("android.intent.action.TIMEZONE_CHANGED"))
					|| (str.equals("android.intent.action.TIME_SET")))
				ClockAndDay();
		}
	}

	private class UninstallBroadcastReceiver extends BroadcastReceiver {
		private UninstallBroadcastReceiver() {
		}

		public void onReceive(Context context, Intent intent) {
//			Log.d(TAG, "----lwj--- UninstallBroadcastReceiver onReceive() ");
			String str = intent.getAction();
			String packageNam = intent.getDataString();
			if (("android.intent.action.PACKAGE_REMOVED".equals(str))
					|| ("android.intent.action.PACKAGE_INSTALL".equals(str))) {
				wantApps.clear();
				mApps.addAll(LoadApp.getInstance(context).getAllApp());
				mAdapter.setApps(wantApps);
				mAdapter.updateItems();
			}else if("android.intent.action.PACKAGE_ADDED".equals(str)) {
				mApps.addAll(LoadApp.getInstance(context).getAllApp());
				Log.d(TAG, mApps.get(mApps.size() - 1).getPackageName());
				wantApps.add(mApps.get(mApps.size() - 1));
				mAdapter.setApps(wantApps);
				mAdapter.updateItems();
			}
		}
	}

	private class WifiBroadcastReceiver extends BroadcastReceiver {
		private void WifiNetBroadcastReceiver() {
		}

		@Override
		public void onReceive(Context context, Intent intent) {
//			Log.d(TAG, "----lwj--- WifiBroadcastReceiver onReceive() ");
			DateAndNetUtil wifiUtil = DateAndNetUtil.getInstance(context);
			switch (wifiUtil.getConnType()) {
			case ConnectivityManager.TYPE_WIFI:
				mNetIcon.setImageLevel(wifiUtil.getWifiSign(5));
				break;
			case ConnectivityManager.TYPE_ETHERNET:
				mNetIcon.setImageLevel(5);
				break;
			case -1:
				mNetIcon.setImageLevel(6);
				break;
			default:
				mNetIcon.setImageLevel(6);
				break;
			}
//			updateClockAndDayVisible();
			return;
		}
	}

	void updateWallpaperVisibility(boolean isVisible) {
		if (isVisible) {
			int i = 1048576;
			int j = 0x100000 & getWindow().getAttributes().flags;
//			Log.d(TAG, "----lwj--- updateWallpaperVisibility()  " + "wpflags: " + i + "  curflags: " + j);
			if (i != j)
				getWindow().setFlags(i, 1048576);
		}
	}

	private void ClockAndDay() {
//		Log.d(TAG, "----lwj--- ClockAndDay() ");
		mClockText.setText(mDateClockNetUtil.getTime());
		mDateText.setText(mDateClockNetUtil.getDay());
	}

	private void updateClockAndDayVisible() {
//		Log.d(TAG, "----lwj--- updateClockAndDayVisible() ");
		if (isNetWorkConnect()) {
			mClockText.setVisibility(View.VISIBLE);
			mDateText.setVisibility(View.VISIBLE);
		} else {
			mClockText.setVisibility(View.INVISIBLE);
			mDateText.setVisibility(View.INVISIBLE);
		}
	}

	private boolean isNetWorkConnect() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService("connectivity");
		NetworkInfo currentNetworkInfo = connectivityManager.getActiveNetworkInfo();
		if (currentNetworkInfo != null)
			return currentNetworkInfo.isAvailable();
		if (connectivityManager != null) {
			NetworkInfo[] arrayOfNetworkInfo = connectivityManager.getAllNetworkInfo();
			if (arrayOfNetworkInfo != null)
				for (int i = 0; i < arrayOfNetworkInfo.length; i++)
					if (arrayOfNetworkInfo[i].getState() == NetworkInfo.State.CONNECTED)
						return true;
		}
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//		Log.d(TAG, "----lwj--- AdapterView onItemClick() arg2 = " + arg2);
		if (arg2 < wantApps.size()) {
			App app = wantApps.get(arg2);
			
			String pkgName = (String) app.getPackageName();
			String clsName = (String) app.getActivityName();
//			Log.d(TAG, "----lwj--- AdapterView onItemClick() pkgName = " + pkgName + "  clsName = " + clsName);
			startActivity(pkgName, clsName);
		}
	}

	private void startActivity(String pkgName, String clsName) {
		try {
			Intent intent = new Intent();
			intent.setClassName(pkgName, clsName);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			return;
		} catch (Exception localException) {
			Toast.makeText(this, getResources().getString(R.string.cant_found_activity),
					Toast.LENGTH_SHORT).show();
		}
	}

}
