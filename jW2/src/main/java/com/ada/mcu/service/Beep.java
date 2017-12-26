package com.ada.mcu.service;

import android.content.Context;
import android.content.Intent;

public class Beep {

	public static void beep(Context context) {

		context.sendBroadcast(new Intent("com.ada.service.beep"));
	}

}
