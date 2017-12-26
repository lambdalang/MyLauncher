package com.ada.jw;

import com.ada.mcu.service.Beep;
import com.lwjwork.launcher.langlangui.activity.AllApp;
import com.lwjwork.launcher.langlangui.util.App;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;

public class ParameterSettingFragment extends Fragment {

	private ImageButton IB_factoryPatternSet;
	private ImageButton IB_androidPatternSet;

	private setActivity currentActivity;
	private View view;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
			
		currentActivity = (setActivity)activity;
		currentActivity.getPasswordFileDate();
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	        
			view = inflater.inflate(R.layout.parameter_set, null, false);
    		findViewById(view);
		Log.i("fragment  onCreateView()", "fragment  执行了onCreateView()");
			return view;
	
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		Log.i("fragment  onStart()", "fragment  执行了onStart()");

		inItUi();
		listionEvent();
		currentActivity.switchFragment(currentActivity.switchFragment);
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i("fragment  onResume()", "fragment  执行了onResume()");
		
	
	}
	
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
		Log.i("fragment  onStop()", "fragment  执行了onStop()");
	
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		Log.i("fragment  onDestroy()", "fragment  执行了onDestroy()");
	}

	/*
	 * 初始化界面
	 */
	private void inItUi() {
		currentActivity.isOrNoAtsetFragment = false;
		currentActivity.setPasswordFlieDate();
	}

	/*
	 * 监听
	 */
	private void listionEvent() {

		IB_factoryPatternSet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				currentActivity.switchFragment = 6;
				if (CommonUtils.isFastDoubleClick()) {
				return;
				}else {

				Beep.beep(currentActivity.getApplicationContext());
				if (currentActivity.mMachineData.currentStatus == 0) {

					final EditText inputPassword = new EditText(currentActivity);
					AlertDialog.Builder builder = new AlertDialog.Builder(
							currentActivity);
					builder.setTitle(R.string.PleaseEnterThePassword)
							.setIcon(android.R.drawable.ic_dialog_info)
							.setView(inputPassword)
							.setNegativeButton(R.string.cancle, null);
					builder.setPositiveButton(R.string.Confirm,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub

									currentActivity.getPassword = inputPassword
											.getText().toString().trim();// 得到输入的数值
									if (stringNoHaveLetter(currentActivity.getPassword)) {

									} else {
										// int getPasswordInt =
										// Integer.parseInt(getPassword);
										// Log.i("getPassword",
										// ""+getPasswordInt);
										// Log.i("Password", ""+password);
									
										String passwordString = ""
												+ currentActivity.password;
										if (currentActivity.getPassword
												.equals(passwordString)) {
											Log.i("getPassword",
													currentActivity.getPassword);
											Log.i("passwordString",
													passwordString);
											currentActivity.getPassword = "";

											currentActivity.switchFragment = 10;
											currentActivity
													.switchFragment(currentActivity.switchFragment);

										} else {
											currentActivity.getPassword = "";
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
							});
					builder.show();
				} else {

					AlertDialog.Builder builder = new AlertDialog.Builder(
							currentActivity);
					builder.setTitle(R.string.prompt);
					builder.setTitle(R.string.TheStandbystateCouldNotEnterTheFactoryPattern);
					builder.setPositiveButton(R.string.Confirm, null);
					builder.show();
				}
			}
		}
	});

		IB_androidPatternSet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				currentActivity.switchFragment = 6;
				Beep.beep(currentActivity.getApplicationContext());
				Intent intent = new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
				startActivity(intent);
			}
		});
	}

	/*
	 * 查找id
	 */
	private void findViewById(View view) {

		IB_factoryPatternSet = (ImageButton) view
				.findViewById(R.id.IB_factoryPatternSet);
		IB_androidPatternSet = (ImageButton) view
				.findViewById(R.id.IB_androidPatternSet);
	}

	/*
	 * 判断字符串是否包含字母
	 */
	private boolean stringNoHaveLetter(String mString) {

		boolean isLetter = true;
		for (int i = 0; i < mString.length(); i++) {
			if (Character.isLetter(mString.charAt(i))) {

				String m = "mtrue";
				AlertDialog.Builder builder = new AlertDialog.Builder(
						currentActivity);
				builder.setTitle(R.string.prompt);
				builder.setMessage(R.string.PasswordCannotContainLettersPleaseEnterAgain);
				builder.setPositiveButton(R.string.Confirm, null);
				builder.show();
				Log.i("是否包含字母", m);
				isLetter = true;
				break;
			} else {
				isLetter = false;
			}
		}
		return isLetter;
	}
}
