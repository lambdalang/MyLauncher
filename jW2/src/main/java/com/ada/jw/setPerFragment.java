package com.ada.jw;

import java.util.Timer;

import com.ada.mcu.service.Beep;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.RemoteException;
import android.renderscript.Type.CubemapFace;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class setPerFragment extends Fragment {

	// 倒计时
	private View activity_count_down;
	private TextView TV_CountDown;
	private Timer mTimer;
	private Intent intent;
	private int i = 4;

	private TextView TV_confirm;

	// c存放中间值
	private static String Age_TextTemp = "";
	private static String Height_TextTemp = "";
	private static String Weight_TextTemp = "";
	private static String Time_TextTemp = "";
	private static String Distance_TextTemp = "";
	private static String Hot_TextTemp = "";
	// private static int DistanceAndHot = 0;//0:没选择距离和热量模式，1：选择了距离模式，2：选择了热量模式
	// 是否被点击
	private boolean Age_TextBool;
	private boolean Height_TextBool;
	private boolean Weight_TextBool;
	private boolean Time_TextBool;
	private boolean Distance_TextBool;
	private boolean Hot_TextBool;

	private String ageHeightWeight = "";
	private int sortSelect = 0;

	// 时间，距离，热量布局
	private View Time_Layout;
	private View Hot_Layout;
	private View Distance_Layout;
	private TextView TV_Time_Show;
	private TextView TV_Time;
	private TextView TV_Hot_Show;
	private TextView TV_Distance_Show;
	private TextView TV_Hot;
	private TextView TV_setPerDistance;

	private View RL_Keyboard_Layout;

	// 显示框
	public static String TempKeyboard = "";// 存放键盘输入的字符串，然后根据判断放入对应的数据里
	// public String Type = ""; // 根据要输入的类型，年龄； 身高； 体重；时间；距离；热量
	private TextView TV_Age_Show;
	private TextView TV_Height_Show;
	private TextView TV_Weight_Show;
	private TextView TV_SexShow;
	private TextView TV_Age;
	private TextView TV_Height;
	private TextView TV_Weight;
	private TextView TV_Sex;
	private boolean isGril = true;

	// // 时间距离热量
	// public String Time_Text = "30";
	// public String Hot_Text = "2";
	// public String Distance_Text = "1";
	// 传递给TaskActivity的目标类型
	public static String GoalShow = "";

	// 键盘按钮
	private Button Btn_One;
	private Button Btn_Two;
	private Button Btn_Three;
	private Button Btn_Four;
	private Button Btn_Five;
	private Button Btn_Six;
	private Button Btn_Seven;
	private Button Btn_Eight;
	private Button Btn_Nine;
	private Button Btn_Zero;
	private Button Btn_Point;
	private Button Btn_Delete;
	private boolean deleteIsOrNoEffect = false;
	private Button Btn_Enter;

	private setActivity currentActivity;
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		view = inflater.inflate(R.layout.activity_ser_per, null, false);

		return view;
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		findViewById(view);
		inItUi();
		listionEvent();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();

		currentActivity.isOrNoSetPerFragment = false;
	}

	/*
	 * 监听事件
	 */
	private void listionEvent() {

		/*
		 * 监听： 点击年龄，身高，体重事件
		 */
		TV_Time_Show.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(currentActivity.getApplicationContext());
				activeKeyboard();
				ageHeightWeight = "TV_Time_Show";
				sortSelect = 1;
				Time_TextBool = true;
				deleteIsOrNoEffect = true;
				TV_Age.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Height.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Weight.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Sex.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Time.setTextColor(Color.parseColor("#FFCC00"));
				TV_setPerDistance.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Hot.setTextColor(Color.parseColor("#FFFFFF"));

				currentActivity.Time_Text = "";
				TV_Time_Show.setText(currentActivity.Time_Text);
				TempKeyboard = currentActivity.Time_Text;// 中间变量清零
			}
		});

		TV_Distance_Show.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(currentActivity.getApplicationContext());
				activeKeyboard();
				ageHeightWeight = "TV_Distance_Show";
				sortSelect = 1;
				Distance_TextBool = true;
				deleteIsOrNoEffect = true;
				TV_Age.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Height.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Weight.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Sex.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Time.setTextColor(Color.parseColor("#FFFFFF"));
				TV_setPerDistance.setTextColor(Color.parseColor("#FFCC00"));
				TV_Hot.setTextColor(Color.parseColor("#FFFFFF"));
				currentActivity.Distance_Text = "";
				TV_Distance_Show.setText(currentActivity.Distance_Text);
				TempKeyboard = currentActivity.Distance_Text;// 中间变量清零
			}
		});

		TV_Hot_Show.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(currentActivity.getApplicationContext());
				activeKeyboard();
				ageHeightWeight = "TV_Hot_Show";
				sortSelect = 1;
				Hot_TextBool = true;
				deleteIsOrNoEffect = true;
				TV_Age.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Height.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Weight.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Sex.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Time.setTextColor(Color.parseColor("#FFFFFF"));
				TV_setPerDistance.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Hot.setTextColor(Color.parseColor("#FFCC00"));
				currentActivity.Hot_Text = "";
				TV_Hot_Show.setText(currentActivity.Hot_Text);
				TempKeyboard = currentActivity.Hot_Text;// 中间变量清零
			}
		});

		// 点击IB_Age，编辑年龄
		TV_Age_Show.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Beep.beep(currentActivity.getApplicationContext());
				// 显示键盘
				activeKeyboard();
				TV_Age.setTextColor(Color.parseColor("#FFCC00"));
				TV_Height.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Weight.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Sex.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Time.setTextColor(Color.parseColor("#FFFFFF"));
				TV_setPerDistance.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Hot.setTextColor(Color.parseColor("#FFFFFF"));
				ageHeightWeight = "TV_Age_Show";
				sortSelect = 1;
				Age_TextBool = true;
				deleteIsOrNoEffect = true;
				currentActivity.Age_Text = "";
				TV_Age_Show.setText(currentActivity.Age_Text);
				TempKeyboard = currentActivity.Age_Text;// 中间变量清零
			}
		});

		// 点击IIB_Height，编辑身高
		TV_Height_Show.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(currentActivity.getApplicationContext());
				// 显示键盘
				activeKeyboard();
				TV_Age.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Height.setTextColor(Color.parseColor("#FFCC00"));
				TV_Weight.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Sex.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Time.setTextColor(Color.parseColor("#FFFFFF"));
				TV_setPerDistance.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Hot.setTextColor(Color.parseColor("#FFFFFF"));
				ageHeightWeight = "TV_Height_Show";
				sortSelect = 1;
				Height_TextBool = true;
				deleteIsOrNoEffect = true;
				currentActivity.Height_Text = "";
				TV_Height_Show.setText(currentActivity.Height_Text);
				TempKeyboard = currentActivity.Height_Text;// 中间变量清零
			}
		});

		// 点击IIB_Weight，编辑体重
		TV_Weight_Show.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(currentActivity.getApplicationContext());
				// 显示键盘
				activeKeyboard();
				TV_Age.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Height.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Weight.setTextColor(Color.parseColor("#FFCC00"));
				TV_Sex.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Time.setTextColor(Color.parseColor("#FFFFFF"));
				TV_setPerDistance.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Hot.setTextColor(Color.parseColor("#FFFFFF"));
				Weight_TextBool = true;
				deleteIsOrNoEffect = true;
				ageHeightWeight = "TV_Weight_Show";
				sortSelect = 1;

				currentActivity.Weight_Text = "";
				TV_Weight_Show.setText(currentActivity.Weight_Text);
				TempKeyboard = currentActivity.Weight_Text;// 中间变量清零

			}
		});

		TV_SexShow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(currentActivity.getApplicationContext());
				TV_Age.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Height.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Weight.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Sex.setTextColor(Color.parseColor("#FFCC00"));
				TV_Time.setTextColor(Color.parseColor("#FFFFFF"));
				TV_setPerDistance.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Hot.setTextColor(Color.parseColor("#FFFFFF"));
				if (isGril) {

					TV_SexShow.setText(R.string.Boy);
					isGril = false;
				} else {
					TV_SexShow.setText(R.string.BoyOrGril);
					isGril = true;
				}
			}
		});

		/*
		 * 监听：键盘点击事件
		 */
		// 点击 1
		Btn_One.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(currentActivity.getApplicationContext());
				TempKeyboard += "1";
				sortSelect();
			}
		});

		// 点击 2
		Btn_Two.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(currentActivity.getApplicationContext());
				TempKeyboard += "2";
				sortSelect();
			}
		});

		// 点击 3
		Btn_Three.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(currentActivity.getApplicationContext());
				TempKeyboard += "3";
				sortSelect();
			}
		});

		// 点击 4
		Btn_Four.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(currentActivity.getApplicationContext());
				TempKeyboard += "4";
				sortSelect();
			}
		});

		// 点击 5
		Btn_Five.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(currentActivity.getApplicationContext());
				TempKeyboard += "5";
				sortSelect();
			}
		});

		// 点击 6
		Btn_Six.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(currentActivity.getApplicationContext());
				TempKeyboard += "6";
				sortSelect();
			}
		});

		// 点击 7
		Btn_Seven.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(currentActivity.getApplicationContext());
				TempKeyboard += "7";
				sortSelect();
			}
		});

		// 点击 8
		Btn_Eight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(currentActivity.getApplicationContext());
				TempKeyboard += "8";
				sortSelect();
			}
		});

		// 点击 9
		Btn_Nine.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(currentActivity.getApplicationContext());
				TempKeyboard += "9";
				sortSelect();
			}
		});

		// 点击 0
		Btn_Zero.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(currentActivity.getApplicationContext());
				TempKeyboard += "0";
				sortSelect();
			}
		});

		// 点击 .
		Btn_Point.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(currentActivity.getApplicationContext());
				AlertDialog.Builder builder = new AlertDialog.Builder(
						currentActivity);
				builder.setTitle(R.string.prompt);
				builder.setMessage(R.string.ThisPageDataDoNotSupportTheDecimalPoint);
				builder.setPositiveButton(R.string.Confirm, null);
				builder.show();
			}
		});

		// 点击enter键
		Btn_Enter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// RL_Keyboard_Layout.setVisibility(View.INVISIBLE);

				Beep.beep(currentActivity.getApplicationContext());
				deleteIsOrNoEffect = false;
				setPerDateShow();

				TV_Age.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Height.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Weight.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Sex.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Time.setTextColor(Color.parseColor("#FFFFFF"));
				TV_setPerDistance.setTextColor(Color.parseColor("#FFFFFF"));
				TV_Hot.setTextColor(Color.parseColor("#FFFFFF"));
			}
		});

		// 点击确认，进入模式
		TV_confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(currentActivity.getApplicationContext());
				if (currentActivity.mMachineData.getCurrentStatus() != 1) {
					entryMode();
				} else {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							currentActivity);
					builder.setTitle(R.string.prompt);
					builder.setMessage("现在是运行状态！！");
					builder.setPositiveButton(R.string.Confirm, null);
					builder.show();
				}
			}
		});

		// 点击Btn_Delete键
		Btn_Delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(currentActivity.getApplicationContext());
				if (deleteIsOrNoEffect) {

					if (TempKeyboard.length() > 0) {
						TempKeyboard = TempKeyboard.substring(0,
								TempKeyboard.length() - 1);
						sortSelect();
					} else {

						AlertDialog.Builder builder = new AlertDialog.Builder(
								currentActivity);
						builder.setTitle(R.string.prompt);
						builder.setMessage(R.string.YouWantToDeleteTheDataIsEmpty);
						builder.setPositiveButton(R.string.Confirm, null);
						builder.show();
					}
				}
			}
		});
	}

	/*
	 * 点击确定，进入模式
	 */
	protected void entryMode() {

		currentActivity.mProgramData.reset();
		switch (currentActivity.programType) {
		case 0:
			AlertDialog.Builder builder = new AlertDialog.Builder(
					currentActivity);
			builder.setTitle(R.string.prompt);
			if (currentActivity.mMachineData.getCurrentStatus() == 3) {
				builder.setMessage(R.string.selfTesting);
			}else {
				builder.setMessage(R.string.youHaveNoChoiceModel);
				Log.i("shenmedoumeizuo", "没有选择模式");
			}
			builder.setPositiveButton(R.string.Confirm, null);
			builder.show();

			break;
		case setActivity.DISTANCE:

			currentActivity.mProgramData.setProgramIndex(12);
			currentActivity.mProgramData.setGoal(Integer
					.parseInt(currentActivity.Distance_Text) * 100000);
			Log.i("setProgramIndex", "12 Distance_Text:"
					+ currentActivity.Distance_Text);
			break;
		case setActivity.HOT:
			currentActivity.mProgramData.setProgramIndex(11);
			currentActivity.mProgramData.setGoal(Integer
					.parseInt(currentActivity.Hot_Text) * 1000);
			Log.i("setProgramIndex", "7");
			break;
		default:

			currentActivity.mProgramData.setWeight(Integer
					.parseInt(currentActivity.Weight_Text));
			switch (currentActivity.programType) {
			case setActivity.HANDMOVE:

				currentActivity.mProgramData.setProgramIndex(1);
				currentActivity.mProgramData.setTimeCount(Integer
						.parseInt(currentActivity.Time_Text));
				break;
			case setActivity.FLUCTUATE:

				currentActivity.mProgramData.setProgramIndex(2);
				currentActivity.mProgramData.setTimeCount(Integer
						.parseInt(currentActivity.Time_Text));
				break;
			case setActivity.CLIMBING:

				currentActivity.mProgramData.setProgramIndex(3);
				currentActivity.mProgramData.setTimeCount(Integer
						.parseInt(currentActivity.Time_Text));
				break;
			case setActivity.INTERVAL:

				currentActivity.mProgramData.setProgramIndex(4);
				currentActivity.mProgramData.setTimeCount(Integer
						.parseInt(currentActivity.Time_Text));
				break;
			case setActivity.LOSTWEIGHT:

				currentActivity.mProgramData.setProgramIndex(5);
				currentActivity.mProgramData.setTimeCount(Integer
						.parseInt(currentActivity.Time_Text));
				break;
			case setActivity.MOUNTION:

				currentActivity.mProgramData.setProgramIndex(6);
				currentActivity.mProgramData.setTimeCount(Integer
						.parseInt(currentActivity.Time_Text));
				break;
			case setActivity.BURN:

				currentActivity.mProgramData.setProgramIndex(7);
				currentActivity.mProgramData.setTimeCount(Integer
						.parseInt(currentActivity.Time_Text));
				break;
			case setActivity.HEART65:

				currentActivity.mProgramData.setProgramIndex(8);
				currentActivity.mProgramData.setAge(Integer
						.parseInt(currentActivity.Age_Text));
				currentActivity.mProgramData.setTimeCount(Integer
						.parseInt(currentActivity.Time_Text));
				break;
			case setActivity.HEART75:

				currentActivity.mProgramData.setProgramIndex(9);
				currentActivity.mProgramData.setAge(Integer
						.parseInt(currentActivity.Age_Text));
				currentActivity.mProgramData.setTimeCount(Integer
						.parseInt(currentActivity.Time_Text));
				break;
			case setActivity.HEART85:

				currentActivity.mProgramData.setProgramIndex(10);
				currentActivity.mProgramData.setAge(Integer
						.parseInt(currentActivity.Age_Text));
				currentActivity.mProgramData.setTimeCount(Integer
						.parseInt(currentActivity.Time_Text));
				break;
			case setActivity.TIME:
				currentActivity.mProgramData.reset();
				currentActivity.mProgramData.setProgramIndex(13);
				currentActivity.mProgramData.setTimeCount(Integer
						.parseInt(currentActivity.Time_Text));
				break;

			default:
				break;
			}
			break;
		}

		if (currentActivity.programType != 0) {
			updateTimeAndWeightToService();
			currentActivity.countDown = 5;
			currentActivity.switchFragment = 7;
			currentActivity.switchFragment(currentActivity.switchFragment);
		}
	}

	/*
	 * 传递给后台体重，时间
	 */
	protected void updateTimeAndWeightToService() {

		Log.i("设置的时间", currentActivity.Time_Text);
		Log.i("设置的体重", currentActivity.Weight_Text);
		int upDateTimeToservice = Integer.parseInt(currentActivity.Time_Text);
		int upDateWeightToservice = Integer
				.parseInt(currentActivity.Weight_Text);
		int upDateGoalToservice = 0;
		if (currentActivity.programType == currentActivity.DISTANCE) {
			upDateGoalToservice = Integer
					.parseInt(currentActivity.Distance_Text);
			currentActivity.mProgramData.setTimeCount(0);
		} else if (currentActivity.programType == currentActivity.TIME) {
			upDateGoalToservice = Integer.parseInt(currentActivity.Time_Text);
			currentActivity.mProgramData.setTimeCount(upDateTimeToservice);
			
		} else if (currentActivity.programType == currentActivity.HOT) {
			upDateGoalToservice = Integer.parseInt(currentActivity.Hot_Text);
			currentActivity.mProgramData.setTimeCount(0);
		}

		// currentActivity.mProgramData.setGoal(upDateGoalToservice);
		Log.i("传给后台的Goal", "" + upDateGoalToservice);
		
		Log.i("传给后台的时间", "" + upDateTimeToservice);
		currentActivity.mProgramData.setWeight(upDateWeightToservice);
		Log.i("传给后台体重", "" + upDateWeightToservice);
		try {
			if (null != currentActivity.mService) {
				currentActivity.mService
				.setProgramData(currentActivity.mProgramData);
				Log.i("setProgramData", "mProgramData");
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 点击enter数据展示处理
	 */
	private void setPerDateShow() {

		Log.i("点击了enter", "进入了数据展示");
		if (Age_TextBool) {
			Log.i("点击了Age_TextTemp", Age_TextTemp);
			Age_TextTemp = stringToItToString(Age_TextTemp, 80, 5);
			Log.i("点击了Age_TextTemp", Age_TextTemp);
			if (!Age_TextTemp.equals("")) {
				currentActivity.Age_Text = Age_TextTemp;
			}
			Age_TextBool = false;
			Log.i("点击了enter", "进入了数据判断");
		}
		Log.i("currentActivity.Age_Text", currentActivity.Age_Text);
		TV_Age_Show.setText(currentActivity.Age_Text);

		if (Weight_TextBool) {
			Log.i("点击了Weight_TextTemp", Weight_TextTemp);
			Weight_TextTemp = stringToItToString(Weight_TextTemp, 400, 25);

			if (!Weight_TextTemp.equals("")) {
				currentActivity.Weight_Text = Weight_TextTemp;
			}
			Log.i("点击了Weight_TextTemp", Weight_TextTemp);

			Weight_TextBool = false;
		}
		Log.i("点击了currentActivity.Weight_Text", currentActivity.Weight_Text);
		TV_Weight_Show.setText(currentActivity.Weight_Text);

		if (Height_TextBool) {
			Height_TextTemp = stringToItToString(Height_TextTemp, 250, 50);
			if (!Height_TextTemp.equals("")) {
				currentActivity.Height_Text = Height_TextTemp;
				Log.i("Height_TextTemp", Height_TextTemp);
			}
			Height_TextBool = false;
		}
		Log.i("Height_Text", currentActivity.Height_Text);
		TV_Height_Show.setText(currentActivity.Height_Text);

		if (Time_TextBool) {
			Time_TextTemp = stringToItToString(Time_TextTemp, 99, 1);
			if (!Time_TextTemp.equals("")) {
				currentActivity.Time_Text = Time_TextTemp;
			}
		}
		TV_Time_Show.setText(currentActivity.Time_Text);

		if (Distance_TextBool) {
			Distance_TextTemp = stringToItToString(Distance_TextTemp, 999, 1);
			if (!Distance_TextTemp.equals("")) {
				currentActivity.Distance_Text = Distance_TextTemp;
			}
			Distance_TextBool = false;
		}
		TV_Distance_Show.setText(currentActivity.Distance_Text);

		if (Hot_TextBool) {
			Hot_TextTemp = stringToItToString(Hot_TextTemp, 999, 2);
			if (!Hot_TextTemp.equals("")) {
				currentActivity.Hot_Text = Hot_TextTemp;
			}
			Hot_TextBool = false;
		}
		TV_Hot_Show.setText(currentActivity.Hot_Text);

		AlertDialog.Builder builder = new AlertDialog.Builder(currentActivity);
		builder.setTitle(R.string.prompt);
		builder.setMessage(R.string.ParameterIsSetSuccessfully);
		builder.setPositiveButton(R.string.Confirm, null);
		builder.show();
	}

	/*
	 * 字符串转整型，限制最大值，最小值后再返回字符串
	 */
	public String stringToItToString(String string, int max, int min) {

		if (string.length() > 4) {
			string = string.substring(0, 4);
		}

		if (!string.equals("")) {

			int myInt = Integer.parseInt(string);
			myInt = (myInt > max) ? max : myInt;
			myInt = (myInt < min) ? min : myInt;
			String mysString = "" + myInt;
			return mysString;
		}
		return "";
	}

	/*
	 * j键盘分类
	 */
	private void sortSelect() {

		if (sortSelect == 1) {
			SortAgeHeightWeight();
		} else {
			Sort();
		}
	}

	/*
	 * 如果是热量距离心率
	 */
	private void Sort() {

		switch (currentActivity.programType) {
		case 0:

			break;
		case setActivity.DISTANCE:

			if (TempKeyboard.length() > 4) {
				TempKeyboard = TempKeyboard.substring(0, 4);
			}
			TV_Distance_Show.setText(TempKeyboard);
			// 获取到值，便于传递到TaskActivity
			currentActivity.Distance_Text = TempKeyboard;
			break;
		case setActivity.HOT:

			if (TempKeyboard.length() > 4) {
				TempKeyboard = TempKeyboard.substring(0, 4);
			}
			TV_Hot_Show.setText(TempKeyboard);
			// 获取到值，便于传递到TaskActivity
			currentActivity.Hot_Text = TempKeyboard;
			break;
		default:

			if (TempKeyboard.length() > 2) {
				TempKeyboard = TempKeyboard.substring(0, 2);
			}
			TV_Time_Show.setText(TempKeyboard);
			// 获取到值，便于传递到TaskActivity
			currentActivity.Time_Text = TempKeyboard;
			break;
		}
	}

	/*
	 * 如果是年龄身高体重
	 */
	private void SortAgeHeightWeight() {

		if (ageHeightWeight.equals("TV_Age_Show")) {

			Age_TextTemp = TempKeyboard;
			TV_Age_Show.setText(TempKeyboard);

			Log.i("Age_TextTemp", Age_TextTemp);
		} else if (ageHeightWeight.equals("TV_Height_Show")) {

			Height_TextTemp = TempKeyboard;
			TV_Height_Show.setText(TempKeyboard);

			Log.i("Height_TextTemp", Height_TextTemp);
		} else if (ageHeightWeight.equals("TV_Weight_Show")) {

			Weight_TextTemp = TempKeyboard;
			TV_Weight_Show.setText(TempKeyboard);

			Log.i("Weight_TextTemp", Weight_TextTemp);
		} else if (ageHeightWeight.equals("TV_Time_Show")) {

			Time_TextTemp = TempKeyboard;
			TV_Time_Show.setText(TempKeyboard);
			Log.i("Time_TextTemp", Time_TextTemp);
		} else if (ageHeightWeight.equals("TV_Distance_Show")) {

			Distance_TextTemp = TempKeyboard;
			TV_Distance_Show.setText(TempKeyboard);
		} else if (ageHeightWeight.equals("TV_Hot_Show")) {

			Hot_TextTemp = TempKeyboard;
			TV_Hot_Show.setText(TempKeyboard);
		}
	}

	/*
	 * 键盘有效
	 */
	private void activeKeyboard() {

		Btn_One.setClickable(true);
		Btn_Two.setClickable(true);
		Btn_Three.setClickable(true);
		Btn_Four.setClickable(true);
		Btn_Five.setClickable(true);
		Btn_Six.setClickable(true);
		Btn_Seven.setClickable(true);
		Btn_Eight.setClickable(true);
		Btn_Nine.setClickable(true);
		Btn_Zero.setClickable(true);
		Btn_Point.setClickable(true);
		Btn_Delete.setClickable(true);
		Btn_Enter.setClickable(true);
	}

	/*
	 * 界面数据初始化
	 */
	private void inItUi() {

		currentActivity.isOrNoSetPerFragment = true;
		
		currentActivity = (setActivity) getActivity();
		currentActivity.isOrNoAtsetFragment = false;

		TV_Age_Show.setText(currentActivity.Age_Text);
		TV_Height_Show.setText(currentActivity.Height_Text);
		TV_Weight_Show.setText(currentActivity.Weight_Text);
		TV_SexShow.setText(R.string.BoyOrGril);

		switch (currentActivity.programType) {
		case 0:
			// 没有选择模式，什么都不做
			break;
		case 11:
			// 选择了热量
			Hot_Layout.setVisibility(View.VISIBLE);
			Time_Layout.setVisibility(View.GONE);
			Distance_Layout.setVisibility(View.GONE);
			RL_Keyboard_Layout.setVisibility(View.VISIBLE);
			TV_Hot_Show.setText(currentActivity.Hot_Text);
			activeKeyboard();
			break;
		case 12:
			// 选择了距离
			Hot_Layout.setVisibility(View.GONE);
			Time_Layout.setVisibility(View.GONE);
			Distance_Layout.setVisibility(View.VISIBLE);
			RL_Keyboard_Layout.setVisibility(View.VISIBLE);
			TV_Distance_Show.setText(currentActivity.Distance_Text);
			activeKeyboard();
			break;

		default:
			// 其它都显示时间
			Hot_Layout.setVisibility(View.GONE);
			Time_Layout.setVisibility(View.VISIBLE);
			Distance_Layout.setVisibility(View.GONE);
			RL_Keyboard_Layout.setVisibility(View.VISIBLE);
			activeKeyboard();
			break;
		}
	}

	/*
	 * 查找id
	 */
	private void findViewById(View view) {
		// TODO Auto-generated method stub

		// extendPackageName = "SetPerActivity";
		TV_confirm = (TextView) view.findViewById(R.id.TV_confirm);
		TV_Age_Show = (TextView) view.findViewById(R.id.TV_Age_Show);
		TV_Height_Show = (TextView) view.findViewById(R.id.TV_Height_Show);
		TV_Weight_Show = (TextView) view.findViewById(R.id.TV_Weight_Show);
		TV_SexShow = (TextView) view.findViewById(R.id.Sex);
		TV_Age = (TextView) view.findViewById(R.id.TV_Age);
		TV_Height = (TextView) view.findViewById(R.id.TV_Height);
		TV_Weight = (TextView) view.findViewById(R.id.TV_Weight);
		TV_Sex = (TextView) view.findViewById(R.id.TV_Sex);

		RL_Keyboard_Layout = (RelativeLayout) view
				.findViewById(R.id.RL_Keyboard_Layout);

		// 键盘按键初始化
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
		Btn_Delete = (Button) view.findViewById(R.id.Btn_Delete);
		Btn_Enter = (Button) view.findViewById(R.id.Btn_Enter);

		// 时间，距离，热量
		Time_Layout = (RelativeLayout) view.findViewById(R.id.Time_Layout);
		Hot_Layout = (RelativeLayout) view.findViewById(R.id.Hot_Layout);
		Distance_Layout = (RelativeLayout) view
				.findViewById(R.id.Distance_Layout);
		TV_Time_Show = (TextView) view.findViewById(R.id.TV_Time_Show);
		TV_Hot_Show = (TextView) view.findViewById(R.id.TV_Hot_Show);
		TV_Distance_Show = (TextView) view.findViewById(R.id.TV_Distance_Show);
		TV_Time = (TextView) view.findViewById(R.id.TV_Time);
		TV_Hot = (TextView) view.findViewById(R.id.TV_Hot);
		TV_setPerDistance = (TextView) view
				.findViewById(R.id.TV_setPerDistance);

	}
}
