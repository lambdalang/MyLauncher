package com.ada.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.util.Log;
import android.view.View;

import com.ada.VerticalSeekBar.VerticalSeekBar;

public class VolumeSeekBar {
	private VerticalSeekBar seekBar;
	private AudioManager mAudioManager;
	private static boolean fromReceiver = false;
	private static int volumeFromReceiver = 0;
	private static final int MULTIPLYING_POWER = 1;
	
	private BroadcastReceiver volumeReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			Log.i("LYJ", "action:" + action);
			if(action.equals("com.mcu.service.music.volume")) {
				int volume = intent.getIntExtra("volume", 99999);
				Log.i("LYJ", "volumeReceiver:" + volume);
				if(volume != 99999) {
					fromReceiver = true;
					volumeFromReceiver = volume;
				}
			}
		}
		
	};
	
	public VolumeSeekBar(Context context, View v, int resId) {
		initReceiver(context);
		seekBar = (VerticalSeekBar) v.findViewById(resId);
		mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		seekBar.setMax(mAudioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM) 
				* MULTIPLYING_POWER);
        seekBar.setProgress(mAudioManager.getStreamVolume(AudioManager.STREAM_SYSTEM));
        seekBar.setOnSeekBarChangeListener(new VerticalSeekBar.OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(VerticalSeekBar VerticalSeekBar,
					int progress, boolean fromUser) {
				// TODO Auto-generated method stub
				mAudioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, progress / MULTIPLYING_POWER , 0);
			}

			@Override
			public void onStartTrackingTouch(VerticalSeekBar VerticalSeekBar) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(VerticalSeekBar VerticalSeekBar) {
				// TODO Auto-generated method stub
				
			}
        }); 
	}
	
	private void initReceiver(Context context) {
		// TODO Auto-generated method stub
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.mcu.service.music.volume");
		context.registerReceiver(volumeReceiver, filter);
	}

	public void dismiss() {
		
	}
	
	public void show() {
		if(fromReceiver) {
			seekBar.setProgress(volumeFromReceiver);
			mAudioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, volumeFromReceiver, 0);
		}else {
			Log.i("AudioManagerL", "jw getStreamVolume:" + mAudioManager.getStreamVolume(AudioManager.STREAM_SYSTEM));
			seekBar.setProgress(mAudioManager.getStreamVolume(AudioManager.STREAM_SYSTEM));	
		}
	}

}
