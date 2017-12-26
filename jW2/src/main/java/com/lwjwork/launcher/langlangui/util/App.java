package com.lwjwork.launcher.langlangui.util;

import android.graphics.drawable.Drawable;

public class App {
	private String activityName;
	private Drawable icon;
	private boolean isAdd;
	private String name;
	private String packageName;

	public String getActivityName() {
		return activityName;
	}

	public Drawable getIcon() {
		return icon;
	}

	public String getName() {
		return name;
	}

	public String getPackageName() {
		return packageName;
	}

	public boolean isAdd() {
		return isAdd;
	}

	public void setActivityName(String paramString) {
		activityName = paramString;
	}

	public void setIcon(Drawable paramDrawable) {
		icon = paramDrawable;
	}

	public void setIsAdd(boolean paramBoolean) {
		isAdd = paramBoolean;
	}

	public void setName(String paramString) {
		name = paramString;
	}

	public void setPackageName(String paramString) {
		packageName = paramString;
	}
}
