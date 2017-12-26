package com.ada.jw;

import java.util.ArrayList;
import java.util.HashMap;

import com.ada.mcu.service.ProgramData;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class taskFragment extends Fragment {

	private ImageView IV_TaskOptions;
	private TextView TV_TaskOptions;
	private TextView TV_TaskOptionsText;
	private ImageView IV_TaskOptionsIcon;

	private HorizontalListView mHorizontalListView;
	private HistogramAdapter adapter;
	private ArrayList<HashMap<String, Integer>> datas;
	private int typeIndex;

	private setActivity currentActivity;
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.activity_task, null, false);
		return view;
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		typeIndex = -1;
		findViewById(view);
		inItUi();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

	}

	/*
	 * 界面数据初始化
	 */
	private void inItUi() {

		currentActivity = (setActivity) getActivity();
		currentActivity.isOrNoAtsetFragment = false;
		currentActivity.isOrNoProgram = true;

		switch (currentActivity.programType) {
		case 0:
			break;
		case setActivity.DISTANCE:

			IV_TaskOptions.setBackgroundResource(R.drawable.task_distance);
			TV_TaskOptions.setText(R.string.Distance2);
			TV_TaskOptionsText.setText("0 " + "min");
			IV_TaskOptionsIcon.setBackgroundResource(R.drawable.task_time);
			break;
		case setActivity.HOT:
			IV_TaskOptions.setBackgroundResource(R.drawable.task_heat);
			TV_TaskOptions.setText(R.string.Heat);
			TV_TaskOptionsText.setText("0  " + "min");
			IV_TaskOptionsIcon.setBackgroundResource(R.drawable.task_time);
			break;
		case setActivity.HANDMOVE:

			typeIndex = 0;
			IV_TaskOptions.setBackgroundResource(R.drawable.hand_task);
			TV_TaskOptions.setText(R.string.Hand);
			TV_TaskOptionsText.setText(currentActivity.Time_Text + "min");
			IV_TaskOptionsIcon.setBackgroundResource(R.drawable.hand_task);
			break;
		case setActivity.FLUCTUATE:

			typeIndex = 3;
			IV_TaskOptions.setBackgroundResource(R.drawable.fluctuate_task);
			TV_TaskOptions.setText(R.string.Fluctuate);
			TV_TaskOptionsText.setText(currentActivity.Time_Text + "min");
			IV_TaskOptionsIcon.setBackgroundResource(R.drawable.task_time);

			break;
		case setActivity.CLIMBING:

			typeIndex = 4;
			IV_TaskOptions.setBackgroundResource(R.drawable.mountion_task);
			TV_TaskOptions.setText(R.string.Mountion);
			TV_TaskOptionsText.setText(currentActivity.Time_Text + "min");
			IV_TaskOptionsIcon.setBackgroundResource(R.drawable.task_time);

			break;
		case setActivity.INTERVAL:

			typeIndex = 5;
			IV_TaskOptions.setBackgroundResource(R.drawable.interval_task);
			TV_TaskOptions.setText(R.string.Interval);
			TV_TaskOptionsText.setText(currentActivity.Time_Text + "min");
			IV_TaskOptionsIcon.setBackgroundResource(R.drawable.task_time);
			break;
		case setActivity.LOSTWEIGHT:

			typeIndex = 2;
			IV_TaskOptions.setBackgroundResource(R.drawable.lostweight_task);
			TV_TaskOptions.setText(R.string.LostWeight);
			TV_TaskOptionsText.setText(currentActivity.Time_Text + "min");
			IV_TaskOptionsIcon.setBackgroundResource(R.drawable.task_time);
			break;
		case setActivity.MOUNTION:

			typeIndex = 1;
			IV_TaskOptions.setBackgroundResource(R.drawable.mountion_task);
			TV_TaskOptions.setText(R.string.Mountion);
			TV_TaskOptionsText.setText(currentActivity.Time_Text + "min");
			IV_TaskOptionsIcon.setBackgroundResource(R.drawable.task_time);

			break;
		case setActivity.BURN:

			typeIndex = 6;
			IV_TaskOptions.setBackgroundResource(R.drawable.burn_task);
			TV_TaskOptions.setText(R.string.Burn);
			TV_TaskOptionsText.setText(currentActivity.Time_Text + "min");
			IV_TaskOptionsIcon.setBackgroundResource(R.drawable.task_time);
			break;
		case setActivity.HEART65:

			IV_TaskOptions.setBackgroundResource(R.drawable.heat_low);
			TV_TaskOptions.setText(R.string.HeartLow);
			TV_TaskOptionsText.setText(currentActivity.Time_Text + "min");
			IV_TaskOptionsIcon.setBackgroundResource(R.drawable.task_time);
			break;
		case setActivity.HEART75:

			IV_TaskOptions.setBackgroundResource(R.drawable.heat_mid);
			TV_TaskOptions.setText(R.string.HeartMid);
			TV_TaskOptionsText.setText(currentActivity.Time_Text + "min");
			IV_TaskOptionsIcon.setBackgroundResource(R.drawable.task_time);
			break;
		case setActivity.HEART85:

			IV_TaskOptions.setBackgroundResource(R.drawable.heat_hight);
			TV_TaskOptions.setText(R.string.HeartHigh);
			TV_TaskOptionsText.setText(currentActivity.Time_Text + "min");
			IV_TaskOptionsIcon.setBackgroundResource(R.drawable.task_time);
			break;
		case setActivity.TIME:

			IV_TaskOptions.setBackgroundResource(R.drawable.task_time);
			TV_TaskOptions.setText(R.string.Time);
			TV_TaskOptionsText.setText(currentActivity.Time_Text + "min");
			IV_TaskOptionsIcon.setBackgroundResource(R.drawable.task_time);
			break;

		default:
			break;
		}

		initListView();

	}

	private void initListView() {
		// TODO Auto-generated method stub
		mHorizontalListView = (HorizontalListView) view
				.findViewById(R.id.list_view);
		initData(typeIndex);
		adapter = new HistogramAdapter(currentActivity,
				R.layout.progress_bars_item, datas);
		adapter.setListView(mHorizontalListView);
		mHorizontalListView.setAdapter(adapter);
	}

	private void initData(int i) {
		// TODO Auto-generated method stub
		ProgramData mProgramData = ProgramData.getInstance();
		datas = new ArrayList<HashMap<String, Integer>>();
		HashMap<String, Integer> temp;
		for (int j = 0; j < mProgramData.programSpeedArray[0].length; j++) {
			temp = new HashMap<String, Integer>();
			if (typeIndex != -1) {
				temp.put("speed", mProgramData.programSpeedArray[i][j]);
				temp.put("incline", mProgramData.programInclineArray[i][j]);
			} else {
				temp.put("speed", 0);
				temp.put("incline", 0);
			}
			datas.add(temp);
		}
	}

	/*
	 * 查找id
	 */
	private void findViewById(View view) {

		IV_TaskOptions = (ImageView) view.findViewById(R.id.IV_TaskOptions);
		TV_TaskOptions = (TextView) view.findViewById(R.id.TV_TaskOptions);
		TV_TaskOptionsText = (TextView) view
				.findViewById(R.id.TV_TaskOptionsText);
		IV_TaskOptionsIcon = (ImageView) view
				.findViewById(R.id.IV_TaskOptionsIcon);
	}

}
