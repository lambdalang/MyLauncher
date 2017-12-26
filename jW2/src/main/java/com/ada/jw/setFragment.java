package com.ada.jw;

import com.ada.mcu.service.Beep;

import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.sip.SipAudioCall.Listener;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class setFragment extends Fragment {

	private ImageButton ib_set;
	private ImageButton ib_usb;
	private ImageButton ib_music;
	private ImageButton IB_Av;
	private ImageButton ib_mode_now;
	private ImageButton ib_movie;
	private ImageButton IB_Tv;
	private ImageButton IB_explorer;
	private ImageButton IB_HDMI;

	private setActivity currentActivity;
	private final String EXTRA_WINDOW_SHOW = "com.ada.service.extrawindow.SHOW";

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View mView = inflater.inflate(R.layout.activity_set, container, false);
		findId(mView);
		return mView;
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		currentActivity = (setActivity) getActivity();
		
		listionEvent();
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		currentActivity.slidingDrawerSet.close();
	}
	
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
	}

	private void findId(View mView) {

		ib_set = (ImageButton) mView.findViewById(R.id.ib_set);
		ib_usb = (ImageButton) mView.findViewById(R.id.ib_usb);
		ib_music = (ImageButton) mView.findViewById(R.id.ib_music);
		IB_Av = (ImageButton) mView.findViewById(R.id.IB_Av);
		ib_mode_now = (ImageButton) mView.findViewById(R.id.ib_mode_now);
		ib_movie = (ImageButton) mView.findViewById(R.id.ib_movie);
		IB_Tv = (ImageButton) mView.findViewById(R.id.IB_Tv);
		IB_explorer = (ImageButton) mView.findViewById(R.id.IB_explorer);
		IB_HDMI = (ImageButton) mView.findViewById(R.id.IB_HDMI);
		// LL_BackAndPause.setVisibility(View.GONE);
		// RL_activity_count_down =
		// (RelativeLayout)mView.findViewById(R.id.activity_count_down);
		// RL_Set = (RelativeLayout)mView.findViewById(R.id.RL_Set);
	}

	/*
	 * 监听事件
	 */
	private void listionEvent() {
		final Context context = getActivity();
		final Intent bIntent = new Intent();

		ib_set.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				currentActivity.switchFragment = 1;
				currentActivity.switchFragment(currentActivity.switchFragment);
			}
		});

		IB_HDMI.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(context);
				// TvCommonManager.getInstance().setInputSource(24);
				Intent newIntent = new Intent(
						"com.mstar.tv.tvplayer.ui.intent.action.SOURCE_CHANGE");
				newIntent.putExtra("inputSrc", 23);
				newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(newIntent);
				try {
					Intent targetIntent;

					targetIntent = new Intent(
							"mstar.tvsetting.ui.intent.action.RootActivity");
					targetIntent.putExtra("task_tag", "input_source_changed");
					targetIntent.putExtra("no_change_source", true);
					targetIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
							| Intent.FLAG_ACTIVITY_TASK_ON_HOME);
					if (targetIntent != null)
						startActivity(targetIntent);

					Intent sourceInfoIntent = new Intent(
							"com.mstar.tv.tvplayer.ui.intent.action.SOURCE_INFO");
					sourceInfoIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(sourceInfoIntent);
					// if((index >= 2 || index <= 4) || index == 9)
					// updateHandler.sendEmptyMessage(mWindowData.SHOW_POPUPWINDOW);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		ib_mode_now.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				Log.i("out", "点击到现在模式");

				if (currentActivity.mMachineData.currentStatus == 1) {

					AlertDialog.Builder builder = new AlertDialog.Builder(
							currentActivity);
					builder.setTitle(R.string.prompt)
							.setMessage("现在是运行状态，禁止切换模式！！")
							.setPositiveButton(R.string.Confirm, null);
					builder.show();
				} else if (currentActivity.mMachineData.currentStatus == 3) {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							currentActivity);
					builder.setTitle(R.string.prompt);
					builder.setMessage("现在是自检状态，禁止切换模式！！");
					builder.setPositiveButton(R.string.Confirm, null);
					builder.show();
				} else {
					currentActivity.isOrNoAtsetFragment = false;
					currentActivity.switchFragment = 2;
					currentActivity
							.switchFragment(currentActivity.switchFragment);
				}
			}
		});

		ib_movie.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("out", "点击到电影");

				Beep.beep(getActivity().getApplication());
				Intent intent = new Intent();        
		        intent.setAction("android.intent.action.VIEW");    
		        intent.setClassName("tv.pps.tpad","tv.pps.tpad.WelcomeActivity");  
		  	    startActivity(intent);
				
