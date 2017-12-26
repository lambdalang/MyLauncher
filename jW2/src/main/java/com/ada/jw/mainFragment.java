package com.ada.jw;

import com.ada.mcu.service.Beep;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class mainFragment extends Fragment implements OnClickListener {

	
	private Button BT_trainSet;
	private Button BT_begin;
	
	private setActivity currentActivity;
	private View view;
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		
		currentActivity = (setActivity)activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		 view = inflater.inflate(R.layout.activity_main, null,false);
		 findViewById(view);
		 return view;
		 }
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		currentActivity.RL_commenTop.setVisibility(View.INVISIBLE);
		listionEvent();
	}
	
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
		currentActivity.RL_commenTop.setVisibility(View.VISIBLE);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		Beep.beep(getActivity().getApplication());
		switch (v.getId()) {
		case R.id.BT_begin:
			currentActivity.switchFragment = 6;
			currentActivity.switchFragment(currentActivity.switchFragment);
			break;
		case R.id.BT_trainSet:
			currentActivity.switchFragment = 2;
			currentActivity.switchFragment(currentActivity.switchFragment);
			break;

		default:
			break;
		}
	}
	
	private void listionEvent(){
		
		BT_begin.setOnClickListener(this);
		BT_trainSet.setOnClickListener(this);
	}
	
	private void findViewById(View view){
		
		currentActivity.RL_commenTop = currentActivity.findViewById(R.id.RL_commenTop);
		BT_trainSet = (Button)view.findViewById(R.id.BT_trainSet);
		BT_begin = (Button)view.findViewById(R.id.BT_begin);
	}
	
	
}
