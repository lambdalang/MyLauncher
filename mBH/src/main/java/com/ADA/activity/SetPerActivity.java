package com.ADA.activity;

import java.util.SortedMap;

import com.ADA.mbh.R;

import android.R.anim;
import android.R.color;
import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class SetPerActivity extends BaseActivity {

    //三个设置，确定，返回
    private Button IB_Set;
    private Button IB_Confirm;
    private Button IB_Cancel;

    private ImageView IV_BoyOrGril;
    //性别，年龄，身高，
    private LinearLayout IV_Sex;
    private LinearLayout IB_Age;
    private LinearLayout IB_Height;
    private LinearLayout IB_Weight;

    //时间，距离，热量布局
    private View Time_Layout;
    private View Hot_Layout;
    private View Distance_Layout;
    private TextView TV_Time_Show;
    private TextView TV_Hot_Show;
    private TextView TV_Distance_Show;

    //显示框
    public static String Temp = "";//存放键盘输入的字符串，然后根据判断放入对应的数据里
    public String Type;                            //根据要输入的类型，年龄； 身高； 体重；时间；距离；热量
    public String Flag;                            //根据要输入的类型，年龄； 身高； 体重；时间；距离；热量
    private TextView TV_Age_Show;
    public static String Age_Text = "";
    private TextView TV_Height_Show;
    public static String Height_Text = "";
    private TextView TV_Weight_Show;
    public static String Weight_Text = "";
    //时间距离热量
    public static String Time_Text = "";
    public static String Hot_Text = "";
    public static String Distance_Text = "";
    //传递给TaskActivity的目标类型
    public static String GoalShow = "";

    //键盘布局
    private View RL_Keyboard_Layout;
    //键盘按钮
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
    private Button Btn_Enter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_per);

        //继承的初始化
        InitBase();
        BaselistionEvent();
        //初始化
        init();
        inItShow();
        listionEvent();
    }

    private boolean isBoy=true;

    private void listionEvent() {
        // TODO Auto-generated method stub
        IV_Sex.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                isBoy=!isBoy;
                Flag = "IV_Sex";
                if(isBoy){
                    ((TextView)IV_Sex.getChildAt(1)).setText(getString(R.string.str_boy));
                    IV_BoyOrGril.setImageResource(R.drawable.man_nospace);
                }else{
                    ((TextView)IV_Sex.getChildAt(1)).setText(getString(R.string.str_girl));
                    IV_BoyOrGril.setImageResource(R.drawable.woman_nospace);
                }
                //setBackgroundResource();
            }
        });

	/*
     *监听： 点击年龄，身高，体重事件
	 */
        //点击IB_Age，编辑年龄
        IB_Age.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //显示键盘
                Flag = "IB_Age";
                Temp = "";//中间变量清零
                RL_Keyboard_Layout.setVisibility(View.VISIBLE);
                //TV_Age_Show.setBackgroundResource(R.drawable.age_text);
                //TV_Age_Show.setBackgroundColor(Color.argb(255, 0, 255, 0));
                //setBackgroundResource();
            }
        });

        //点击IIB_Height，编辑身高
        IB_Height.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //显示键盘
                Flag = "IB_Height";
                Temp = "";//中间变量清零
                RL_Keyboard_Layout.setVisibility(View.VISIBLE);
                //TV_Height_Show.setBackgroundResource(R.drawable.height_text);
                //setBackgroundResource();
            }
        });

        //点击IIB_Weight，编辑体重
        IB_Weight.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //显示键盘
                Flag = "IB_Weight";
                Temp = "";//中间变量清零
                RL_Keyboard_Layout.setVisibility(View.VISIBLE);
                //TV_Weight_Show.setBackgroundResource(R.drawable.weight_text);
                //setBackgroundResource();
            }
        });

        Time_Layout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //显示键盘
                Flag = "Time_Layout";
                Temp = "";//中间变量清零
                RL_Keyboard_Layout.setVisibility(View.VISIBLE);
                //TV_Time_Show.setBackgroundResource(R.drawable.time_text);
                //setBackgroundResource();
            }
        });

        Hot_Layout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //显示键盘
                Flag = "Hot_Layout";
                Temp = "";//中间变量清零
                RL_Keyboard_Layout.setVisibility(View.VISIBLE);
                //TV_Hot_Show.setBackgroundResource(R.drawable.hot_text);
                //setBackgroundResource();
            }
        });

        Distance_Layout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //显示键盘
                Flag = "Distance_Layout";
                Temp = "";//中间变量清零
                RL_Keyboard_Layout.setVisibility(View.VISIBLE);
                //TV_Distance_Show.setBackgroundResource(R.drawable.distance_text);
                //setBackgroundResource();
            }
        });

	/*
	 * 监听：键盘点击事件
	 */
        //点击 1
        Btn_One.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Temp += "1";
                Sort();
            }
        });

        //点击 2
        Btn_Two.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Temp += "2";
                Sort();
            }
        });

        //点击 3
        Btn_Three.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Temp += "3";
                Sort();
            }
        });

        //点击 4
        Btn_Four.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Temp += "4";
                Sort();
            }
        });

        //点击 5
        Btn_Five.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Temp += "5";
                Sort();
            }
        });

        //点击 6
        Btn_Six.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Temp += "6";
                Sort();
            }
        });

        //点击 7
        Btn_Seven.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Temp += "7";
                Sort();
            }
        });

        //点击 8
        Btn_Eight.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Temp += "8";
                Sort();
            }
        });

        //点击 9
        Btn_Nine.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Temp += "9";
                Sort();
            }
        });

        //点击 0
        Btn_Zero.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Temp += "0";
                Sort();
            }
        });

        //点击 .
        Btn_Point.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                Temp += ".";
