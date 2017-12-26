package com.ada.jw;

import com.ada.mcu.service.Beep;
import com.semicircularmenu.libary.SemiCircularRadialMenu;
import com.semicircularmenu.libary.SemiCircularRadialMenuItem;
import com.semicircularmenu.libary.SemiCircularRadialMenuItem.OnSemiCircularRadialMenuPressed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

public class nowModeGoalFragment extends Fragment {


	 public static nowModeGoalFragment newInstance(Activity mActivity) {
	
	 nowModeGoalFragment mNowModeGoalFragment = new
	 nowModeGoalFragment();
	
	 mNowModeGoalFragment.currentActivity = (setActivity) mActivity;
	
	 return mNowModeGoalFragment;
	 }
	private setActivity currentActivity;
	private View view;

	private ImageView mBackGround;
	private SemiCircularRadialMenu mMenu;
	private SemiCircularRadialMenuItem mProject, mHeart, mGoal;

	// 时间，距离，热量
	private ImageButton IB_Time;
	private ImageButton IB_Distance;
	private ImageButton IB_Hot;
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		
		//currentActivity = (setActivity)activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.activity_nowmode_goal, null, false);
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
		
		currentActivity.isOrNoFirstInModeFragment = true;
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

		// 时间
		IB_Time.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Beep.beep(getActivity().getApplication());
				currentActivity.programType = currentActivity.TIME;
				displayModeType(currentActivity.programType);
				currentActivity.switchFragment = 1;
				currentActivity.switchFragment(currentActivity.switchFragment);
			}
		});

		// 热量
		IB_Hot.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Beep.beep(getActivity().getApplication());
				currentActivity.programType = currentActivity.HOT;
				displayModeType(currentActivity.programType);
				currentActivity.switchFragment = 1;
				currentActivity.switchFragment(currentActivity.switchFragment);
			}
		});

		// 距离
		IB_Distance.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Beep.beep(getActivity().getApplication());
				currentActivity.programType = currentActivity.DISTANCE;
				displayModeType(currentActivity.programType);
				currentActivity.switchFragment = 1;
				currentActivity.switchFragment(currentActivity.switchFragment);
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

		mProject.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {
			@Override
			public void onMenuItemPressed() {

				Beep.beep(getActivity().getApplication());
		
				currentActivity.switchFragment = 2;
				currentActivity.switchFragment(currentActivity.switchFragment);
			}
		});
	}

/*
 * 判断显示对应图标
 */
	protected void displayModeType(int programType) {
		
		switch (currentActivity.programType) {
		case setActivity.HOT:
			
			IB_Hot.setBackgroundResource(R.drawable.hot_shine);
			IB_Distance.setBackgroundResource(R.drawable.distance_no_shine);
			IB_Time.setBackgroundResource(R.drawable.time_no_shine);
			break;
	case setActivity.DISTANCE:
			IB_Distance.setBackgroundResource(R.drawable.distance_shine);
			IB_Time.setBackgroundResource(R.drawable.time_no_shine);
			IB_Hot.setBackgroundResource(R.drawable.hot_no_shine);
			break;
	case setActivity.TIME:
			IB_Time.setBackgroundResource(R.drawable.time_shine);
			IB_Distance.setBackgroundResource(R.drawable.distance_no_shine);
			IB_Hot.setBackgroundResource(R.drawable.hot_no_shine);
		break;

		default:
			break;
		}
	}
	
public void  pressModeKey(){
	
	Log.i("currentActivity.programType", "" + currentActivity.programType);
	currentActivity.programType = (currentActivity.programType < currentActivity.TIME)?currentActivity.programType:10;
	if (currentActivity.isOrNoFirstInModeFragment) {
		displayModeType(currentActivity.programType);
		currentActivity.isOrNoFirstInModeFragment = false;
	} else {
		displayModeType(++(currentActivity.programType));
	}	
}
	

	/*
	 * 初始化界面
	 */
	private void inItUi() {

		currentActivity.programType = 10;
		
		mHeart = new SemiCircularRadialMenuItem("camera", getResources()
				.getDrawable(R.drawable.ic_action_camera), "camera");
		mProject = new SemiCircularRadialMenuItem("dislike", getResources()
				.getDrawable(R.drawable.ic_action_dislike), "dislike");
		mGoal = new SemiCircularRadialMenuItem("info", getResources()
				.getDrawable(R.drawable.ic_action_info), "Info");

		mMenu.addMenuItem(mGoal.getMenuID(), mGoal);
		mMenu.addMenuItem(mHeart.getMenuID(), mHeart);
		mMenu.addMenuItem(mProject.getMenuID(), mProject);

		mBackGround.setVisibility(View.INVISIBLE);
		currentActivity.isOrNoAtsetFragment = false;
	}

	/*
	 * 查找id
	 */
	private void findViewById(View view) {

		IB_Time = (ImageButton) view.findViewById(R.id.IB_Time);
		IB_Hot = (ImageButton) view.findViewById(R.id.IB_Hot);
		IB_Distance = (ImageButton) view.findViewById(R.id.IB_Distacne);

		mBackGround = (ImageView) view
				.findViewById(R.id.IV_NowModeGoal_Background);

		mMenu = (SemiCircularRadialMenu) view.findViewById(R.id.Project_menu);
	}
}
