package com.ada.jw;

import java.text.DecimalFormat;

import com.ada.mcu.service.Beep;
import com.ada.mcu.service.SettingData;

import android.R.anim;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FactoryPatternSetFragment extends Fragment {

	public static FactoryPatternSetFragment newInstance(Activity mActivity) {

		FactoryPatternSetFragment mFactoryPatternSetFragment = new FactoryPatternSetFragment();
		mFactoryPatternSetFragment.currentActivity = (setActivity) mActivity;
		return mFactoryPatternSetFragment;
	}

	private setActivity currentActivity;
	private View view;

	protected Thread mThread;
	private boolean isRuning = true;

	private int newPassword = 0;
	private int getOldPassWord = 0;
	private boolean isOrNoChangeNewPassword = false;

	// 用于存储键盘输入的值，点击enter再把这个值赋给默认变量
	public int defaultSingTemp;
	private int defaultMinSpeedTemp;
	private int defaultMaxSpeedTemp;
	private int defaultMaxLnclineTemp;
	private int defaultSpeedTimeTemp;
	private int defaultWhellTemp;
	private int defaultAutoOilDistanceTemp;
	private int defaultoilTimeTemp;
	private int defaultAddOliNumberTemp;

	private String defaultMinSpeedTempString;
	private String defaultMaxSpeedTempString;
	private String defaultWheelTempString;
	private int setDistanceLimitTemp;
	private int setNumberSectionSpeedTemp;

	private boolean defaultSingBool = false;
	private boolean defaultMinSpeedBool = false;
	private boolean defaultMaxSpeedBool = false;
	private boolean defaultMaxLnclineBool = false;
	private boolean defaultSpeedTimeBool = false;
	private boolean defaultWhellBool = false;
	private boolean defaultAutoOilDistanceBool = false;
	private boolean defaultoilTimeBool = false;
	private boolean defaultAddOliNumberBool = false;

	private boolean setDistanceLimitBool = false;

	private String defaultAutoOilDistanceString;
	private String defaultoilTimeString;
	private String setNumberSectionSpeedString;
	private String defaultWhellString;

	// 锟斤拷锟斤拷模锟斤拷
	private String version;
	private TextView TV_showVersions;

	// 选锟斤拷锟斤拷9锟斤拷锟叫碉拷锟斤拷一锟斤拷
	private View RL_SIENG;
	private View RL_minSpeed;
	private View RL_maxSpeed;
	private View RL_maxIncline;
	private View RL_speedTime;
	private View RL_whell;
	private View RL_autoOil;
	private View RL_oilTime;
	private View RL_language;

	// 4个界面
	private View FactoryPatternset_elevator;
	private View Password_Close;
	private View RL_PasswordOpen;
	private View LL_showDate;

	// 9个框
	private TextView TV_SIENG;
	private TextView TV_minSpeed;
	private TextView TV_maxSpeed;
	private TextView TV_maxIncline;
	private TextView TV_speedTime;
	private TextView TV_whell;
	private TextView TV_autoOil;
	private TextView TV_oilTime;
	private TextView TV_addOilNumber;

	// 右边5个切换视图
	private TextView TV_generalSet;
	private TextView TV_elevator;
	private TextView TV_password;
	private TextView TV_recoverSet;
	private TextView TV_exit;
	private TextView TV_addOil;
	private TextView totalDistanceTextView;
	private TextView totalTimeTextView;

	// 锟斤拷锟斤拷锟斤拷锟斤拷
	private TextView TV_run;
	private TextView TV_reset;
	private TextView TV_setNumber;
	private TextView TV_stepOneDetection;
	private TextView TV_stepTwoDetection;
	// 锟截憋拷锟斤拷锟斤拷
	private ImageButton IB_closePasswordSet;
	// private TextView TV_saveClosePassword;

	// 锟斤拷锟斤拷锟斤拷
	private ImageButton IB_openPasswordSet;
	private TextView TV_setDistance;
	private TextView TV_oldPasswordShow;
	private TextView TV_newPasswordShow;
	private TextView TV_openPasswordSave;

	// 做标记，以便键盘显示到某个上面
	final private int SIENG = 1;
	final private int minSpeed = 2;
	final private int maxSpeed = 3;
	final private int maxIncline = 4;
	final private int speedTime = 5;
	final private int whell = 6;
	final private int autoOil = 7;
	final private int oilTime = 8;
	final private int addOilNumber = 9;

	final private int setNumber = 10;
	final private int setDistance = 11;
	final private int oldPasswordShow = 12;
	final private int newPasswordShow = 13;

	private int choice;

	private Button Btn_One;
	private Button Btn_Two;
	private Button Btn_Three;
	private Button Btn_Four;
	private Button Btn_Five;
	private Button Btn_Six;
	private Button Btn_Seven;
	private Button Btn_Eight;
	private Button Btn_Nine;
	private Button Btn_Point;
	private Button Btn_Zero;
	private Button Btn_Enter;
	private Button Btn_Delete;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		view = inflater.inflate(R.layout.factorypatternset, null, false);
		findViewById(view);
		return view;
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		inItUi();
		listionEvent();
		
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
			
		currentActivity.RL_commenTop.setVisibility(View.VISIBLE);
		setActivity.isOrNoFactoryPatternSetFragment = false;
		currentActivity.setPasswordFlieDate();
		
		setDistanceLimitTemp = 0; 
	}

	/*
	 * 监听事件
	 */
	private void listionEvent() {
		// TODO Auto-generated method stub

		// 一般模式
		TV_generalSet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				Password_Close.setVisibility(View.GONE);
				RL_PasswordOpen.setVisibility(View.GONE);
				FactoryPatternset_elevator.setVisibility(View.GONE);
				LL_showDate.setVisibility(View.VISIBLE);

				TV_generalSet.setBackgroundResource(R.drawable.set_blue);
				TV_elevator.setBackgroundResource(R.drawable.set_black);
				TV_password.setBackgroundResource(R.drawable.set_black);
				TV_recoverSet.setBackgroundResource(R.drawable.set_black);
			}
		});

		// 升降机
		TV_elevator.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				Password_Close.setVisibility(View.GONE);
				RL_PasswordOpen.setVisibility(View.GONE);
				FactoryPatternset_elevator.setVisibility(View.VISIBLE);
				LL_showDate.setVisibility(View.GONE);

				TV_setNumber
						.setText("" + setActivity.setNumberSectionSpeed);

				TV_elevator.setBackgroundResource(R.drawable.set_blue);
				TV_generalSet.setBackgroundResource(R.drawable.set_black);
				TV_password.setBackgroundResource(R.drawable.set_black);
				TV_recoverSet.setBackgroundResource(R.drawable.set_black);
			}
		});

		// 密码
		TV_password.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				Password_Close.setVisibility(View.VISIBLE);
				RL_PasswordOpen.setVisibility(View.GONE);
				FactoryPatternset_elevator.setVisibility(View.GONE);
				LL_showDate.setVisibility(View.GONE);

				TV_password.setBackgroundResource(R.drawable.set_blue);
				TV_elevator.setBackgroundResource(R.drawable.set_black);
				TV_generalSet.setBackgroundResource(R.drawable.set_black);
				TV_recoverSet.setBackgroundResource(R.drawable.set_black);
			}
		});

		// //恢复出厂设置
		TV_recoverSet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				recoverSet();

				Password_Close.setVisibility(View.GONE);
				RL_PasswordOpen.setVisibility(View.GONE);
				FactoryPatternset_elevator.setVisibility(View.GONE);
				LL_showDate.setVisibility(View.VISIBLE);

				TV_generalSet.setBackgroundResource(R.drawable.set_black);
				TV_elevator.setBackgroundResource(R.drawable.set_black);
				TV_password.setBackgroundResource(R.drawable.set_black);
				TV_recoverSet.setBackgroundResource(R.drawable.set_blue);

				TV_SIENG.setText("" + setActivity.defaultSing);
				TV_minSpeed.setText("" + setActivity.defaultMinSpeed);
				TV_maxSpeed.setText("" + setActivity.defaultMaxSpeed);
				TV_maxIncline.setText("" + setActivity.defaultMaxLncline);
				TV_speedTime.setText("" + setActivity.defaultSpeedTime);
				TV_whell.setText("" + setActivity.defaultWhell);
				TV_autoOil.setText("" + setActivity.defaultAutoOilDistance);
				TV_oilTime.setText("" + setActivity.defaultoilTime);
				TV_addOilNumber.setText(""
						+ setActivity.defaultAddOliNumber);
			}
		});

		// 关闭密码功能
		IB_closePasswordSet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				TV_setDistance.setText("" + setActivity.setDistanceLimit);
				RL_PasswordOpen.setVisibility(View.VISIBLE);
				Password_Close.setVisibility(View.GONE);
				FactoryPatternset_elevator.setVisibility(View.GONE);
				LL_showDate.setVisibility(View.GONE);
			}
		});

		// 打开密码功能
		IB_openPasswordSet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				RL_PasswordOpen.setVisibility(View.GONE);
				Password_Close.setVisibility(View.VISIBLE);
				FactoryPatternset_elevator.setVisibility(View.GONE);
				LL_showDate.setVisibility(View.GONE);
			}
		});

		// 退出
		TV_exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				isRuning = false;
				
				setActivity.switchFragment = 6;
				currentActivity.switchFragment(setActivity.switchFragment);
			}
		});

		// 手动加油
		TV_addOil.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				
				try {
					if (null != currentActivity.mService) {
						currentActivity.mService.beginOilTime();
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				AlertDialog.Builder builder = new AlertDialog.Builder(
						currentActivity);
				builder.setTitle(R.string.prompt);
				builder.setMessage(R.string.addOiling);
				builder.setPositiveButton(
						R.string.Confirm, null);
				builder.show();
			}
		});

		RL_SIENG.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				TV_SIENG.setText("");
				choice = SIENG;
				defaultSingBool = true;
				setActivity.TempFactoryPatternSet += "";// 锟叫硷拷锟斤拷锟斤拷锟斤拷锟�
			}
		});

		RL_minSpeed.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				TV_minSpeed.setText("");
				choice = minSpeed;
				defaultMinSpeedBool = true;
				setActivity.TempFactoryPatternSet = "";// 锟叫硷拷锟斤拷锟斤拷锟斤拷锟�
			}
		});

		RL_maxSpeed.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				TV_maxSpeed.setText("");
				choice = maxSpeed;
				defaultMaxSpeedBool = true;
				setActivity.TempFactoryPatternSet = "";//
			}
		});

		RL_maxIncline.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				TV_maxIncline.setText("");
				choice = maxIncline;
				defaultMaxLnclineBool = true;
				setActivity.TempFactoryPatternSet = "";// 锟叫硷拷锟斤拷锟斤拷锟斤拷锟�
			}
		});

		RL_speedTime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				TV_speedTime.setText("");
				choice = speedTime;
				defaultSpeedTimeBool = true;
				setActivity.TempFactoryPatternSet = "";// 锟叫硷拷锟斤拷锟斤拷锟斤拷锟�
			}
		});

		RL_whell.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				TV_whell.setText("");
				choice = whell;
				defaultWhellBool = true;
				setActivity.TempFactoryPatternSet = "";// 锟叫硷拷锟斤拷锟斤拷锟斤拷锟�
			}
		});

		RL_autoOil.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				TV_autoOil.setText("");
				choice = autoOil;
				defaultAutoOilDistanceBool = true;
				setActivity.TempFactoryPatternSet = "";// 锟叫硷拷锟斤拷锟斤拷锟斤拷锟�
			}
		});

		RL_oilTime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				TV_oilTime.setText("");
				choice = oilTime;
				defaultoilTimeBool = true;
				setActivity.TempFactoryPatternSet = "";// 键盘存储值清零
			}
		});

		RL_language.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				TV_addOilNumber.setText("");
				choice = addOilNumber;
				defaultAddOliNumberBool = true;
				setActivity.TempFactoryPatternSet = "";
			}
		});

		/*
		 * 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
		 */
		TV_setNumber.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				TV_setNumber.setText("");
				choice = setNumber;
				setActivity.TempFactoryPatternSet = "";//
				TV_setNumber.setBackgroundResource(R.drawable.set_numberblue);
			}
		});

		TV_run.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				if (setNumberSectionSpeedTemp > 10) {
					setNumberSectionSpeedTemp = returnIntervalValue(
							setNumberSectionSpeedTemp, 30, 10);
					setActivity.setNumberSectionSpeed = setNumberSectionSpeedTemp;
				}
				TV_setNumber
						.setText("" + setActivity.setNumberSectionSpeed);
				currentActivity.setPasswordFlieDate();

				try {
					setActivity.mMachineData.setCurrentStatus(3);
					if (null != currentActivity.mService) {
						currentActivity.mService.updateToMcu(setActivity.mMachineData);
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});

		/*
		 * 锟斤拷锟斤拷锟斤拷锟斤拷锟� 锟斤拷锟斤拷
		 */

		TV_setDistance.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				TV_setDistance.setBackgroundResource(R.drawable.set_numberblue);
				choice = setDistance;
				TV_setDistance.setText("");
				setDistanceLimitBool = true;
				setActivity.TempFactoryPatternSet = "";// 锟叫硷拷锟斤拷锟斤拷锟斤拷锟�
			}
		});

		TV_oldPasswordShow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				TV_oldPasswordShow.setText("");
				TV_oldPasswordShow.setBackgroundResource(R.drawable.set_blue);
				choice = oldPasswordShow;
				setActivity.TempFactoryPatternSet = "";// 锟叫硷拷锟斤拷锟斤拷锟斤拷锟�
			}
		});

		TV_newPasswordShow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				isOrNoChangeNewPassword = true;
				TV_newPasswordShow.setText("");
				TV_newPasswordShow.setBackgroundResource(R.drawable.set_blue);

				getOldPassWord = returnIntervalValue(getOldPassWord, 999999,
						111111);
				TV_oldPasswordShow.setText(srtingToStar(("" + getOldPassWord)));

				currentActivity.getPasswordFileDate();
				Log.i("getOldPassWord", "" + getOldPassWord);
				Log.i("password", "" + setActivity.password);
				if (getOldPassWord == setActivity.password) {
					choice = newPasswordShow;
					setActivity.TempFactoryPatternSet = "";//

				} else {
					choice = oldPasswordShow;
					setActivity.TempFactoryPatternSet = "";// 锟叫硷拷锟斤拷锟斤拷锟斤拷锟�
					TV_newPasswordShow
							.setBackgroundResource(R.drawable.set_black);
					AlertDialog.Builder builder = new AlertDialog.Builder(
							currentActivity);
					builder.setTitle(R.string.prompt);
					builder.setMessage(R.string.PasswordErrorPleaseTryAgain);
					builder.setPositiveButton(R.string.Confirm, null);
					builder.show();
					TV_oldPasswordShow.setText("");
				}
			}
		});

		TV_openPasswordSave.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				TV_oldPasswordShow.setBackgroundResource(R.drawable.set_black);
				TV_newPasswordShow.setBackgroundResource(R.drawable.set_black);
				TV_openPasswordSave.setBackgroundResource(R.drawable.set_blue);

				AlertDialog.Builder builder = new AlertDialog.Builder(
						currentActivity);
				// 处理输入的新密码

				// 处理输入的设定公里数
				if (setDistanceLimitBool) {
					if (setDistanceLimitTemp >= 1) {
						setDistanceLimitTemp = returnIntervalValue(
								setDistanceLimitTemp, 100000, 1);
						currentActivity.lock = true;//锁机有效
						Log.i("setDistanceLimitTemp", ""+setDistanceLimitTemp);
						TV_setDistance.setText("" + setDistanceLimitTemp);
					} else {
						setDistanceLimitTemp = 0;
						currentActivity.lock = false;//锁机有效
					}
					setDistanceLimitBool = false;
					setActivity.setDistanceLimit = setDistanceLimitTemp;
					TV_setDistance.setText(""
							+ setActivity.setDistanceLimit);
					Log.i("setDistanceLimitTemp", ""+setDistanceLimitTemp);
					Log.i("currentActivity.lock", ""+currentActivity.lock);
					try {
						if (null != currentActivity.mService) {
						SettingData mSettingData = currentActivity.mService
								.getSettingData();
						mSettingData
								.setDistanceMax(setActivity.setDistanceLimit);
					
							currentActivity.mService.setSettingData(mSettingData);
						}		
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				Log.i("修改密码passWord", "" + newPassword);

				if (isOrNoChangeNewPassword) {

					if (newPassword > 111110) {
						newPassword = returnIntervalValue(newPassword, 999999,
								111110);
						setActivity.password = newPassword;
						TV_newPasswordShow.setText("" + newPassword);
						builder.setTitle(R.string.prompt);
						builder.setMessage(R.string.DataIsSuccessfullySaved);
						builder.setPositiveButton(R.string.Confirm, null);
						builder.show();
						isOrNoChangeNewPassword = false;
					} else {

						builder.setTitle(R.string.prompt);
						builder.setMessage("密码必须在 111111-999999之间");
						builder.setPositiveButton(R.string.Confirm, null);
						builder.show();
						TV_newPasswordShow.setText("");
						choice = 100;// 新密码不在区间内，必须重新点击新密码输入框才有效
					}

				} else {
					builder.setTitle(R.string.prompt);
					builder.setMessage(R.string.DataIsSuccessfullySaved);
					builder.setPositiveButton(R.string.Confirm, null);
					builder.show();

				}

				currentActivity.setPasswordFlieDate();
				TV_openPasswordSave.setBackgroundResource(R.drawable.set_black);
			}
		});

		/*
		 * 锟斤拷锟斤拷锟斤拷锟斤拷锟教碉拷锟斤拷录锟�
		 */
		// 锟斤拷锟� 1
		Btn_One.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				setActivity.TempFactoryPatternSet += "1";
				sortSelect();
			}
		});

		// 锟斤拷锟� 2
		Btn_Two.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				setActivity.TempFactoryPatternSet += "2";
				sortSelect();
			}
		});

		// 锟斤拷锟� 3
		Btn_Three.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				setActivity.TempFactoryPatternSet += "3";
				sortSelect();
			}
		});

		// 锟斤拷锟� 4
		Btn_Four.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				setActivity.TempFactoryPatternSet += "4";
				sortSelect();
			}
		});

		// 锟斤拷锟� 5
		Btn_Five.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				setActivity.TempFactoryPatternSet += "5";
				sortSelect();
			}
		});

		// 锟斤拷锟� 6
		Btn_Six.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				setActivity.TempFactoryPatternSet += "6";
				sortSelect();
			}
		});

		// 锟斤拷锟� 7
		Btn_Seven.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				setActivity.TempFactoryPatternSet += "7";
				sortSelect();
			}
		});

		// 锟斤拷锟� 8
		Btn_Eight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				setActivity.TempFactoryPatternSet += "8";
				sortSelect();
			}
		});

		// 锟斤拷锟� 9
		Btn_Nine.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				setActivity.TempFactoryPatternSet += "9";
				sortSelect();
			}
		});

		// 锟斤拷锟� 0
		Btn_Zero.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				setActivity.TempFactoryPatternSet += "0";
				sortSelect();
			}
		});

		// 锟斤拷锟� .
		Btn_Point.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				if (choice == 2 || choice == 3 || choice == 6) {

					if (setActivity.TempFactoryPatternSet.contains(".")) {
					} else {
						setActivity.TempFactoryPatternSet += ".";
					}
					sortSelect();
				} else {

					AlertDialog.Builder builder = new AlertDialog.Builder(
							currentActivity);
					builder.setTitle(R.string.prompt);
					builder.setMessage(R.string.ThisDataTypeCannotHaveADecimalPoint);
					builder.setPositiveButton(R.string.Confirm, null);
					builder.show();
				}
			}
		});

		// 锟斤拷锟絜nter锟斤拷
		Btn_Enter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// defaultSing =defaultSingTemp;
				// metricAndEnglishSwitch(defaultSing);

				Beep.beep(getActivity().getApplication());
				generalSetDealAndSave();
				Log.i("defaultSpeedTime11111111111", ""
						+ setActivity.defaultSpeedTime);

				AlertDialog.Builder builder = new AlertDialog.Builder(
						currentActivity);
				builder.setTitle(R.string.prompt);
				builder.setMessage(R.string.ParameterIsSetSuccessfully);
				builder.setPositiveButton(R.string.Confirm, null);
				builder.show();
			}
		});

		// 锟斤拷锟紹tn_Delete锟斤拷
		Btn_Delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				if (choice == 2 || choice == 3 || choice == 6) {

				} else {
					if (setActivity.TempFactoryPatternSet.length() > 0) {
						setActivity.TempFactoryPatternSet = setActivity.TempFactoryPatternSet
								.substring(0,
										setActivity.TempFactoryPatternSet
												.length() - 1);
						if (setActivity.TempFactoryPatternSet.length() > 0) {
							sortSelect();
						}
					}
				}
			}
		});
	}

	/*
	 * 处理一般设置输入的数据以及保存
	 */
	private void generalSetDealAndSave() {

		if (defaultSingBool) {
			defaultSingTemp = returnIntervalValue(defaultSingTemp, 1, 0);
			TV_SIENG.setText("" + defaultSingTemp);
			setActivity.defaultSing = defaultSingTemp;
			currentActivity.metricAndEnglishSwitch(setActivity.defaultSing);
			defaultSingBool = false;
		}

		if (defaultMinSpeedBool) {
			defaultMinSpeedTempString = stringToDoubleToString(
					defaultMinSpeedTempString, 6.0, 0.6);
			TV_minSpeed.setText(defaultMinSpeedTempString + "Km/h");
			setActivity.defaultMinSpeed = stringToDoubleEnlargeTenToInt(defaultMinSpeedTempString);
			setActivity.defaultMinSpeed = setActivity.defaultMinSpeed / 10;
			defaultMinSpeedBool = false;
		} else {
			defaultMinSpeedTempString = inSmallTentoDoubleToString(setActivity.defaultMinSpeed * 10);
			TV_minSpeed.setText(defaultMinSpeedTempString + "Km/h");
		}

		if (defaultMaxSpeedBool) {
			TV_maxSpeed.setText("" + defaultMaxSpeedTempString + "Km/h");
			defaultMaxSpeedTempString = stringToDoubleToString(
					defaultMaxSpeedTempString, 30.0, 10.0);
			setActivity.defaultMaxSpeed = stringToDoubleEnlargeTenToInt(defaultMaxSpeedTempString);
			setActivity.defaultMaxSpeed = setActivity.defaultMaxSpeed / 10;
			defaultMaxSpeedBool = false;
		} else {
			defaultMaxSpeedTempString = inSmallTentoDoubleToString(setActivity.defaultMaxSpeed * 10);
			TV_maxSpeed.setText("" + defaultMaxSpeedTempString + "Km/h");
		}

		if (defaultMaxLnclineBool) {
			defaultMaxLnclineTemp = returnIntervalValue(defaultMaxLnclineTemp,
					30, 10);
			TV_maxIncline.setText("" + defaultMaxLnclineTemp);
			setActivity.defaultMaxLncline = defaultMaxLnclineTemp;
			defaultMaxLnclineBool = false;
		}

		if (defaultSpeedTimeBool) {
			defaultSpeedTimeTemp = returnIntervalValue(defaultSpeedTimeTemp,
					60, 2);
			setActivity.defaultSpeedTime = defaultSpeedTimeTemp;
			TV_speedTime.setText("" + defaultSpeedTimeTemp + "s");
			defaultSpeedTimeBool = false;
		}

		if (defaultWhellBool) {
			defaultWheelTempString = stringToDoubleToString(
					defaultWheelTempString, 8.0, 1.0);
			TV_whell.setText("" + defaultWheelTempString);
			setActivity.defaultWhell = stringToDoubleEnlargeTenToInt(defaultWheelTempString);
			setActivity.defaultWhell = setActivity.defaultWhell / 10;
			defaultWhellBool = false;
		} else {
			defaultWheelTempString = inSmallTentoDoubleToString(setActivity.defaultWhell);
		}

		if (defaultAutoOilDistanceBool) {
			defaultAutoOilDistanceTemp = returnIntervalValue(
					defaultAutoOilDistanceTemp, 2000, 10);
			TV_autoOil.setText("" + defaultAutoOilDistanceTemp + "Km");
			setActivity.defaultAutoOilDistance = defaultAutoOilDistanceTemp;
			defaultAutoOilDistanceBool = false;
		}

		if (defaultoilTimeBool) {
			defaultoilTimeTemp = returnIntervalValue(defaultoilTimeTemp, 180,
					10);
			TV_oilTime.setText("" + defaultoilTimeTemp + "s");
			setActivity.defaultoilTime = defaultoilTimeTemp;
			defaultoilTimeBool = false;
		}

		if (defaultAddOliNumberBool) {
			defaultAddOliNumberTemp = returnIntervalValue(
					defaultAddOliNumberTemp, 50, 0);
			TV_addOilNumber.setText("" + defaultAddOliNumberTemp);
			setActivity.defaultAddOliNumber = defaultAddOliNumberTemp;
			defaultAddOliNumberBool = false;
		}

		try {
			if (null != currentActivity.mService) {

			SettingData mSettingData = currentActivity.mService
					.getSettingData();
			mSettingData.setMetricInch(setActivity.defaultSing);
			mSettingData.setSpeedMin(setActivity.defaultMinSpeed * 10);
			mSettingData.setSpeedMax(setActivity.defaultMaxSpeed * 10);
			mSettingData.setInclineMax(setActivity.defaultMaxLncline);
			
			mSettingData.setSpeedRatio(setActivity.defaultWhell * 10);
			mSettingData.setOilDistance(setActivity.defaultAutoOilDistance * 1000);
			mSettingData.setOilTime(setActivity.defaultoilTime);
			mSettingData.setOilCountMax(setActivity.defaultAddOliNumber);
		
			
			 mSettingData.setDistanceMax(setActivity.setDistanceLimit);

			currentActivity.mService.setSettingData(mSettingData);
			Log.i("数据已经发送", "数据已经发送");
			Log.i("getMetricInch()", "" + mSettingData.getMetricInch());
			Log.i("getSpeedMin()",""+mSettingData.getSpeedMin());
			Log.i("getSpeedMax()", "" + mSettingData.getSpeedMax());
			Log.i("getInclineMax()", "" + mSettingData.getInclineMax());
			Log.i("getDistanceMax()", "" + mSettingData.getDistanceMax());
			Log.i("getSpeedRatio()", "" + mSettingData.getSpeedRatio());
			Log.i("getOilDistance()", "" + mSettingData.getOilDistance());
			Log.i("getOilTime()", "" + mSettingData.getOilTime());
			Log.i("getOilCountMax()", "" + mSettingData.getOilCountMax());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		currentActivity.setPasswordFlieDate();
	}

	/*
	 * int缩小10倍，Double转化成字符串
	 */
	public String inSmallTentoDoubleToString(int mInt) {

		String mString = "" + mInt;
		Double mDouble = Double.parseDouble(mString);
		mDouble /= 10;

		String returnString = "" + mDouble;
		return returnString;
	}

	/*
	 * 字符串转Double,放大10倍后再返回字int
	 */
	public int stringToDoubleEnlargeTenToInt(String mString) {

		Double mDouble = Double.parseDouble(mString);
		mDouble *= 10;
		int mInt = Integer.parseInt(new java.text.DecimalFormat("0")
				.format(mDouble));
		return mInt;
	}

	/*
	 * 字符串转Double,限制最大值，最小值后再返回字符串
	 */
	public String stringToDoubleToString(String string, Double max, Double min) {

		if (string.equals(".")) {
			return "0";
		}

		Double myDouble = Double.parseDouble(string);
		if (myDouble < 1.0) {

			myDouble = (myDouble > max) ? max : myDouble;
			myDouble = (myDouble < min) ? min : myDouble;
			DecimalFormat format = new DecimalFormat("##.0");
			String mString = format.format(myDouble);
			return "0" + mString;
		}

		myDouble = (myDouble > max) ? max : myDouble;
		myDouble = (myDouble < min) ? min : myDouble;
		DecimalFormat format = new DecimalFormat("##.0");
		String mString = format.format(myDouble);

		return mString;
	}

	/*
	 * 判断点击的是那个框，把键盘的值放入对应框
	 */
	@SuppressLint("UseValueOf")
	private void sortSelect() {
		// TODO Auto-generated method stub

		switch (choice) {
		case SIENG:
			defaultSingTemp = Integer
					.parseInt(setActivity.TempFactoryPatternSet);
			TV_SIENG.setText("" + setActivity.TempFactoryPatternSet);
			break;
		case minSpeed:
			defaultMinSpeedTempString = stringToDoubleToString(
					setActivity.TempFactoryPatternSet, 6.0, 1.0);
			TV_minSpeed.setText(setActivity.TempFactoryPatternSet + "Km/h");
			Log.i("defaultMinSpeedTemp", "" + defaultMinSpeedTemp);
			break;
		case maxSpeed:
			defaultMaxSpeedTempString = stringToDoubleToString(
					setActivity.TempFactoryPatternSet, 30.0, 10.0);
			TV_maxSpeed.setText("" + setActivity.TempFactoryPatternSet
					+ "Km/h");
			break;
		case maxIncline:
			defaultMaxLnclineTemp = Integer
					.parseInt(setActivity.TempFactoryPatternSet);
			TV_maxIncline.setText("" + setActivity.TempFactoryPatternSet);
			break;
		case speedTime:
			defaultSpeedTimeTemp = Integer
					.parseInt(setActivity.TempFactoryPatternSet);
			TV_speedTime.setText("" + setActivity.TempFactoryPatternSet
					+ "s");
			break;
		case whell:
			defaultWheelTempString = stringToDoubleToString(
					setActivity.TempFactoryPatternSet, 8.0, 1.0);
			TV_whell.setText(setActivity.TempFactoryPatternSet + "Hz");
			break;
		case autoOil:
			defaultAutoOilDistanceTemp = Integer
					.parseInt(setActivity.TempFactoryPatternSet);
			TV_autoOil.setText("" + setActivity.TempFactoryPatternSet
					+ "Km");
			break;
		case oilTime:
			defaultoilTimeTemp = Integer
					.parseInt(setActivity.TempFactoryPatternSet);
			TV_oilTime
					.setText("" + setActivity.TempFactoryPatternSet + "s");
			break;
		case addOilNumber:
			defaultAddOliNumberTemp = Integer
					.parseInt(setActivity.TempFactoryPatternSet);
			TV_addOilNumber.setText("" + setActivity.TempFactoryPatternSet);
			break;
		case setNumber:
			setNumberSectionSpeedTemp = Integer
					.parseInt(setActivity.TempFactoryPatternSet);
			Log.i("setNumberSectionSpeedTemp", "" + setNumberSectionSpeedTemp);
			TV_setNumber.setText("" + setActivity.TempFactoryPatternSet);
			break;
		case setDistance:
			setDistanceLimitTemp = Integer
					.parseInt(setActivity.TempFactoryPatternSet);
			TV_setDistance.setText("" + setActivity.TempFactoryPatternSet);
			break;
		case oldPasswordShow:
			getOldPassWord = Integer
					.parseInt(setActivity.TempFactoryPatternSet);
//			TV_oldPasswordShow.setText(""
//					+ setActivity.TempFactoryPatternSet);
			TV_oldPasswordShow.setText(srtingToStar( setActivity.TempFactoryPatternSet));
			break;
		case newPasswordShow:
			newPassword = Integer
					.parseInt(setActivity.TempFactoryPatternSet);
			TV_newPasswordShow.setText(""
					+ setActivity.TempFactoryPatternSet);
			break;
		default:
			break;
		}
	}

	private String srtingToStar(String mString){
		
		String string = "";
		switch (mString.length()) {
		case 1:
			string = "*" ;
			break;
		case 2:
			string = "**" ;
			break;
		case 3:
			string = "***" ;
			break;
		case 4:
			string = "****" ;
			break;
		case 5:
			string = "*****" ;
			break;
		case 6:
			string = "******" ;
			break;
		default:
			break;
		}
		return  string;
	}

	/*
	 * 返回区间值int
	 */
	public int returnIntervalValue(int mInt, int max, int min) {

		mInt = mInt > max ? max : mInt;
		mInt = mInt < min ? min : mInt;
		return mInt;
	}

	/*
	 * 恢复出厂设置，重新初始化数据
	 */
	private void recoverSet() {

		setActivity.password = 666666;
		// 公英制标记 0:公制 1：英制
		setActivity.defaultSing = 0;
		setActivity.defaultMinSpeed = 1;
		setActivity.defaultMaxSpeed = 20;
		setActivity.defaultMaxLncline = 20;
		setActivity.defaultSpeedTime = 2;
		setActivity.defaultWhell = 5;
		setActivity.defaultAutoOilDistance = 800;
		setActivity.defaultoilTime = 10;
		setActivity.defaultAddOliNumber = 25;

		setActivity.setDistanceLimit = 0;
		setActivity.setNumberSectionSpeed = 20;

		setActivity.totalTimeValue = 0;
		setActivity.totalDistanceValue = 0;
		try {
			if (null != currentActivity.mService) {

			SettingData mSettingData = currentActivity.mService
					.getSettingData();
			mSettingData.setMetricInch(setActivity.defaultSing);
			mSettingData.setSpeedMin(setActivity.defaultMinSpeed * 10);
			mSettingData.setSpeedMax(setActivity.defaultMaxSpeed * 10);
			mSettingData.setInclineMax(setActivity.defaultMaxLncline);

			mSettingData.setOilCountMax(setActivity.defaultAddOliNumber);
			mSettingData.setSpeedRatio(setActivity.defaultWhell * 10);
			mSettingData
					.setOilDistance(setActivity.defaultAutoOilDistance * 1000);
			 mSettingData.setDistanceMax(setActivity.setDistanceLimit);

			currentActivity.mService.setSettingData(mSettingData);
			Log.i("数据已经发送", "数据已经发送");
			Log.i("getMetricInch()", "" + mSettingData.getMetricInch());
			Log.i("getSpeedMin()", "" + mSettingData.getSpeedMin());
			Log.i("getSpeedMax()", "" + mSettingData.getSpeedMax());
			Log.i("getInclineMax()", "" + mSettingData.getInclineMax());
			Log.i("getDistanceMax()", "" + mSettingData.getDistanceMax());
			Log.i("getSpeedRatio()", "" + mSettingData.getSpeedRatio());
			Log.i("getOilDistance()", "" + mSettingData.getOilDistance());
			Log.i("getOilTime()", "" + mSettingData.getOilTime());
			Log.i("getLanguageSelect()", "" + mSettingData.getLanguageSelect());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currentActivity.setPasswordFlieDate();
	}

	/*
	 * 初始化界面的数据
	 */
	private void inItUi() {

		try {
			getApkVersionName();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		currentActivity.RL_commenTop = currentActivity
				.findViewById(R.id.RL_commenTop);
		currentActivity.RL_commenTop.setVisibility(View.GONE);
		setActivity.isOrNoAtsetFragment = false;
		setActivity.isOrNoFactoryPatternSetFragment = true;

		TV_SIENG.setText("" + setActivity.defaultSing);

		TV_minSpeed.setText(""+(setActivity.defaultMinSpeed));
		Log.i("defaultMinSpeed", "" + setActivity.defaultMinSpeed);

		TV_maxSpeed.setText(""+(setActivity.defaultMaxSpeed));
		Log.i("defaultMaxSpeed", "" + setActivity.defaultMaxSpeed);

		TV_maxIncline.setText("" + setActivity.defaultMaxLncline);
		TV_speedTime.setText("" + setActivity.defaultSpeedTime);
		Log.i("defaultSpeedTime", "" + setActivity.defaultSpeedTime);
		
		TV_whell.setText(""+ setActivity.defaultWhell);
		Log.i("defaultWhell", "" + setActivity.defaultWhell);
		
		TV_autoOil.setText("" + setActivity.defaultAutoOilDistance);
		TV_oilTime.setText("" + setActivity.defaultoilTime);
		TV_addOilNumber.setText("" + setActivity.defaultAddOliNumber);
		
		Log.i("setDistanceLimit", "" + setActivity.setDistanceLimit);
		TV_setDistance.setText(""+setActivity.setDistanceLimit);

		Log.i("totalTimeValue", ""+setActivity.totalTimeValue);
		Log.i("totalDistanceValue", ""+setActivity.totalDistanceValue);
		totalDistanceTextView.setText("" + setActivity.totalDistanceValue +"  "+"Km");
		totalTimeTextView.setText("" + setActivity.totalTimeValue+ "  "+ "H");

	}

	/*
	 * 获取版本号
	 */
	private void getApkVersionName() throws Exception {
		// TODO Auto-generated method stub

		version = getVersionName();
		TV_showVersions.setText(version);
	}

	/*
	 * 显示版本号
	 */
	private String getVersionName() throws Exception {
		// packagemanager
		PackageManager packageManager = currentActivity.getPackageManager();
		// getPackageName()
		PackageInfo packInfo = packageManager.getPackageInfo(
				currentActivity.getPackageName(), 0);
		String version = packInfo.versionName;
		return version;
	}

	/*
	 * 字符串显示
	 */
	private String showString(int mInt) {
		String mString = "" ;
	//	String mString = "" + mInt + "." + "0";
		if (mInt >= 10) {
		 mString = "" +mInt/10 +"." + mInt/10%10 ;
		 }else if (mInt >=1) {
		 mString = "" +mInt%10;
		 }

		return mString;
	}

	/*
	 * 查找id
	 */
	private void findViewById(View view) {

		
		// 总时间总距离保存到数据库
		
		Log.i("totalTimeValue", "工厂写入"+setActivity.totalTimeValue);
		Log.i("totalDistanceValue", ""+setActivity.totalDistanceValue);
		
		// 从数据库取数据
		currentActivity.getPasswordFileDate();
		Log.i("defaultSpeedTime22", ""
				+ setActivity.defaultSpeedTime);
		Log.i("defaultWhell22", ""
				+ setActivity.defaultWhell);
		Log.i("defaultWhell22", ""
				+ setActivity.setNumberSectionSpeed);


		// 版本号
		TV_showVersions = (TextView) view.findViewById(R.id.TV_showVersions);
		// 9个布局
		RL_SIENG = (RelativeLayout) view.findViewById(R.id.RL_SIENG);
		RL_minSpeed = (RelativeLayout) view.findViewById(R.id.RL_minSpeed);
		RL_maxSpeed = (RelativeLayout) view.findViewById(R.id.RL_maxSpeed);
		RL_maxIncline = (RelativeLayout) view.findViewById(R.id.RL_maxIncline);
		RL_speedTime = (RelativeLayout) view.findViewById(R.id.RL_speedTime);
		RL_whell = (RelativeLayout) view.findViewById(R.id.RL_whell);
		RL_autoOil = (RelativeLayout) view.findViewById(R.id.RL_autoOil);
		RL_oilTime = (RelativeLayout) view.findViewById(R.id.RL_oilTime);
		RL_language = (RelativeLayout) view.findViewById(R.id.RL_language);

		// 4个布局
		FactoryPatternset_elevator = (RelativeLayout) view
				.findViewById(R.id.FactoryPatternset_elevator);
		Password_Close = (RelativeLayout) view
				.findViewById(R.id.Password_Close);
		RL_PasswordOpen = (RelativeLayout) view
				.findViewById(R.id.RL_PasswordOpen);
		LL_showDate = (LinearLayout) view.findViewById(R.id.LL_showDate);

		// 键盘按钮
		Btn_One = (Button) view.findViewById(R.id.Btn_One);
		Btn_Two = (Button) view.findViewById(R.id.Btn_Two);
		Btn_Three = (Button) view.findViewById(R.id.Btn_Three);
		Btn_Four = (Button) view.findViewById(R.id.Btn_Four);
		Btn_Five = (Button) view.findViewById(R.id.Btn_Five);
		Btn_Six = (Button) view.findViewById(R.id.Btn_Six);
		Btn_Seven = (Button) view.findViewById(R.id.Btn_Seven);
		Btn_Eight = (Button) view.findViewById(R.id.Btn_Eight);
		Btn_Nine = (Button) view.findViewById(R.id.Btn_Nine);
		Btn_Zero = (Button) view.findViewById(R.id.Btn_Zero);
		Btn_Point = (Button) view.findViewById(R.id.Btn_Point);
		Btn_Enter = (Button) view.findViewById(R.id.Btn_Enter);
		Btn_Delete = (Button) view.findViewById(R.id.Btn_Delete);

		// 9个显示框
		TV_SIENG = (TextView) view.findViewById(R.id.TV_SIENG);
		TV_minSpeed = (TextView) view.findViewById(R.id.TV_minSpeed);
		TV_maxSpeed = (TextView) view.findViewById(R.id.TV_maxSpeed);
		TV_maxIncline = (TextView) view.findViewById(R.id.TV_maxIncline);
		TV_speedTime = (TextView) view.findViewById(R.id.TV_speedTime);
		TV_whell = (TextView) view.findViewById(R.id.TV_whell);
		TV_autoOil = (TextView) view.findViewById(R.id.TV_autoOil);
		TV_oilTime = (TextView) view.findViewById(R.id.TV_oilTime);
		TV_addOilNumber = (TextView) view.findViewById(R.id.TV_addOilNumber);

		TV_setNumber = (TextView) view.findViewById(R.id.TV_setNumber);

		TV_generalSet = (TextView) view.findViewById(R.id.TV_generalSet);
		TV_elevator = (TextView) view.findViewById(R.id.TV_elevator);
		TV_password = (TextView) view.findViewById(R.id.TV_password);
		TV_recoverSet = (TextView) view.findViewById(R.id.TV_recoverSet);
		TV_exit = (TextView) view.findViewById(R.id.TV_exit);
		TV_addOil = (TextView) view.findViewById(R.id.TV_addOil);
		totalDistanceTextView = (TextView) view
				.findViewById(R.id.totalDistanceTextView);
		totalTimeTextView = (TextView) view
				.findViewById(R.id.totalTimeTextView);

		// 设置段速
		TV_run = (TextView) view.findViewById(R.id.TV_run);
		TV_reset = (TextView) view.findViewById(R.id.TV_reset);
		TV_stepOneDetection = (TextView) view
				.findViewById(R.id.TV_stepOneDetection);
		TV_stepTwoDetection = (TextView) view
				.findViewById(R.id.TV_stepTwoDetection);

		// 密码设置打开
		IB_openPasswordSet = (ImageButton) view
				.findViewById(R.id.IB_openPasswordSet);
		TV_setDistance = (TextView) view.findViewById(R.id.TV_setDistance);
		TV_oldPasswordShow = (TextView) view
				.findViewById(R.id.TV_oldPasswordShow);
		TV_newPasswordShow = (TextView) view
				.findViewById(R.id.TV_newPasswordShow);
		TV_openPasswordSave = (TextView) view
				.findViewById(R.id.TV_openPasswordSave);

		// 关闭密码
		IB_closePasswordSet = (ImageButton) view
				.findViewById(R.id.IB_closePasswordSet);
		// 暂时没用
		// TV_saveClosePassword =
		// (TextView)view.findViewById(R.id.TV_saveClosePassword);
	}

}
