package com.ADA.activity;

import com.ADA.mbh.R;
import com.ADA.mbh.R.drawable;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class TaskActivity extends BaseActivity {

    //停止，暂停，返回
    private Button IB_Task_stop;
    private Button IB_Task_Pause;
    private Button IB_Task_Cancel;

    private ImageView IV_TaskOptions;
    private TextView TV_TaskOptions;
    private TextView TV_TaskOptionsText;
    private ImageView IV_TaskOptionsIcon;

    //接收bundle
    private String Type;//项目
    private String GoalShow;//目标

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        init();
        InitBase();
        BaselistionEvent();

        RL_Big_Layout.setVisibility(View.GONE);
        RL_Big_Layout_Right.setVisibility(View.GONE);
        RL_Middle_Layout.setVisibility(View.GONE);
        try {

            Intent intent = this.getIntent();
            Bundle bundle = intent.getExtras();
            //接收项目
            Type = bundle.getString("IB_Type_String");
            //接收目标
            GoalShow = bundle.getString("GoalShow");

        } catch (Exception e) {
            // TODO: handle exception
        }


//			Log.i("8888888888888888", Type);
        if (Type.equals("IB_Hand")) {

            IV_TaskOptions.setImageResource(R.drawable.hand_task);
            TV_TaskOptions.setText(R.string.Hand);
            TV_TaskOptionsText.setText(R.string.Nothing);
            IV_TaskOptionsIcon.setVisibility(View.INVISIBLE);

        } else if (Type.equals("IB_Mountion")) {

            IV_TaskOptions.setImageResource(R.drawable.mountion_task);
            TV_TaskOptions.setText(R.string.Mountion);
            TV_TaskOptionsText.setText(GoalShow + "min");
            IV_TaskOptionsIcon.setImageResource(R.drawable.time);


        } else if (Type.equals("IB_LostWeight")) {

            IV_TaskOptions.setImageResource(R.drawable.lostweight_task);
            TV_TaskOptions.setText(R.string.LostWeight);
            TV_TaskOptionsText.setText(GoalShow + "min");
            IV_TaskOptionsIcon.setImageResource(R.drawable.time);

        } else if (Type.equals("IB_Fluctuate")) {

            IV_TaskOptions.setImageResource(R.drawable.fluctuate_task);
            TV_TaskOptions.setText(R.string.Fluctuate);
            TV_TaskOptionsText.setText(GoalShow + "min");
            IV_TaskOptionsIcon.setImageResource(R.drawable.time);

        } else if (Type.equals("IB_Climbing")) {

            IV_TaskOptions.setImageResource(R.drawable.climbing_task);
            TV_TaskOptions.setText(R.string.Climbing);
            TV_TaskOptionsText.setText(GoalShow + "min");
            IV_TaskOptionsIcon.setImageResource(R.drawable.time);

        } else if (Type.equals("IB_Interval")) {

            IV_TaskOptions.setImageResource(R.drawable.interval_task);
            TV_TaskOptions.setText(R.string.Interval);
            TV_TaskOptionsText.setText(GoalShow + "min");
            IV_TaskOptionsIcon.setImageResource(R.drawable.time);

        } else if (Type.equals("IB_Burn")) {

            IV_TaskOptions.setImageResource(R.drawable.burn_task);
            TV_TaskOptions.setText(R.string.Burn);
            TV_TaskOptionsText.setText(GoalShow + "min");
            IV_TaskOptionsIcon.setImageResource(R.drawable.time);
        } else if (Type.equals("IB_Distance")) {

            IV_TaskOptions.setVisibility(View.INVISIBLE);
            TV_TaskOptions.setText(R.string.Nothing);
            TV_TaskOptionsText.setText(GoalShow + "Km");
            IV_TaskOptionsIcon.setImageResource(R.drawable.distance);
        } else if (Type.equals("IB_Hot")) {

            IV_TaskOptions.setVisibility(View.INVISIBLE);
            TV_TaskOptions.setText(R.string.Nothing);
            TV_TaskOptionsText.setText(GoalShow + "Kcal");
            IV_TaskOptionsIcon.setImageResource(R.drawable.heart);
        } else if (Type.equals("IB_Heart_65")) {

            IV_TaskOptions.setVisibility(View.INVISIBLE);
            TV_TaskOptions.setText(R.string.HeartLow);
            TV_TaskOptionsText.setText(GoalShow + "min");
            IV_TaskOptionsIcon.setImageResource(R.drawable.time);
        } else if (Type.equals("IB_Heart_75")) {

            IV_TaskOptions.setVisibility(View.INVISIBLE);
            TV_TaskOptions.setText(R.string.HeartMid);
            TV_TaskOptionsText.setText(GoalShow + "min");
            IV_TaskOptionsIcon.setImageResource(R.drawable.time);
        } else if (Type.equals("IB_Heart_85")) {

            IV_TaskOptions.setVisibility(View.INVISIBLE);
            TV_TaskOptions.setText(R.string.HeartHigh);
            TV_TaskOptionsText.setText(GoalShow + "min");
            IV_TaskOptionsIcon.setImageResource(R.drawable.time);
        }

        IB_Task_Cancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });

    }

//		public enum SportType{
//			
//			IB_Hand,
//			IB_Mountion,
//			IB_Burn,
//			IB_LostWeight,
//			IB_Fluctuate,
//			IB_Climbing,
//			IB_Interval,
//		}

    private void init() {
        // TODO Auto-generated method stub

        IB_Task_stop = (Button) findViewById(R.id.IB_Task_stop);
        IB_Task_Pause = (Button) findViewById(R.id.IB_Task_Pause);
        IB_Task_Cancel = (Button) findViewById(R.id.IB_Task_Cancel);

        IV_TaskOptions = (ImageView) findViewById(R.id.IV_TaskOptions);
        TV_TaskOptions = (TextView) findViewById(R.id.TV_TaskOptions);
        TV_TaskOptionsText = (TextView) findViewById(R.id.TV_TaskOptionsText);
        IV_TaskOptionsIcon = (ImageView) findViewById(R.id.IV_TaskOptionsIcon);
    }
}