//				/**webView播放**/
//				currentActivity.switchFragment = 12;
//				currentActivity
//						.switchFragment(currentActivity.switchFragment);
//				
			}
		});

		IB_explorer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("out", "点击到浏览器");
				Beep.beep(getActivity().getApplication());
				 Uri uri = Uri.parse("http://www.hao123.com/");
				 Intent it = new Intent(Intent.ACTION_VIEW, uri);
				 startActivity(it);

//				currentActivity.switchFragment = 11;
//				currentActivity.switchFragment(currentActivity.switchFragment);
			}
		});

		ib_music.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stu
				Log.i("out", "点击到音乐");

				Beep.beep(context);
				ComponentName Music = new ComponentName("com.baidu.music.pad",
						"com.baidu.music.pad.launch.LaunchActivity");
				Intent intent = new Intent();
				intent.setComponent(Music);
				startActivity(intent);
				// Uri uri = Uri.parse("http://music.baidu.com/");
				// Intent it = new Intent(Intent.ACTION_VIEW, uri);
				// startActivity(it);
			}
		});

		ib_usb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("out", "点击到USB");

				Beep.beep(context);
				// ComponentName Usb = new ComponentName("com.jrm.localmm",
				// "com.jrm.localmm.ui.main.FileBrowserActivity");
				ComponentName Usb = new ComponentName(
						"com.mxtech.videoplayer.ad",
						"com.mxtech.videoplayer.ad.ActivityMediaList");
				Intent intent = new Intent();
				intent.setComponent(Usb);
				startActivity(intent);
				// Intent intent= new
				// Intent(SetActivity.this,UsbActivity.class);
				// startActivity(intent);
			}
		});

		IB_Av.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(context);
				// TvCommonManager.getInstance().setInputSource(2);
				Intent newIntent = new Intent(
						"com.mstar.tv.tvplayer.ui.intent.action.SOURCE_CHANGE");
				newIntent.putExtra("inputSrc", 2);
				newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(newIntent);
				try {
					Intent targetIntent;

					targetIntent = new Intent(
							"mstar.tvsetting.ui.intent.action.RootActivity");
					targetIntent.putExtra("task_tag", "input_source_changed");
					targetIntent.putExtra("no_change_source", true);
					targetIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
							| Intent.FLAG_ACTIVITY_TASK_ON_HOME);
					if (targetIntent != null)
						startActivity(targetIntent);

					Intent sourceInfoIntent = new Intent(
							"com.mstar.tv.tvplayer.ui.intent.action.SOURCE_INFO");
					sourceInfoIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(sourceInfoIntent);
					// if((index >= 2 || index <= 4) || index == 9)
					// updateHandler.sendEmptyMessage(mWindowData.SHOW_POPUPWINDOW);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		IB_Tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(context);
				
				Intent newIntent = new Intent(
						"com.mstar.tv.tvplayer.ui.intent.action.SOURCE_CHANGE");
				newIntent.putExtra("inputSrc", 1);
				newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(newIntent);
				try {
					Intent targetIntent;
					targetIntent = new Intent(
							"mstar.tvsetting.ui.intent.action.RootActivity");
					targetIntent.putExtra("task_tag", "input_source_changed");
					targetIntent.putExtra("no_change_source", true);
					targetIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
							| Intent.FLAG_ACTIVITY_TASK_ON_HOME);
					if (targetIntent != null)
						startActivity(targetIntent);

					Intent sourceInfoIntent = new Intent(
							"com.mstar.tv.tvplayer.ui.intent.action.SOURCE_INFO");
					sourceInfoIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(sourceInfoIntent);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
