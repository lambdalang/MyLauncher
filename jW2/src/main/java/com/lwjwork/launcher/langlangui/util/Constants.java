package com.lwjwork.launcher.langlangui.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Constants {
	public static final String ADD_APP_RECORD_ACTIVITY_NAME = "add_app_record_activity_name";
	public static final String ADD_APP_RECORD_DB = "ADD_APP_RECORD_DB.DB";
	public static final String ADD_APP_RECORD_PACKAGE_NAME = "add_app_record_package_name";
	public static final String ADD_APP_RECORD_TB = "add_app_record_tb";
	public static final String ADD_APP_RECORD_TYPE = "add_app_record_type";
	public static final String CODE_ZH = "CN";
	public static boolean FIRST_UPDATE_APP = false;
	public static final String[] IMAGE_TYPES = { "bmp", "png", "jpg" };
	public static boolean INDEX_HAVEING_PARSING = false;
	public static final int MAIN_LUNCHER_APP_TYPE = 8;
	public static String NOW_WEATHER;
	public static String NOW_WEATHER_TEMP;
	public static final String[] SCREEN_SAVER_SCAN_DIRS;
	public static final int TYPE_GAME = 4;
	public static final int TYPE_KID = 3;
	public static final int TYPE_MOVIE = 1;
	public static final int TYPE_MUSIC = 2;
	public static final int TYPE_OTHER = 5;
	public static final int TYPE_TV = 0;// //
	public static String WEATHER_CITY_CODE;
	public static String WENTHER_CITY;
	public static int WERTHER_ICON_DAY_DATA;
	public static int WERTHER_ICON_NIGHT_DATA;
	public static final Map<String, Integer> customIconMap;
	public static final Map<String, String> filterActivity;
	public static final Map<String, String> filterApp = new HashMap();
	public static final Map<String, String> month;
	public static final Map<Integer, Integer> weather;

	static {
		filterActivity = new HashMap();
		month = new HashMap();
		weather = new HashMap();
		filterApp.put("hk.com.dycx.cleanram", null);
		filterApp.put("com.android.settings", null);
		filterApp.put("com.android.contacts", null);
		filterApp.put("com.android.development", null);
		filterApp.put("hk.com.dycx.serialport", null);
		filterApp.put("com.adobe.flashplayer", null);
		if (!new File("/dev/video0").exists())
			filterActivity.put("com.android.camera.CameraLauncher", null);
		INDEX_HAVEING_PARSING = true;
		FIRST_UPDATE_APP = false;
		WERTHER_ICON_DAY_DATA = 54;
		WERTHER_ICON_NIGHT_DATA = 54;
		customIconMap = new HashMap();
		SCREEN_SAVER_SCAN_DIRS = new String[] { "/system/etc/metroui/screensaver",
				"/mnt/sdcard/crystal_beach", "/mnt/extsd/crystal_beach",
				"/mnt/usbhost0/crystal_beach", "/mnt/usbhost1/crystal_beach",
				"/mnt/usbhost2/crystal_beach" };
	}
}