package com.ada.jw;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.ada.mcu.service.Beep;
import com.semicircularmenu.libary.SemiCircularRadialMenu;
import com.semicircularmenu.libary.SemiCircularRadialMenuItem;
import com.semicircularmenu.libary.SemiCircularRadialMenuItem.OnSemiCircularRadialMenuPressed;

public class nowModeProjectFragment extends Fragment {

	private setActivity currentActivity;

	 public static nowModeProjectFragment newInstance(Activity mActivity) {
	
	 nowModeProjectFragment mNowModeProjectFragment = new
	 nowModeProjectFragment();
	
	 mNowModeProjectFragment.currentActivity = (setActivity) mActivity;
	
	 return mNowModeProjectFragment;
	 }

	private ImageView mBackGround;
	private SemiCircularRadialMenu mMenu;
	private SemiCircularRadialMenuItem mProject, mHeart, mGoal;

	// 手动，起伏，间歇
	protected ImageButton IB_Hand_Move;
	protected ImageButton IB_Fluctuate;
	protected ImageButton IB_Climbing;
	protected ImageButton IB_Interval;
	protected ImageButton IB_LostWeight;
	protected ImageButton IB_Mountion;
	protected ImageButton IB_Burn;

	// 按键板,选程式
	// public int programChoice ;

