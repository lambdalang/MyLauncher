package com.ada.jw;

import com.ada.mcu.service.Beep;
import com.semicircularmenu.libary.SemiCircularRadialMenu;
import com.semicircularmenu.libary.SemiCircularRadialMenuItem;
import com.semicircularmenu.libary.SemiCircularRadialMenuItem.OnSemiCircularRadialMenuPressed;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

public class nowModeHeartFragment extends Fragment {
	
	private ImageView mBackGround;
	private SemiCircularRadialMenu mMenu;
	private SemiCircularRadialMenuItem mProject, mHeart, mGoal;
	
//心率
	private ImageButton IB_Heart_65;
	private ImageButton IB_Heart_75;
	private ImageButton IB_Heart_85;
	
	private setActivity  currentActivity ;
	private View view;
	
	
		@Override
		public View onCreateView(LayoutInflater inflater,
				@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			 view =  inflater.inflate(R.layout.activity_nowmode_heart, null, false);
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
		
/*
 * 初始化界面
 * */
private void inItUi(){
	
	mHeart = new SemiCircularRadialMenuItem("camera", getResources().getDrawable(R.drawable.ic_action_camera), "camera");
	mProject = new SemiCircularRadialMenuItem("dislike", getResources().getDrawable(R.drawable.ic_action_dislike), "dislike");
	mGoal = new SemiCircularRadialMenuItem("info", getResources().getDrawable(R.drawable.ic_action_info), "Info");
	
	mMenu.addMenuItem(mGoal.getMenuID(), mGoal);
	mMenu.addMenuItem(mProject.getMenuID(), mProject);
	mMenu.addMenuItem(mHeart.getMenuID(), mHeart);
	
	mBackGround.setVisibility(View.INVISIBLE);
	currentActivity =(setActivity) getActivity();
	currentActivity.isOrNoAtsetFragment = false;
}

/*
 * 监听事件
 * */		
private void listionEvent(){
	
			mMenu.setOnMenuVisibleChangeListener(new SemiCircularRadialMenu.OnMenuVisibleChangeListener() {
				
				@Override
				public void onVisibleChanged(boolean isVisible) {
					// TODO Auto-generated method stub
					
					if (isVisible) {
						mBackGround.setVisibility(View.VISIBLE);
					}else {
						mBackGround.setVisibility(View.INVISIBLE);
					}
				}
			});
	
	//距离
	IB_Heart_65.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
										
			Beep.beep(getActivity().getApplication());
			currentActivity.programType = currentActivity.HEART65;
			currentActivity.switchFragment = 1;
			currentActivity.switchFragment(currentActivity.switchFragment);
			}
	});
	
	//距离
	IB_Heart_75.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
										
			Beep.beep(getActivity().getApplication());
			currentActivity.programType = currentActivity.HEART75;
			currentActivity.switchFragment = 1;
			currentActivity.switchFragment(currentActivity.switchFragment);
			}
	});
	
	//距离
	IB_Heart_85.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
								
			Beep.beep(getActivity().getApplication());
			currentActivity.programType = currentActivity.HEART85;
			currentActivity.switchFragment = 1;
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
 * 查找id
 * */
private void findViewById(View view){
	
	IB_Heart_65 = (ImageButton)view.findViewById(R.id.IB_Heart_65);
	IB_Heart_75 = (ImageButton)view.findViewById(R.id.IB_Heart_75);
	IB_Heart_85 = (ImageButton)view.findViewById(R.id.IB_Heart_85);
	
	mBackGround = (ImageView)view.findViewById(R.id.IV_NowModeHeart_Background);
	
	mMenu = (SemiCircularRadialMenu)view. findViewById(R.id.Project_menu);
	}

}
