package com.lwjwork.launcher.langlangui.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.lwjwork.launcher.langlangui.activity.AllApp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.util.Log;

public final class LoadApp {
	private static final String TAG = LoadApp.class.getName();
	private static List<App> apps = new ArrayList<App>();
	private static Context context;
	private static LoadApp instance;
	private static Intent intent = new Intent("android.intent.action.MAIN");
	private static PackageManager pm;
	private static List<ResolveInfo> resolveInfos;

	public static LoadApp getInstance(Context ctx) {
		if (instance == null)
			instance = new LoadApp();
		context = ctx;
		pm = context.getPackageManager();
		resolveInfos = pm.queryIntentActivities(intent, PackageManager.GET_ACTIVITIES);
		return instance;
	}

	public LoadApp() {
		intent.addCategory("android.intent.category.LAUNCHER");
	}

	public List<App> getAllApp() {
		apps.clear();
		ActivityInfo activityInfo;

		if (resolveInfos != null) {
			int i;
			for (i = 0; i < resolveInfos.size(); i++) {
				activityInfo = ((ResolveInfo) resolveInfos.get(i)).activityInfo;
				String str = ((ResolveInfo) resolveInfos.get(i)).activityInfo.packageName;
				Drawable icon;
				icon = activityInfo.loadIcon(pm);

				App app = new App();
				app.setPackageName(str);
				app.setActivityName(activityInfo.name);
				app.setIcon(icon);
				CharSequence name = activityInfo.loadLabel(pm);

				app.setName(name.toString());

				if (app.getPackageName() != null
						&& app.getPackageName().contains("com.haier.showallapps")) {
					// fitler some appFF
					continue;
				} else
					apps.add(app);
			}
		}
		return apps;
	}

}