//                Sort();
            }
        });

        //点击enter键
        Btn_Enter.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                RL_Keyboard_Layout.setVisibility(View.INVISIBLE);

            }
        });

        //点击Btn_Delete键
        Btn_Delete.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(Temp!=null&&Temp.length()>0){
                    Temp = Temp.substring(0, Temp.length() - 1);
                    Sort();
                }
            }
        });

	/*
	 * 监听设定，确定，返回按钮点击事件
	 */
        //点击设定键
        IB_Set.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

//                IB_Age.setEnabled(true);
//                IB_Height.setEnabled(true);
//                IB_Weight.setEnabled(true);
            }
        });

        //点击确定键
        IB_Confirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

//                IB_Age.setEnabled(false);
//                IB_Height.setEnabled(false);
//                IB_Weight.setEnabled(false);
//
//                TV_Age_Show.setBackgroundResource(R.drawable.age_text_back);
//                TV_Height_Show.setBackgroundResource(R.drawable.height_text_back);
//                TV_Weight_Show.setBackgroundResource(R.drawable.weight_text_back);
//                TV_Time_Show.setBackgroundResource(R.drawable.time_text_back);
//                TV_Hot_Show.setBackgroundResource(R.drawable.hot_text_back);
//                TV_Distance_Show.setBackgroundResource(R.drawable.distance_text_back);


                Intent intent = new Intent();
                intent.setClass(SetPerActivity.this, TaskActivity.class);
                Bundle bundle = new Bundle();
                //传给taskActivity,根据IB_Type_String显示项目内容
                bundle.putString("IB_Type_String", Type);
                //传给taskActivity,根据IV_TaskOptionsIcon区别目标内容
                if (Type.equals("IB_Time") || Type.equals("IB_Burn") || Type.equals("IB_Mountion") || Type.equals("IB_LostWeight")
                        || Type.equals("IB_Interval") || Type.equals("IB_Climbing") || Type.equals("IB_Fluctuate")
                        || Type.equals("IB_Heart_65") || Type.equals("IB_Heart_75") || Type.equals("IB_Heart_85")) {
                    GoalShow = Time_Text;
                    bundle.putString("GoalShow", GoalShow);
                } else if (Type.equals("IB_Hot")) {

                    GoalShow = Hot_Text;
                    bundle.putString("GoalShow", GoalShow);
                } else if (Type.equals("IB_Distance")) {

                    GoalShow = Distance_Text;
                    mHandler.sendEmptyMessage(1);

                    bundle.putString("GoalShow", GoalShow);
                }

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //点击返回
        IB_Cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SetPerActivity.this.finish();
            }
        });
    }

    private void setBackgroundResource() {
        if(Flag.equals("IV_Sex")){
            IV_Sex.setBackgroundResource(R.drawable.sex_back_ground_onclick);
        }else{
            IV_Sex.setBackgroundResource(R.drawable.sex_back_ground);
        }
        if(Flag.equals("IB_Age")){
            IB_Age.setBackgroundResource(R.drawable.age_back_ground_onclick);
            TV_Age_Show.setBackgroundResource(R.drawable.age_text);
        }else{
            IB_Age.setBackgroundResource(R.drawable.age_back_ground);
            TV_Age_Show.setBackgroundResource(0);
        }
        if(Flag.equals("IB_Height")){
            IB_Height.setBackgroundResource(R.drawable.height_back_ground_onclick);
            TV_Height_Show.setBackgroundResource(R.drawable.height_text);
        }else{
            IB_Height.setBackgroundResource(R.drawable.height_back_ground);
            TV_Height_Show.setBackgroundResource(0);
        }
        if(Flag.equals("IB_Weight")){
            IB_Weight.setBackgroundResource(R.drawable.weight_back_ground_onclick);
            TV_Weight_Show.setBackgroundResource(R.drawable.weight_text);
        }else{
            IB_Weight.setBackgroundResource(R.drawable.weight_back_ground);
            TV_Weight_Show.setBackgroundResource(0);
        }
        if(Flag.equals("Time_Layout")){
            Time_Layout.setBackgroundResource(R.drawable.time_set_onclick);
            TV_Time_Show.setBackgroundResource(R.drawable.time_text);
        }else{
            Time_Layout.setBackgroundResource(R.drawable.time_set);
            TV_Time_Show.setBackgroundResource(0);
        }
        if(Flag.equals("Hot_Layout")){
            Hot_Layout.setBackgroundResource(R.drawable.hot_set_onclick);
            TV_Hot_Show.setBackgroundResource(R.drawable.hot_text);
        }else{
            Hot_Layout.setBackgroundResource(R.drawable.hot_set);
            TV_Hot_Show.setBackgroundResource(0);
        }
        if(Flag.equals("Distance_Layout")){
            Distance_Layout.setBackgroundResource(R.drawable.distance_set_onclick);
            TV_Distance_Show.setBackgroundResource(R.drawable.distance_text);
        }else{
            Distance_Layout.setBackgroundResource(R.drawable.distance_set);
            TV_Distance_Show.setBackgroundResource(0);
        }
    }

    private void inItShow() {
        // TODO Auto-generated method stub

        RL_Big_Layout.setVisibility(View.GONE);
        RL_Big_Layout_Right.setVisibility(View.GONE);
        RL_Middle_Layout.setVisibility(View.GONE);

/**
 * try判断从ModeNowActivity进入此activity
 * 从模式选择进入，不用处理
 */
        try {

            Temp = "";//中间变量清零(键盘缓冲数据删除)
            Log.i("进入Try 进入Try进入Try进入Try   ", " 000000000000000000000000000000");
            Intent intent = this.getIntent();
            Bundle bundle = intent.getExtras();

            Type = bundle.getString("IB_Type_String");
            if (Type.equals("IB_Time") || Type.equals("IB_Burn") || Type.equals("IB_Mountion") || Type.equals("IB_LostWeight")
                    || Type.equals("IB_Interval") || Type.equals("IB_Climbing") || Type.equals("IB_Fluctuate")
                    || Type.equals("IB_Heart_65") || Type.equals("IB_Heart_75") || Type.equals("IB_Heart_85")) {

                //TV_Time_Show.setBackgroundResource(R.drawable.time_text);
                Hot_Layout.setVisibility(View.GONE);
                Time_Layout.setVisibility(View.VISIBLE);
                Distance_Layout.setVisibility(View.GONE);
                //RL_Keyboard_Layout.setVisibility(View.VISIBLE);
            } else if (Type.equals("IB_Hot")) {

                //TV_Hot_Show.setBackgroundResource(R.drawable.hot_text);
                Hot_Layout.setVisibility(View.VISIBLE);
                Time_Layout.setVisibility(View.GONE);
                Distance_Layout.setVisibility(View.GONE);
                //RL_Keyboard_Layout.setVisibility(View.VISIBLE);
            } else if (Type.equals("IB_Distance")) {

                //TV_Distance_Show.setBackgroundResource(R.drawable.distance_text);
                Hot_Layout.setVisibility(View.GONE);
                Time_Layout.setVisibility(View.GONE);
                Distance_Layout.setVisibility(View.VISIBLE);
                //RL_Keyboard_Layout.setVisibility(View.VISIBLE);
            }
            //RL_Keyboard_Layout.setVisibility(View.GONE);

        } catch (Exception e) {
            // TODO: handle exception
        } finally {
        }

//        //给按钮分类
//        IB_Age.setEnabled(false);
//        IB_Height.setEnabled(false);
//        IB_Weight.setEnabled(false);

        //布局显示
        RL_Small_Layout.setVisibility(View.VISIBLE);
        RL_Small_Layout_Right.setVisibility(View.VISIBLE);
        RL_Top_Layout.setVisibility(View.VISIBLE);


    }

    /*
     * 根据type判断点击的谁（年龄，身高，体重），然后在谁里面显示出来
     */
    private void Sort() {
        // TODO Auto-generated method stub
        if(Temp.equals("")) Temp="0";

        if (Flag.equals("IB_Age")) {

            if (Temp.length() > 2) {
                Temp = Temp.substring(0, 2);
            }
            TV_Age_Show.setText(Temp);
            Age_Text = Temp;
            Log.i("SetPerActivity", Age_Text);
        } else if (Flag.equals("IB_Height")) {

            if (Temp.length() > 3) {

                Temp = Temp.substring(0, 3);
            }
            TV_Height_Show.setText(Temp);
            Height_Text = Temp;
            Log.i("SetPerActivity", Height_Text);
        } else if (Flag.equals("IB_Weight")) {

            if (Temp.length() > 3) {
                Temp = Temp.substring(0, 3);
            }
            TV_Weight_Show.setText(Temp);
            Weight_Text = Temp;
            Log.i("SetPerActivity", Weight_Text);
        } else if (Flag.equals("Time_Layout")) {

            if (Temp.length() > 4) {
                Temp = Temp.substring(0, 4);
            }
            TV_Time_Show.setText(Temp);
            //获取到值，便于传递到TaskActivity
            Time_Text = Temp;
        } else if (Flag.equals("Distance_Layout")) {

            if (Temp.length() > 4) {
                Temp = Temp.substring(0, 4);
            }
            TV_Distance_Show.setText(Temp);
            //获取到值，便于传递到TaskActivity
            Distance_Text = Temp;
        } else if (Flag.equals("Hot_Layout")) {

            if (Temp.length() > 4) {
                Temp = Temp.substring(0, 4);
            }
            TV_Hot_Show.setText(Temp);
            //获取到值，便于传递到TaskActivity
            Hot_Text = Temp;
        }
        if(Temp.equals("0")) Temp="";

    }

    /*
     * 初始化
     */
    private void init() {

        //设定， 确认，取消
        IB_Set = (Button) findViewById(R.id.IB_Set);
        IB_Confirm = (Button) findViewById(R.id.IB_Confirm);
        IB_Cancel = (Button) findViewById(R.id.IB_Cancel);

        //性别，年龄，身高，体重
        IV_Sex = (LinearLayout) findViewById(R.id.IV_Sex);
        IB_Age = (LinearLayout) findViewById(R.id.IB_Age);
        IB_Height = (LinearLayout) findViewById(R.id.IB_Height);
        IB_Weight = (LinearLayout) findViewById(R.id.IB_Weight);
        //显示图片
        IV_BoyOrGril = (ImageView) findViewById(R.id.IV_Boy);

        TV_Age_Show = (TextView) findViewById(R.id.TV_Age_Show);
        TV_Height_Show = (TextView) findViewById(R.id.TV_Height_Show);
        TV_Weight_Show = (TextView) findViewById(R.id.TV_Weight_Show);
        //键盘布局初始化
        RL_Keyboard_Layout = (RelativeLayout) findViewById(R.id.RL_Keyboard_Layout);
        //键盘按键初始化
        Btn_One = (Button) findViewById(R.id.Btn_One);

        Btn_Two = (Button) findViewById(R.id.Btn_Two);
        Btn_Three = (Button) findViewById(R.id.Btn_Three);
        Btn_Four = (Button) findViewById(R.id.Btn_Four);
        Btn_Five = (Button) findViewById(R.id.Btn_Five);
        Btn_Six = (Button) findViewById(R.id.Btn_Six);
        Btn_Seven = (Button) findViewById(R.id.Btn_Seven);
        Btn_Eight = (Button) findViewById(R.id.Btn_Eight);
        Btn_Nine = (Button) findViewById(R.id.Btn_Nine);
        Btn_Zero = (Button) findViewById(R.id.Btn_Zero);
        Btn_Point = (Button) findViewById(R.id.Btn_Point);
        Btn_Delete = (Button) findViewById(R.id.Btn_Delete);
        Btn_Enter = (Button) findViewById(R.id.Btn_Enter);

        //时间，距离，热量
        Time_Layout = (LinearLayout) findViewById(R.id.Time_Layout);
        Hot_Layout = (LinearLayout) findViewById(R.id.Hot_Layout);
        Distance_Layout = (LinearLayout) findViewById(R.id.Distance_Layout);
        TV_Time_Show = (TextView) findViewById(R.id.TV_Time_Show);
        TV_Hot_Show = (TextView) findViewById(R.id.TV_Hot_Show);
        TV_Distance_Show = (TextView) findViewById(R.id.TV_Distance_Show);
    }
}
