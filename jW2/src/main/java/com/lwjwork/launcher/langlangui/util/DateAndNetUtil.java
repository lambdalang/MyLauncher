package com.lwjwork.launcher.langlangui.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.ada.jw.R;

import android.text.format.Time;
import android.util.Log;
import android.content.res.Resources;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class DateAndNetUtil {
	private static final String TAG = DateAndNetUtil.class.getName();
	
	public static final int CONNE_TYPE_ETHERNET = 1;
	public static final int CONNE_TYPE_WIFI = 0;
	public static final int CONNE_TYPE_WITHOUT = -1;
	private static ConnectivityManager connMgr;
	private static Context mContext;
	private static DateAndNetUtil instance;
	private static WifiManager wifiMgr;
	private Time mtime = new Time();

	public DateAndNetUtil() {
	}

	public static DateAndNetUtil getInstance(Context ctx) {
		try {
			if (instance == null)
				instance = new DateAndNetUtil();
			
			mContext = ctx;
			connMgr = (ConnectivityManager) mContext
					.getSystemService("connectivity");
			wifiMgr = (WifiManager) mContext.getSystemService("wifi");
			DateAndNetUtil util = instance;
			return util;
		} finally {
		}
	}

	public int getConnType() {
		int i = -1;
		NetworkInfo localNetworkInfo = connMgr.getActiveNetworkInfo();
		if (localNetworkInfo != null) {
//			Log.d(TAG, "----lwj--- getConnType() NetworkInfo = "+localNetworkInfo.getType());
			if (localNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI)
				i = ConnectivityManager.TYPE_WIFI;
			if (localNetworkInfo.getType() == ConnectivityManager.TYPE_ETHERNET)
				i = ConnectivityManager.TYPE_ETHERNET;
			return i;
		}
		return -1;
	}

	public int getWifiSign(int numLevels) {
		int level = 0;
		level = WifiManager.calculateSignalLevel(wifiMgr.getConnectionInfo()
				.getRssi(), numLevels);
//		Log.d(TAG, "----lwj--- getConnType() Wifi SignalLevel = "+level);
		return level;
	}

	public String getDay() {
		// Calendar calendar = Calendar.getInstance();
		// calendar.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		// calendar.setTime(new Date());
		// Log.d(TAG,
		// "----lwj--- getDay() Calendar = "+calendar.getTime().toString());
		// Date now = calendar.getTime();

		// SimpleDateFormat sdf = new
		// SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.CHINA);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-EE",
				Locale.CHINA);
//		Log.d(TAG, "----lwj--- getDay() SimpleDateFormat = " + sdf.format(new Date()));
		String all[] = sdf.format(new Date()).split("-");
		int year = Integer.parseInt(all[0]);
		int month = Integer.parseInt(all[1]);
		int date = Integer.parseInt(all[2]);
		
		mtime.setToNow();
		int day = mtime.weekDay;
		String dayStr = "";
		if (day == 1)
			dayStr = "一";
		if (day == 2)
			dayStr = "二";
		if (day == 3)
			dayStr = "三";
		if (day == 4)
			dayStr = "四";
		if (day == 5)
			dayStr = "五";
		if (day == 6)
			dayStr = "六";
		if (day == 0)
			dayStr = "日";

		// return new String(year+"年"+ month+"月"+date+"日"+" 星期"+day);
		return new String(year + "-" + month + "-" + date + " "
				+ mContext.getResources().getString(R.string.week) + dayStr);
	}

	public String getTime() {
//		Log.d(TAG, "----lwj--- getTime() ");
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
		String all[] = sdf.format(new Date()).split(":");
		String hour = all[0];
		String min = all[1];
		return new String(hour + ":" + min);
	}

}
