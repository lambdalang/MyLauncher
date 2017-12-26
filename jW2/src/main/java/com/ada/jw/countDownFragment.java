package com.ada.jw;

import java.util.Timer;
import java.util.TimerTask;

import com.ada.mcu.service.Beep;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class countDownFragment extends Fragment {

	private int i = 4;
	private Timer mTimer;
	private TextView TV_CountDown;
	private setActivity currentActivity;
	private View view;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		
		currentActivity = (setActivity)activity;
	}
	
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {

			Log.i("msg0∂® ±∆˜", "" + i);
			switch (msg.what) {
			case 0:
				break;
			case 1:
				Beep.beep(getActivity().getApplication());
				if (currentActivity.countDown == 6) {
					currentActivity.switchFragment = 6;
					currentActivity
							.switchFragment(currentActivity.switchFragment);
				} else if (currentActivity.countDown == 5) {
					currentActivity.switchFragment = 5;
					currentActivity
							.switchFragment(currentActivity.switchFragment);
				}

				break;
			case 2:
				Beep.beep(getActivity().getApplication());
				TV_CountDown.setText("" + i);
				break;
			case 3:
				Beep.beep(getActivity().getApplication());
				TV_CountDown.setText("" + i);
				break;
			case 4:
				TV_CountDown.setText("" + i);
			default:
				break;
			}
		};
	};

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.activity_count_down, null, false);

		return view;
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
//		if (currentActivity.data.getTimeCount() > 0) {
//		currentActivity.switchFragment = 6;
//		currentActivity.switchFragment(currentActivity.switchFragment);
	//	return;
//	}
		findViewById(view);

		mTimer = new Timer();
		mTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub

					Message message = Message.obtain();
					message.what = i--;
					mHandler.sendMessage(message);
					if (i == 0) {
						mTimer.cancel();
					}
			}
		}, 0, 1000);
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();

		i = 4;
		currentActivity.RL_commenTop.setVisibility(View.VISIBLE);
	}

/*
 * ≤È’“id
 */
	private void findViewById(View view) {
		
		currentActivity.isOrNoCountdown = true;
		currentActivity.RL_commenTop = currentActivity.findViewById(R.id.RL_commenTop);
		currentActivity.RL_commenTop.setVisibility(View.INVISIBLE);
		

		TV_CountDown = (TextView) view.findViewById(R.id.TV_CountDown);
	}
}