	private View view;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);

	//	currentActivity = (setActivity) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		view = inflater.inflate(R.layout.activity_nowmode_project, null, false);

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

		currentActivity.isOrNoFirstInProjectFragment = true;
	}

	/*
	 * 界面初始化
	 */
	private void inItUi() {

		mProject = new SemiCircularRadialMenuItem("dislike", getResources()
				.getDrawable(R.drawable.ic_action_dislike), "camera");
		mHeart = new SemiCircularRadialMenuItem("camera", getResources()
				.getDrawable(R.drawable.ic_action_camera), "dislike");
		mGoal = new SemiCircularRadialMenuItem("info", getResources()
				.getDrawable(R.drawable.ic_action_info), "Info");

		mMenu = (SemiCircularRadialMenu) view.findViewById(R.id.Project_menu);
		mMenu.addMenuItem(mGoal.getMenuID(), mGoal);
		mMenu.addMenuItem(mProject.getMenuID(), mProject);
		mMenu.addMenuItem(mHeart.getMenuID(), mHeart);

		mBackGround.setVisibility(View.INVISIBLE);
		currentActivity.isOrNoAtsetFragment = false;
		currentActivity.isOrNoAtNowModeProjectActivity = true;

	}

	/*
	 * 监听事件
	 */
	private void listionEvent() {

		mMenu.setOnMenuVisibleChangeListener(new SemiCircularRadialMenu.OnMenuVisibleChangeListener() {

			@Override
			public void onVisibleChanged(boolean isVisible) {
				// TODO Auto-generated method stub

				if (isVisible) {
					mBackGround.setVisibility(View.VISIBLE);
				} else {
					mBackGround.setVisibility(View.INVISIBLE);
				}
			}
		});

		// 起伏
		IB_Fluctuate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Beep.beep(currentActivity.getApplicationContext());
				if (currentActivity.mMachineData.currentStatus == 1) {
					hint();
				} else if (currentActivity.mMachineData.currentStatus == 0) {

					currentActivity.programType = currentActivity.FLUCTUATE;
					displayProgramType(currentActivity.programType);
					currentActivity.switchFragment = 1;
					currentActivity
							.switchFragment(currentActivity.switchFragment);
				}
			}
		});

		// 手动
		IB_Hand_Move.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Beep.beep(currentActivity.getApplicationContext());
				if (currentActivity.mMachineData.currentStatus == 1) {
					hint();
				} else if (currentActivity.mMachineData.currentStatus == 0) {

					currentActivity.programType = currentActivity.HANDMOVE;
					displayProgramType(currentActivity.programType);
					currentActivity.switchFragment = 1;
					currentActivity
							.switchFragment(currentActivity.switchFragment);
				}
			}
		});

		// 爬坡
		IB_Climbing.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Beep.beep(currentActivity.getApplicationContext());
				if (currentActivity.mMachineData.currentStatus == 1) {
					hint();
				} else if (currentActivity.mMachineData.currentStatus == 0) {

					currentActivity.programType = currentActivity.CLIMBING;
					displayProgramType(currentActivity.programType);
					currentActivity.switchFragment = 1;
					currentActivity
							.switchFragment(currentActivity.switchFragment);
				}
			}
		});

		// 间歇
		IB_Interval.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Beep.beep(currentActivity.getApplicationContext());
				if (currentActivity.mMachineData.currentStatus == 1) {
					hint();
				} else if (currentActivity.mMachineData.currentStatus == 0) {

					currentActivity.programType = currentActivity.INTERVAL;
					displayProgramType(currentActivity.programType);
					currentActivity.switchFragment = 1;
					currentActivity
							.switchFragment(currentActivity.switchFragment);
				}
			}
		});

		// 减重
		IB_LostWeight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Beep.beep(currentActivity.getApplicationContext());
				if (currentActivity.mMachineData.currentStatus == 1) {
					hint();
				} else if (currentActivity.mMachineData.currentStatus == 0) {

					currentActivity.programType = currentActivity.LOSTWEIGHT;
					displayProgramType(currentActivity.programType);
					currentActivity.switchFragment = 1;
					currentActivity
							.switchFragment(currentActivity.switchFragment);
				}
			}
		});

		// 山地
		IB_Mountion.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Beep.beep(currentActivity.getApplicationContext());
				if (currentActivity.mMachineData.currentStatus == 1) {
					hint();
				} else if (currentActivity.mMachineData.currentStatus == 0) {

					currentActivity.programType = currentActivity.MOUNTION;
					displayProgramType(currentActivity.programType);
					currentActivity.switchFragment = 1;
					currentActivity
							.switchFragment(currentActivity.switchFragment);
				}
			}
		});

		// 燃脂
		IB_Burn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Beep.beep(currentActivity.getApplicationContext());
				if (currentActivity.mMachineData.currentStatus == 1) {
					hint();
				} else if (currentActivity.mMachineData.currentStatus == 0) {

					currentActivity.programType = currentActivity.BURN;
					displayProgramType(currentActivity.programType);
					currentActivity.switchFragment = 1;
					currentActivity
							.switchFragment(currentActivity.switchFragment);
				}
			}
		});

		mHeart.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {
			@Override
			public void onMenuItemPressed() {

				Beep.beep(getActivity().getApplication());
				currentActivity.switchFragment = 4;
				currentActivity.switchFragment(currentActivity.switchFragment);
			}
		});

		mGoal.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {
			@Override
			public void onMenuItemPressed() {

				Beep.beep(getActivity().getApplication());
				currentActivity.switchFragment = 3;
				currentActivity.switchFragment(currentActivity.switchFragment);
			}
		});
	}

	/*
	 * 判断显示对应图标
	 */
	protected void displayProgramType(int programType) {

		switch (currentActivity.programType) {
		case setActivity.HANDMOVE:

			IB_Hand_Move.setBackgroundResource(R.drawable.hand_choice);
			IB_Fluctuate.setBackgroundResource(R.drawable.fluctuate_no_shine);
			IB_Climbing.setBackgroundResource(R.drawable.climbing_no_shine);
			IB_Interval.setBackgroundResource(R.drawable.interval_no_shine);
			IB_LostWeight.setBackgroundResource(R.drawable.lose_eight_no_shine);
			IB_Mountion.setBackgroundResource(R.drawable.mountion_no_shine);
			IB_Burn.setBackgroundResource(R.drawable.burn_no_shine);
			break;
		case setActivity.FLUCTUATE:

			IB_Hand_Move.setBackgroundResource(R.drawable.hand_no_shine);
			IB_Fluctuate.setBackgroundResource(R.drawable.fluctuate_choice);
			IB_Climbing.setBackgroundResource(R.drawable.climbing_no_shine);
			IB_Interval.setBackgroundResource(R.drawable.interval_no_shine);
			IB_LostWeight.setBackgroundResource(R.drawable.lose_eight_no_shine);
			IB_Mountion.setBackgroundResource(R.drawable.mountion_no_shine);
			IB_Burn.setBackgroundResource(R.drawable.burn_no_shine);
			break;
		case setActivity.CLIMBING:

			IB_Hand_Move.setBackgroundResource(R.drawable.hand_no_shine);
			IB_Fluctuate.setBackgroundResource(R.drawable.fluctuate_no_shine);
			IB_Climbing.setBackgroundResource(R.drawable.climbing_choice);
			IB_Interval.setBackgroundResource(R.drawable.interval_no_shine);
			IB_LostWeight.setBackgroundResource(R.drawable.lose_eight_no_shine);
			IB_Mountion.setBackgroundResource(R.drawable.mountion_no_shine);
			IB_Burn.setBackgroundResource(R.drawable.burn_no_shine);
			currentActivity.programType = currentActivity.CLIMBING;
			break;
		case setActivity.INTERVAL:

			IB_Hand_Move.setBackgroundResource(R.drawable.hand_no_shine);
			IB_Fluctuate.setBackgroundResource(R.drawable.fluctuate_no_shine);
			IB_Climbing.setBackgroundResource(R.drawable.climbing_no_shine);
			IB_Interval.setBackgroundResource(R.drawable.interval_choice);
			IB_LostWeight.setBackgroundResource(R.drawable.lose_eight_no_shine);
			IB_Mountion.setBackgroundResource(R.drawable.mountion_no_shine);
			IB_Burn.setBackgroundResource(R.drawable.burn_no_shine);
			break;
		case setActivity.LOSTWEIGHT:

			IB_Hand_Move.setBackgroundResource(R.drawable.hand_no_shine);
			IB_Fluctuate.setBackgroundResource(R.drawable.fluctuate_no_shine);
			IB_Climbing.setBackgroundResource(R.drawable.climbing_no_shine);
			IB_Interval.setBackgroundResource(R.drawable.interval_no_shine);
			IB_LostWeight.setBackgroundResource(R.drawable.lose_choice);
			IB_Mountion.setBackgroundResource(R.drawable.mountion_no_shine);
			IB_Burn.setBackgroundResource(R.drawable.burn_no_shine);
			break;
		case setActivity.MOUNTION:

			IB_Hand_Move.setBackgroundResource(R.drawable.hand_no_shine);
			IB_Fluctuate.setBackgroundResource(R.drawable.fluctuate_no_shine);
			IB_Climbing.setBackgroundResource(R.drawable.climbing_no_shine);
			IB_Interval.setBackgroundResource(R.drawable.interval_no_shine);
			IB_LostWeight.setBackgroundResource(R.drawable.lose_eight_no_shine);
			IB_Mountion.setBackgroundResource(R.drawable.mountion_choice);
			IB_Burn.setBackgroundResource(R.drawable.burn_no_shine);
			break;
		case setActivity.BURN:

			IB_Hand_Move.setBackgroundResource(R.drawable.hand_no_shine);
			IB_Fluctuate.setBackgroundResource(R.drawable.fluctuate_no_shine);
			IB_Climbing.setBackgroundResource(R.drawable.climbing_no_shine);
			IB_Interval.setBackgroundResource(R.drawable.interval_no_shine);
			IB_LostWeight.setBackgroundResource(R.drawable.lose_eight_no_shine);
			IB_Mountion.setBackgroundResource(R.drawable.mountion_no_shine);
			IB_Burn.setBackgroundResource(R.drawable.burn_choice);
			break;

		default:
			break;
		}
	}

	/*
	 * 点击按键板的模式键，执行此方法
	 */
	public void pressProgramkey() {

		Log.i("currentActivity.programType", "" + currentActivity.programType);
		currentActivity.programType = (currentActivity.programType < currentActivity.BURN) ? currentActivity.programType
				: 0;

		if (currentActivity.isOrNoFirstInProjectFragment) {
			displayProgramType(currentActivity.programType);
			currentActivity.isOrNoFirstInProjectFragment = false;
		} else {
			displayProgramType(++(currentActivity.programType));
		}
	}

	/*
	 * 提示
	 */
	private void hint() {

		AlertDialog.Builder builder = new AlertDialog.Builder(currentActivity);
		builder.setTitle("提示");
		builder.setMessage("现在是运行状态，禁止切换模式！！");
		builder.setPositiveButton("确定", null);
		builder.show();
	}

	/*
	 * 查找id
	 */
	private void findViewById(View view) {

		mBackGround = (ImageView) view.findViewById(R.id.IV_BackGround);

		// 手动，起伏，间歇
		IB_Hand_Move = (ImageButton) view.findViewById(R.id.IB_Hand_Move);
		IB_Fluctuate = (ImageButton) view.findViewById(R.id.IB_Fluctuate);
		IB_Climbing = (ImageButton) view.findViewById(R.id.IB_Climbing);
		IB_Interval = (ImageButton) view.findViewById(R.id.IB_Interval);
		IB_LostWeight = (ImageButton) view.findViewById(R.id.IB_LostWeight);
		IB_Mountion = (ImageButton) view.findViewById(R.id.IB_Mountion);
		IB_Burn = (ImageButton) view.findViewById(R.id.IB_Burn);
	}

}
