package com.ADA.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ADA.mbh.R;
import com.ADA.mcu.service.IContactUi;
import com.ADA.mcu.service.IServiceCallback;
import com.ADA.mcu.service.MachineData;
import com.ADA.mcu.service.ProgramData;
import com.ADA.mcu.service.ServiceRepliedData;
import com.ADA.mcu.service.SettingData;
import com.mstar.android.tv.TvCommonManager;

public class SetActivity extends BaseActivity {

    private String TAG = "SetActivity";

    //定义
    private ImageButton IB_SetPer;
    private LinearLayout IB_Explorer;
    private LinearLayout IB_Mode_Now;
    private LinearLayout IB_Usb;
    private LinearLayout IB_Movie;
    private LinearLayout IB_Tv;
    private LinearLayout IB_Music;
    private LinearLayout IB_Av;
    private LinearLayout IB_parameterSetting;

    private TextView TV_Set;
    private TextView TV_Explorer;
    private TextView TV_Mode_Now;
    private TextView TV_Usb;
    private TextView TV_Movie;
    private TextView TV_Tv;
    private TextView TV_Music;

    public ServiceRepliedData data;

    // 下位机的定义
    public IContactUi mService;
    private ServiceConnection mConnection;
    private IServiceCallback mCallback;
    public static MachineData mMachineData = null;
    public static int[] repliedBackup = {0, 0, 0, 0, 0};
    private final int UPDATE_RECEIVED_DATA = 1000;
    private final int UPDATE_UI_DATA = 1001;
    // 以上是下位机定义

    public boolean lock = false;//设定锁机，开始计算距离
    public boolean locked = false;//当前状态是否是锁机状态
    public static int setDistanceLimit = 0;
    public static int lockDistance = 0;
    public static int lockDistanceTemp = 0;

    // 选择模式后传给后台
    public ProgramData mProgramData;

    // 个人消耗信息
    public static int startEndMark = 0;

    public int isWarningOpened = 0;
    public static int numCode = 0;

    public static int totalTimeValue = 0;
    public static int totalDistanceValue = 0;

    private int caloryCount;
    private int milesCount;
    private static int timeCount;

    // 公英制标记 0:公制 1：英制
    public static int defaultSing = 0;
    public static int progressSpeedEnglish = 0;// 公英制转换用
    public static Double progressSpeedEnglishCircle = 0.0;// 公英制转换用


    private Handler uiUpdateHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            data = (ServiceRepliedData) msg.obj;
            Log.d(TAG,"data.getAlarmCode()"+data.getAlarmCode());
            Log.d(TAG,"data.getCalories()"+data.getCalories());
            Log.d(TAG,"data.getExecutionMiles()"+data.getExecutionMiles());
            Log.d(TAG,"data.getExecutionTime()"+data.getExecutionTime());
            Log.d(TAG,"data.getHeartBeat()"+data.getHeartBeat());
            Log.d(TAG,"data.getMiles()"+data.getMiles());
            Log.d(TAG,"data.getOilTime()"+data.getOilTime());
            Log.d(TAG,"data.getSelfCheckStatus()"+data.getSelfCheckStatus());
            Log.d(TAG,"data.getTimeCount()"+data.getTimeCount());


            // Log.i(TAG, "LYJ, mMachineData.getCurrentStatus():" +
            //	 mMachineData.getCurrentStatus());
//			 Log.i("data.getAlarmCode()" , " "+data.getAlarmCode());
//			 Log.i("isWarningOpened" ," "+isWarningOpened);
//            if (locked && switchFragment != 13) {
//                switchFragment = 13;
//                switchFragment(switchFragment);
//            }

            int currentStatus = mMachineData.getCurrentStatus();
            if (currentStatus == 1) {
                Log.i("状态是1 startEndMark", " " + startEndMark);
                startEndMark = 1;
            }

