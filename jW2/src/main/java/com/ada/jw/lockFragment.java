package com.ada.jw;

import com.ada.mcu.service.SettingData;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class lockFragment extends Fragment implements OnClickListener{
	
	private setActivity currentActivity;
	private View view;
	
	private EditText ET_lock;
	private Button BT_ok;
	private String getPassword = "";
	
public static lockFragment newInstance(Activity mActivity){
		
	lockFragment mLockFragment = new lockFragment();
	mLockFragment.currentActivity = (setActivity)mActivity;
		
		return mLockFragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		view = inflater.inflate(R.layout.locked, null, false);
		return view;
	}
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		findViewById(view);
	
		BT_ok.setOnClickListener(this);
	}

	private void findViewById(View view) {
		// TODO Auto-generated method stub
		
		ET_lock = (EditText)view.findViewById(R.id.ET_lock);
		BT_ok = (Button)view.findViewById(R.id.BT_ok);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.BT_ok:
			unlock();
			break;

		default:
			break;
		}
	}
	
	private void unlock(){
		

		getPassword = ET_lock.getText().toString().trim();
		String password = ""+currentActivity.password;
		Log.i("WDHlock", "WDH"+getPassword);
		Log.i("WDHpassword", password);
		if (getPassword.equals(password)) {
					ET_lock.setText("");
					getPassword = "";
					currentActivity.setDistanceLimit = 0;
					currentActivity.lockDistance = 0;
					currentActivity.lock = false;//设置不锁机
					currentActivity.locked = false;//当前状态为非锁机状态
					currentActivity.setPasswordFlieDate();
					currentActivity.switchFragment = 0;
					currentActivity.switchFragment(currentActivity.switchFragment);
		}else {
			ET_lock.setText("");
				getPassword = "";
			AlertDialog.Builder builder = new AlertDialog.Builder(
					currentActivity);
			builder.setTitle(R.string.prompt);
			builder.setMessage(R.string.EnterThePasswordMistake);
			builder.setPositiveButton(
					R.string.Confirm, null);
			builder.show();
		}
	}
}