            if (currentStatus != 0 && currentStatus != 3) {
                repliedBackup[0] = data.getTimeCount();
                repliedBackup[1] = data.getMiles();
                repliedBackup[2] = data.getCalories();
                repliedBackup[3] = mMachineData.getOperatingFrequency();
                repliedBackup[4] = mProgramData.getProgramIndex();
//				 Log.i(TAG, "LYJ " + repliedBackup[0] + " " + repliedBackup[1]
//				 + " " + repliedBackup[2] + " " + repliedBackup[3] + " "
//				 + repliedBackup[4]);
            } else if (currentStatus == 0 && data.getAlarmCode() == 0 && isWarningOpened == 0) {
                // 任务完成显示个人消耗信息
                Log.i("startEndMark", " " + startEndMark);
                if (startEndMark == 1) {
                    startEndMark = 0;
                    mProgramData.reset();
                    if (lockDistance <= setDistanceLimit && setDistanceLimit >= 0 && locked == false) {
                        //pressStopShowConsumeDialogue();
                        //按下停止出现个人消耗信息框,任务结束也需要显示消耗信息
                    }
                }
            } else if (currentStatus == 3) {
                if (data.getSelfCheckStatus() == 1) {
                    //warning();
                    //报警
                } else if (locked == false) {
//                    switchFragment = 0;
//                    switchFragment(switchFragment);
                }
            }
            // Log.i("data.getAlarmCode()", ""+data.getAlarmCode());
            if ((data.getAlarmCode() != 0) && (isWarningOpened == 0)) {
                isWarningOpened = 1;
                numCode = data.getAlarmCode();
                //Log.i("data.getAlarmCode()", "" + data.getAlarmCode());

                // 警告
                //warning();
            }
            //	Log.i("data.getSelfCheckStatus()", ""+data.getSelfCheckStatus());

            switch (msg.what) {
                case UPDATE_RECEIVED_DATA:

                    TV_Heart_Show_Top.setText("" + (data.getHeartBeat() & 0xff));
                    TV_Time_Show_Top.setText(formatIntToTime(data.getTimeCount()));
                    Log.i("repliedBackup[0]", "" + repliedBackup[0]);
                    Log.i("repliedBackup[1]", "" + repliedBackup[1]);
                    Log.i("repliedBackup[2]", "" + repliedBackup[2]);
                    Log.i("repliedBackup[3]", "" + repliedBackup[3]);
                    Log.i("时间显示", formatIntToTime(data.getTimeCount()));
                    int distanceResultString = data.getMiles();
                    int head = distanceResultString / 1000;
                    int tail = distanceResultString % 1000;
                    String pointRightText = "";
                    if (tail / 100 > 0) {
                        pointRightText = "" + tail;
                    } else if (tail / 10 > 0) {
                        pointRightText = "0" + tail;
                    } else {
                        pointRightText = "00" + tail;
                    }
                    TV_Distance_Show_Top.setText(head + "." + pointRightText);

                    long currentCalorie = data.getCalories();
                    tail = (int) currentCalorie % 1000;
                    if (tail / 100 > 0) {
                        pointRightText = "" + tail;
                    } else if (tail / 10 > 0) {
                        pointRightText = "0" + tail;
                    } else {
                        pointRightText = "00" + tail;
                    }
                    TV_Heat_Show_Top.setText(currentCalorie / 1000 + "."
                            + pointRightText);

                    metricAndEnglishSwitch(mMachineData.getOperatingFrequency());

                    gradientShow(mMachineData.getIncline());
                    if (data.getExecutionMiles() / 100000 > 0) {
                        totalDistanceValue = (int) data.getExecutionMiles() / 100000;
                        Log.i("lock", "" + lock);
                        Log.i("WDHtotalDistanceValue", "" + totalDistanceValue);
                        Log.i("WDHsetDistanceLimit", "" + setDistanceLimit);
                        Log.i("WDHlockDistanceTemp", "" + lockDistanceTemp);
                        Log.i("WDHlockDistance", "" + lockDistance);
                        if (!lock) {
                            lockDistanceTemp = totalDistanceValue;//没锁机暂存距离
                        } else {
                            lockDistance = totalDistanceValue - lockDistanceTemp;//锁机后计算距离

                            if (lockDistance >= setDistanceLimit && setDistanceLimit >= 1) {
                                mMachineData.setCurrentStatus(0);
                                try {
                                    if (null != mService) {
                                        mService.updateToMcu(mMachineData);
                                    }
                                } catch (RemoteException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                                updateToService();
                                locked = true;
//                                setPasswordFlieDate();//进入就写入文件，防止断电数据丢失，保证重启后跑步机不能用
//                                if (switchFragment != 13) {
//                                    switchFragment = 13;
//                                    switchFragment(switchFragment);
//                                }
                            }
                        }
                    }

                    if (data.getExecutionTime() / 3600 > 0) {
                        totalTimeValue = data.getExecutionTime() / 3600;
                    }
//				Log.i("totalTimeValue", ""+totalTimeValue);
//				Log.i("totalDistanceValue", ""+totalDistanceValue);
                    break;
                case UPDATE_UI_DATA:
                    break;
            }
        }
    };

    // 时间以指定格式显示
    private String formatIntToTime(int timeCount) {
        String hh = timeCount / 3600 > 9 ? timeCount / 3600 + "" : "0"
                + timeCount / 3600;
        String mm = (timeCount % 3600) / 60 > 9 ? (timeCount % 3600) / 60 + ""
                : "0" + (timeCount % 3600) / 60;
        String ss = (timeCount % 3600) % 60 > 9 ? (timeCount % 3600) % 60 + ""
                : "0" + (timeCount % 3600) % 60;
        return (hh + ":" + mm + ":" + ss);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        if (mMachineData == null){
            mMachineData = MachineData.getInstance();
        }


        //BaseActivity初始化
        InitBase();
        //BaseActivity监听
        BaselistionEvent();
        //初始化
        init();
        //监听
        listionEvent();

        initConnection();
        startService();
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        Log.i(" fragmentActivity onStart", "fragmentActivity 执行了onStart");
        if (data == null || mService == null) {
            initConnection();
            startService();
        }

//        inItUi();
//        resetBackup();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Log.i("fragmentActivity  onResume", "fragmentActivity执行了onResume");

//        findViewById();
//        inItUi();
        listionEvent();
        if (null == mService || null == data) {
            initConnection();
            startService();
        }
//        resetBackup();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        Log.i("fragmentActivity onDestroy()", "fragmentActivity 执行了onDestroy()");
//        unregisterReceiver(receiver);
        exitService();
//        programType = 0;
    }

    /*
     * 启动后台
     */
    private void startService() {
        // TODO Auto-generated method stub
        Intent intent = new Intent();
        //绑定服务端的service
        intent.setAction("com.ada.mcu.service.BIND_SERVICE");
        //新版本（5.0后）必须显式intent启动 绑定服务
        intent.setComponent(new ComponentName("com.ADA.mbh", "com.ada.mcu.service.BIND_SERVICE"));
        //绑定的时候服务端自动创建
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

    }

    /*
     * 退出服务
     */
    public void exitService() {
        // Log.i(TAG, "exitService " + (mService != null) + " " + (mConnection
        // != null));
        try {
            if (mService != null) {
                Log.i(TAG, "unregisterCallback");
                mService.unregisterCallback("com.ADA.mbh", mCallback);
            }
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (mConnection != null) {
            unbindService(mConnection);
            mConnection = null;
            Log.i(TAG, "unbindService");
        }
    }

    /*
     * 连接后台
	 */
    private void initConnection() {
        mCallback = new IServiceCallback.Stub() {

            @Override
            public void sendSettingData(SettingData data) throws RemoteException {

            }

            @Override
            public void responseToUi(ServiceRepliedData data) throws RemoteException {
                Message message = Message.obtain();
                message.obj = data;
                message.what = UPDATE_RECEIVED_DATA;
                uiUpdateHandler.sendMessage(message);
            }
        };

        mConnection = new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                // TODO Auto-generated method stub
                mService = IContactUi.Stub.asInterface(service);
                try {
					/*
					 * if(mService.isInited()) {
					 * mService.registerCallback(mCallback); } else {
					 * exitService(); }
					 */

                    Log.i(TAG, "onServiceConnected");
                    mService.registerCallback("com.ADA.mbh", mCallback);

                } catch (RemoteException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mService = null;
            }
        };
    }

    private void listionEvent() {
        // TODO Auto-generated method stub

        //参数设置
        IB_parameterSetting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (!FactoryPatternSet.isPasswordOpen) {
                    Intent intent = new Intent(SetActivity.this, ParameterSettingActivity.class);
                    startActivity(intent);
                    return;
                }
                final EditText inputPassword = new EditText(SetActivity.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(SetActivity.this);
                builder.setTitle(getString(R.string.str_please_input_password))
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setView(inputPassword)
                        .setNegativeButton(getString(R.string.str_cancel), null);
                builder.setPositiveButton(getString(R.string.str_confirm), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                        getPassword = inputPassword.getText().toString();//得到输入的数值

                        String password = getPasswordFileDate();//得到文件中的数据
                        Log.i("password", password);
                        if (getPassword.equals(password)) {
                            Log.i("password", getPassword);
                            getPassword = "";
                            Intent intent = new Intent(SetActivity.this, ParameterSettingActivity.class);
                            startActivity(intent);
                        } else {
                            getPassword = "";
                            AlertDialog.Builder builder = new AlertDialog.Builder(SetActivity.this);
                            builder.setTitle(getString(R.string.str_prompt));
                            builder.setMessage(getString(R.string.str_input_password_error));
                            builder.setPositiveButton(getString(R.string.str_confirm), null);
                            builder.show();
                        }
                    }
                });
                builder.show();
            }
        });

        //添加IB_Mode_Now点击事件,切换到现有模式
        IB_Mode_Now.setOnClickListener(new ImageButton.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(SetActivity.this, ModeNowActivity.class);
                startActivity(intent);
            }
        });

        //添加IB_Movie点击事件,切换到现有模式
        IB_Movie.setOnClickListener(new ImageButton.OnClickListener() {

            public void onClick(View v) {

                Uri uri = Uri.parse("http://www.iqiyi.com/dianying/");
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
//					Intent intent = new Intent(SetActivity.this,MovieActivity.class);
//					startActivity(intent);				
            }
        });

        //添加IB_Explorer点击事件,切换到现有模式
        IB_Explorer.setOnClickListener(new ImageButton.OnClickListener() {

            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.baidu.com");
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
            }
        });

        //添加IB_Music点击事件,切换到现有模式
        IB_Music.setOnClickListener(new ImageButton.OnClickListener() {

            public void onClick(View v) {
                Uri uri = Uri.parse("http://music.baidu.com/");
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
            }
        });

        //添加IB_Usb点击事件,切换到现有模式
        IB_Usb.setOnClickListener(new ImageButton.OnClickListener() {

            public void onClick(View v) {

                ComponentName Usb = new ComponentName("com.jrm.localmm", "com.jrm.localmm.ui.main.FileBrowserActivity");
                Intent intent = new Intent();
                intent.setComponent(Usb);
                startActivity(intent);
            }
        });

        //添加IB_Tv点击事件,切换到现有模式
        IB_Tv.setOnClickListener(new ImageButton.OnClickListener() {

            public void onClick(View v) {

                TvCommonManager.getInstance().setInputSource(1);
//                Intent newIntent = new Intent(
//                        "com.mstar.tv.tvplayer.ui.intent.action.SOURCE_CHANGE");
//                newIntent.putExtra("inputSrc", 1);
//                newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(newIntent);
//                try {
//                    Intent targetIntent;
//
//                    targetIntent = new Intent(
//                            "mstar.tvsetting.ui.intent.action.RootActivity");
//                    targetIntent.putExtra("task_tag", "input_source_changed");
//                    targetIntent.putExtra("no_change_source", true);
//                    targetIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
//                    if (targetIntent != null)
//                        startActivity(targetIntent);
//
//                    Intent sourceInfoIntent = new Intent("com.mstar.tv.tvplayer.ui.intent.action.SOURCE_INFO");
//                    sourceInfoIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(sourceInfoIntent);
//                    //if((index >= 2 || index <= 4) || index == 9)
//                    //	updateHandler.sendEmptyMessage(mWindowData.SHOW_POPUPWINDOW);
                try {
                    ComponentName componentName = null;
                    componentName = new ComponentName("com.mstar.tv.tvplayer.ui",
                            "com.mstar.tv.tvplayer.ui.RootActivity");
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.setComponent(componentName);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                            | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //添加IB_Av点击事件,切换到现有模式
        IB_Av.setOnClickListener(new ImageButton.OnClickListener() {

            public void onClick(View v) {

                TvCommonManager.getInstance().setInputSource(2);
//                Intent newIntent = new Intent(
//                        "com.mstar.tv.tvplayer.ui.intent.action.SOURCE_CHANGE");
//                newIntent.putExtra("inputSrc", 2);
//                newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(newIntent);
//                try {
//                    Intent targetIntent;
//
//                    targetIntent = new Intent(
//                            "mstar.tvsetting.ui.intent.action.RootActivity");
//                    targetIntent.putExtra("task_tag", "input_source_changed");
//                    targetIntent.putExtra("no_change_source", true);
//                    targetIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
//                    if (targetIntent != null)
//                        startActivity(targetIntent);
//
//                    Intent sourceInfoIntent = new Intent("com.mstar.tv.tvplayer.ui.intent.action.SOURCE_INFO");
//                    sourceInfoIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(sourceInfoIntent);
//                    //if((index >= 2 || index <= 4) || index == 9)
//                    //	updateHandler.sendEmptyMessage(mWindowData.SHOW_POPUPWINDOW);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                try {
                    ComponentName componentName = null;
                    componentName = new ComponentName("com.mstar.tv.tvplayer.ui",
                            "com.mstar.tv.tvplayer.ui.RootActivity");
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.setComponent(componentName);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                            | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void init() {

        IB_parameterSetting = (LinearLayout) findViewById(R.id.ib_set);
        //IB_SetPer = (ImageButton)findViewById(R.id.ib_set);
        IB_Explorer = (LinearLayout) findViewById(R.id.ib_explorer);
        IB_Mode_Now = (LinearLayout) findViewById(R.id.ib_mode_now);
        IB_Usb = (LinearLayout) findViewById(R.id.ib_usb);
        IB_Movie = (LinearLayout) findViewById(R.id.ib_movie);
        IB_Tv = (LinearLayout) findViewById(R.id.IB_Tv);
        IB_Music = (LinearLayout) findViewById(R.id.ib_music);
        IB_Av = (LinearLayout) findViewById(R.id.IB_Av);

        TV_Set = (TextView) findViewById(R.id.TV_Set);
        TV_Mode_Now = (TextView) findViewById(R.id.TV_Mode_Now);
        TV_Usb = (TextView) findViewById(R.id.TV_Usb);
        TV_Explorer = (TextView) findViewById(R.id.TV_Explorer);
        TV_Music = (TextView) findViewById(R.id.TV_Music);
        TV_Movie = (TextView) findViewById(R.id.TV_Movie);
        TV_Tv = (TextView) findViewById(R.id.TV_Tv);

        RL_Big_Layout.setVisibility(View.GONE);
        RL_Big_Layout_Right.setVisibility(View.GONE);
        RL_Middle_Layout.setVisibility(View.GONE);
    }

    /*
     * 公英制转换
     */
    public void metricAndEnglishSwitch(int progressSpeed) {

        if (defaultSing == 0) {

//            PB_Speed.metricAndEnglish = 0;
            TV_Speed.setText(R.string.Speed);
            TV_Distance.setText(R.string.Distance);
            Intent intent = new Intent();
            intent.setAction("defaultSing");
            intent.putExtra("defaultSing", defaultSing);
            sendBroadcast(intent);
            speedShow(progressSpeed);
        } else {

//            PB_Speed.metricAndEnglish = 1;
            TV_Speed.setText(R.string.SpeedEnglish);
            TV_Distance.setText(R.string.DistanceEnglish);
            speedShowByEnglish(progressSpeed);
        }
    }

    /*
     * 公制速度界面展示处理
     */
    public void speedShow(int progressSpeed) {

        this.progressSpeed = progressSpeed;// 按键板选择的时候可以喝界面调节加减的同步
        String temp = "" + (progressSpeed / 10) + "." + (progressSpeed % 10);
        TV_Speed_Show_Top.setText(temp);
        PB_Speed.setProgress(progressSpeed, circle_point_imgSpeed);
    }

    /*
     * 英制速度显示
     */
    @SuppressLint("UseValueOf")
    public void speedShowByEnglish(int progressSpeed) {

        this.progressSpeed = progressSpeed;// 按键板选择的时候可以喝界面调节加减的同步

        progressSpeedEnglish = progressSpeed * 6;
        String progressSpeedString = "" + progressSpeedEnglish / 100 + "." + ""
                + progressSpeedEnglish % 100;

        progressSpeedEnglishCircle = progressSpeed * 0.6;
        Double mDouble = new Double(progressSpeedEnglishCircle);
        progressSpeedEnglish = mDouble.intValue();
        TV_Speed_Show_Top.setText(progressSpeedString);
        PB_Speed.setProgress(progressSpeedEnglish, circle_point_imgSpeed);
    }

    /*
     * 坡度处理显示
     */
    public void gradientShow(int progressGradient) {

        // Log.i("this.progressGradient ", ""+this.progressGradient );
        // Log.i("progressGradient ", ""+progressGradient );

        this.progressGradiend = progressGradient;// 按键板选择的时候可以喝界面调节加减的同步
        TV_Gradiend_Show_Top.setText("" + progressGradient);
        PB_Gradiend.setProgress(progressGradient, circle_point_imgSpeed);
    }

    /*
     * 更新到服务器
     */
    private void updateToService() {

        updateToTextView();
        if (mService != null) {
            try {
                mService.updateToMcu(mMachineData);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * 更新到textView
     */
    private void updateToTextView() {

        switch (mMachineData.getCurrentStatus()) {
            case 0:
                resetAllText();
                mMachineData.setInitStandbyDatas();
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                resetAllText();
                mMachineData.setInitSelfCheckDatas();
                break;
        }
    }

    /*
     * 更新数据
     */
    private void resetAllText() {
        caloryCount = 0;
        milesCount = 0;
        timeCount = 0;
        mMachineData.setUserDistance(0);
        mMachineData.setUserCalorie(0);
        mMachineData.setHeartBeat(0);
    }
}
