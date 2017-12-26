package com.ada.jw;

import java.io.File;
import java.security.Principal;

import com.ada.ScreenBrightnessTool.ScreenBrightnessTool;
import com.ada.VerticalSeekBar.VerticalSeekBar;
import com.ada.button.KeypadControl;
import com.ada.mcu.service.Beep;
import com.ada.mcu.service.IContactUi;
import com.ada.mcu.service.IServiceCallback;
import com.ada.mcu.service.MachineData;
import com.ada.mcu.service.ProgramData;
import com.ada.mcu.service.ServiceRepliedData;
import com.ada.mcu.service.SettingData;
import com.ada.util.CircleProgressBar;
import com.ada.wifi.WifiAdmin;
import com.lwjwork.launcher.langlangui.activity.AllApp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

@SuppressWarnings("deprecation")
public class setActivity extends FragmentActivity {

    private ComponentName cn;
    private ActivityManager am;

    public ServiceRepliedData data;
    // 下位机的定义
    private static final String TAG = "setActivity";
    public IContactUi mService;
    private ServiceConnection mConnection;
    public static MachineData mMachineData = null;
    public static int[] repliedBackup = {0, 0, 0, 0, 0};
    private final int UPDATE_RECEIVED_DATA = 1000;
    private final int UPDATE_UI_DATA = 1001;
    private IServiceCallback mCallback;
    // 以上是下位机定义

    // 包名
    public String extendPackageName;

    // 定义一个数据库
    //static SharedPreferences sharedPreferences = null;

    public static final String PREFS_NAME = "MyPrefsFile";
    public static final String FIRST_RUN = "first";
    private boolean first;

    // 按键板,选程式
    public static boolean isOrNoAtNowModeProjectActivity = false;
    public static boolean isOrNoAtsetFragment = true;
    public static boolean isOrNoSetPerFragment = false;
    public static boolean isOrNoFactoryPatternSetFragment = false;
    // 个人消耗信息
    public static int startEndMark = 0;
    public TextView TV_consumptionOk;
    public TextView TV_consumptionTimeShow;
    public TextView TV_consumptionDistanceShow;
    public TextView TV_consumptionCalorieShow;
    public TextView TV_consumptionSpeedShow;

    // 年龄身高体重的值
    public static String Age_Text = "35";
    public static String Height_Text = "168";
    public static String Weight_Text = "68";
    // 时间距离热量
    public String Time_Text = "30";
    public String Hot_Text = "20";
    public String Distance_Text = "3";

    // 选择模式后传给后台
    public ProgramData mProgramData;
    // 公英制
    public static String TempFactoryPatternSet = "";// 区别是选择了那个,键盘好去显示到对应的上面

    // 密码模块
    // 密码
    public static int password = 333999;
    public static boolean isOrNoChangeParameter = false;// 如果改了工程参数设置里面的最大值最小值
    // 公英制标记 0:公制 1：英制
    public static int defaultSing = 0;
    public static int defaultMinSpeed = 1;
    public static int defaultMaxSpeed = 20;
    public static int defaultMaxLncline = 20;
    public static int defaultSpeedTime = 2;// 对后台无用的
    public static int defaultWhell = 5;
    public static int defaultAutoOilDistance = 800;
    public static int defaultoilTime = 20;
    public static int defaultAddOliNumber = 25;// 加油报警次数

    public static int setDistanceLimit = 0;
    public static int lockDistance = 0;
    public boolean lock = false;//设定锁机，开始计算距离
    public boolean locked = false;//当前状态是否是锁机状态
    public static int lockDistanceTemp = 0;
    public static int setNumberSectionSpeed = 20;
    public static int useOrNoPassword = 1;

    public static int totalTimeValue = 0;
    public static int totalDistanceValue = 0;

    // 上面�?行显示数据的
    public TextView TV_Speed_Show_Top;
    public TextView TV_Speed;
    public TextView TV_Heart_Show_Top;
    public TextView TV_Heat_Show_Top;
    public TextView TV_Distance_Show_Top;
    public TextView TV_Distance;
    public TextView TV_Time_Show_Top;
    public TextView TV_GradiendShow_Top;

    private int caloryCount;
    private int milesCount;
    private static int timeCount;

    public Handler stepTimeHandler;
    public Runnable mTicker;
    long startTime = 0;

    // 鐎规矮绠熼獮鎸庢尡閹恒儲鏁归崳锟�
    // public SdcardStateChanageReceiver sdcardStateReceiver;

    public String SdState;
    public String Sdpath;

    // SlidingDrawer模块
    public SlidingDrawer slidingDrawerSet;
    public ImageButton handle;

    // 亮度
    public VerticalSeekBar SeekBar_screenBrightness;
    private boolean sysAutomaticMode = false;
    // 声音
    public VerticalSeekBar SeekBar_Voice;
    private AudioManager audioManager; // 系统声音管理类
    private int maxVolume, currentVolume;
    private ToggleButton TB_toggleButtonMute;

    // wifi 下载
    public SeekBar SbWifi;
    public ImageView WiFi_ON;
    public ImageView WiFi_OFF;
    public SeekBar SB_Load;
    public ImageView Load_ON;
    public ImageView Load_OFF;
    // public wifiStateBroadcast mwifiStateBroadcast;
    public String wifiState;
    public ImageView mNetIcon;

    // public ImageView WiFi_offState;
    // public wifiStateBroadcast mwifiStateBroadcast;

    // 瀹搞儱宸剁拋鍓х枂
    public Button Btn_SlidingSetting;
    public String getPassword = "";
    public Button Btn_SlidingMenu;

    public View LL_BackAndPause;
    public View RL_consumptionInformation;

    // 閺堬拷娑撳娼伴敍灞界毈閹稿鎸�
    public ImageButton IB_downSmall;

    // 4个按钮
    // 是否是程式状态，是的话四个按钮无效
    public boolean isORNoPatternState = false;
    public ImageButton IB_leftOnly;
    public ImageButton IB_leftBoth;
    public ImageButton IB_rightOnly;
    public ImageButton IB_rightBoth;

    // 返回按钮
    public Button Back;
    private Button pause;

    /*
     * 类型标记 初始值0：点击4个按钮进度条不变化；点击 值为2：调节坡度点击IV_SpeedSliding 值为1：调节速度
     */
    public static int TypeMark = 1;
    // 坡度图片
    public ImageView circle_point_imgGradient;
    // 坡度圆形进度�?
    public CircleProgressBar PB_Gradient;
    // 閸р�冲閹稿鎸抽敍宀�鍋ｉ崙璇叉倵閸欘垱鏁奸崣妯烘蒋鎼达箑娓捐ぐ銏ｇ箻鎼达附娼惃鍕拷锟�
    public View RL_circle_point_imgGradient;
    public ImageView IV_GradientSliding;
    public static int progressGradient = 0;
    public TextView ProgressBarGradient;

    // 闁喎瀹抽崶鍓у
    public ImageView circle_point_imgSpeed;
    // 闁喎瀹抽崷鍡楄埌鏉╂稑瀹抽弶锟�
    public CircleProgressBar PB_Speed;
    // 闁喎瀹抽幐澶愭尦閿涘瞼鍋ｉ崙璇叉倵閸欘垱鏁奸崣妯跨箻鎼达箑娓捐ぐ銏ｇ箻鎼达附娼惃鍕拷锟�
    public ImageView IV_SpeedSliding;
    public static int progressSpeed = 10;
    public static int progressSpeedEnglish = 0;// 公英制转换用
    public static Double progressSpeedEnglishCircle = 0.0;// 公英制转换用
    public TextView CircleProgressBarSpeed;

    public int isWarningOpened = 0;
    public static int numCode = 0;

    public KeypadControl mKeypadControl;
    protected OnKeypadDownReceiver receiver;

    public static int switchFragment = 0;
    private FragmentTransaction fragmentTransaction;
    private mainFragment mMainFragment;
    private setFragment mSetFragment;
    private setPerFragment mSetPerFragment;
    private nowModeProjectFragment mNowModeProjectFragment;
    public Boolean isOrNoFirstInProjectFragment = true;
    private nowModeGoalFragment mNowModeGoalFragment;
    public Boolean isOrNoFirstInModeFragment = true;
    private nowModeHeartFragment mNowModeHeartFragment;
    private taskFragment mTaskFragment;
    private countDownFragment mCountDownFragment;
    private warningFragment mWarningFragment;
    private ParameterSettingFragment mParameterSettingFragment;
    private FactoryPatternSetFragment mFactoryPatternSetFragment;
    private explorerFragment mExplorerFragment;
    private movieFragment mMovieFragment;
    private lockFragment mLockFragment;

    private Dialog dialog;

    public int programType = 0;
    public static final int HANDMOVE = 1;
    public static final int FLUCTUATE = 2;
    public static final int CLIMBING = 3;
    public static final int INTERVAL = 4;
    public static final int LOSTWEIGHT = 5;
    public static final int MOUNTION = 6;
    public static final int BURN = 7;

    public static final int HEART65 = 8;
    public static final int HEART75 = 9;
    public static final int HEART85 = 10;

    public static final int HOT = 11;
    public static final int DISTANCE = 12;
    public static final int TIME = 13;
    public static boolean isOrNoProgram = false;
    public boolean isOrNoClickStop = false;

    public View RL_commenTop;
    public int countDown = 0;// 倒计时区分进入setj界面还是task界面
    protected boolean isOrNoCountdown = true;

    // 定义按键板
    public enum KEYPAD_KEYCODE {
        KEYCODE_PBJ_SP_ADD, KEYCODE_PBJ_SP_DEC, KEYCODE_PBJ_INC_ADD, KEYCODE_PBJ_INC_DEC, KEYCODE_PBJ_START, KEYCODE_PBJ_STOP, KEYCODE_PBJ_ENTER, KEYCODE_PBJ_RESET, KEYCODE_PBJ_PROGRAM, KEYCODE_PBJ_PROGRAM_ADD, KEYCODE_PBJ_PROGRAM_DEC, KEYCODE_PBJ_FAN, KEYCODE_PBJ_MODE, KEYCODE_PBJ_ADJUST, KEYCODE_PBJ_FACTORY, KEYCODE_PBJ_SEARCH, KEYCODE_PBJ_SCREEN, KEYCODE_PBJ_START_STOP, KEYCODE_PBJ_SET_ADD, KEYCODE_PBJ_SET_DEC, KEYCODE_PBJ_SPEED_4, KEYCODE_PBJ_SPEED_8, KEYCODE_PBJ_SPEED_12, KEYCODE_PBJ_SPEED_16, KEYCODE_PBJ_SPEED_3, KEYCODE_PBJ_SPEED_6, KEYCODE_PBJ_SPEED_9, KEYCODE_PBJ_SPEED_15, KEYCODE_PBJ_SPEED_2, KEYCODE_PBJ_SPEED_10, KEYCODE_PBJ_INCLINE_2, KEYCODE_PBJ_INCLINE_5, KEYCODE_PBJ_INCLINE_8, KEYCODE_PBJ_INCLINE_12, KEYCODE_PBJ_INCLINE_3, KEYCODE_PBJ_INCLINE_6, KEYCODE_PBJ_INCLINE_9, KEYCODE_PBJ_INCLINE_15, KEYCODE_PBJ_INCLINE_4, KEYCODE_PBJ_INCLINE_10, KEYCODE_PBJ_SPEED_OK, KEYCODE_PBJ_INCLINE_OK, KEYCODE_PBJ_INCLINE_16
    }

    /*
     * 开线程更所有UI
     */
    private Handler dataUpdateHandler = new Handler() {

        public void handleMessage(Message msg) {
            Log.i("KEYPAD_KEYCODE.values()[msg.what - 5000]", ""
                    + KEYPAD_KEYCODE.values()[msg.what - 5000]);
            switch (KEYPAD_KEYCODE.values()[msg.what - 5000]) {

                case KEYCODE_PBJ_SP_ADD: {
                    break;
                }
                case KEYCODE_PBJ_SP_DEC: {
                    break;
                }
                case KEYCODE_PBJ_INC_ADD: {
                    break;
                }
                case KEYCODE_PBJ_INC_DEC: {
                    break;
                }
                case KEYCODE_PBJ_START:
                    Log.i("点击了开始键", "" + mMachineData.getCurrentStatus());
                    Log.i("isOrNoFactoryPatternSetFragment", "" + isOrNoFactoryPatternSetFragment);
                    Log.i("data.getAlarmCode()", "" + data.getAlarmCode());

                    if (data.getAlarmCode() == 0 && isOrNoFactoryPatternSetFragment == false) {
                        if (mMachineData.getCurrentStatus() == 2) {
                            pause.setText(R.string.Pause);
                            programType = 0;
                        } else if (mMachineData.getCurrentStatus() == 0) {
                            Log.i("点击了开始键", "" + mMachineData.getCurrentStatus());
                            resetBackup();
                            pause.setText(R.string.Pause);
                            if (isJwActivityTop() && locked == false) {
                                if (null != dialog) {
                                    switchFragment = 0;
                                    dialog.dismiss();
                                    dialog = null;
                                    switchFragment(switchFragment);
                                } else {
                                    if (!locked) {
                                        mMachineData.setCurrentStatus(1);
                                        countDown = 6;
                                        setActivity.switchFragment = 7;
                                        switchFragment(switchFragment);
                                        if (mService != null) {
                                            try {
                                                mService.updateToMcu(mMachineData);
                                            } catch (RemoteException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                }
                            }
                            programType = 0;
                        } else if (mMachineData.getCurrentStatus() == 1) {
//									if (isOrNoCountdown ) {
//										isOrNoCountdown = false;
//											countDown = 6;
//											setActivity.switchFragment = 7;
//											switchFragment(switchFragment);
//									}
                        }
                    }
                    break;
                case KEYCODE_PBJ_STOP:
                    break;
                case KEYCODE_PBJ_RESET:
                    back();
                    break;
                case KEYCODE_PBJ_PROGRAM:
                    if (data.getAlarmCode() == 0) {
                        if (mMachineData.currentStatus == 1) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(
                                    setActivity.this);
                            builder.setTitle(R.string.prompt);
                            builder.setMessage("现在是运行状态，禁止切换模式！！");
                            builder.setPositiveButton(R.string.Confirm, null);
                            builder.show();
                        } else if (mMachineData.currentStatus == 3) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(
                                    setActivity.this);
                            builder.setTitle(R.string.prompt);
                            builder.setMessage("现在是自检状态，禁止切换模式！！");
                            builder.setPositiveButton(R.string.Confirm, null);
                            builder.show();
                        } else {
                            switchFragment = 2;
                            switchFragment(switchFragment);
                            mNowModeProjectFragment.pressProgramkey();
                        }
                    }
                    break;
                case KEYCODE_PBJ_ENTER:
                    if (mMachineData.currentStatus == 0 && programType != 0) {
                        Log.i("KEYCODE_PBJ_MODE", "" + programType);
                        if (isOrNoSetPerFragment) {
                            if (mMachineData.getCurrentStatus() != 1) {
                                mSetPerFragment.entryMode();
                            }
                        } else {
                            switchFragment = 1;
                            switchFragment(switchFragment);
                        }
                    }
                    break;
                case KEYCODE_PBJ_PROGRAM_ADD:
                    break;
                case KEYCODE_PBJ_PROGRAM_DEC:
                    break;
                case KEYCODE_PBJ_FAN:
                    break;
                case KEYCODE_PBJ_MODE:
                    if (data.getAlarmCode() == 0) {
                        if (mMachineData.currentStatus == 1) {

                            AlertDialog.Builder builder = new AlertDialog.Builder(
                                    setActivity.this);
                            builder.setTitle(R.string.prompt);
                            builder.setMessage("现在是运行状态，禁止切换模式！！");
                            builder.setPositiveButton(R.string.Confirm, null);
                            builder.show();
                        } else if (mMachineData.currentStatus == 3) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(
                                    setActivity.this);
                            builder.setTitle(R.string.prompt);
                            builder.setMessage("现在是自检状态，禁止切换模式！！");
                            builder.setPositiveButton(R.string.Confirm, null);
                            builder.show();

                        } else {
                            switchFragment = 3;
                            switchFragment(switchFragment);
                            mNowModeGoalFragment.pressModeKey();
                        }
                    }
                    break;
                case KEYCODE_PBJ_ADJUST:
                    break;
                case KEYCODE_PBJ_FACTORY:
                    break;
                case KEYCODE_PBJ_SEARCH:
                    break;
                case KEYCODE_PBJ_SCREEN:
                    break;
                case KEYCODE_PBJ_START_STOP:
                    break;
                case KEYCODE_PBJ_SET_ADD:
                    break;
                case KEYCODE_PBJ_SET_DEC:
                    break;
                case KEYCODE_PBJ_SPEED_4:
                    mMachineData.setOperatingFrequency(40);
                    metricAndEnglishSwitch(40);
                    break;
                case KEYCODE_PBJ_SPEED_8:
                    mMachineData.setOperatingFrequency(80);
                    metricAndEnglishSwitch(80);
                    break;
                case KEYCODE_PBJ_SPEED_12:
                    mMachineData.setOperatingFrequency(120);
                    metricAndEnglishSwitch(120);
                    break;
                case KEYCODE_PBJ_SPEED_16:
                    mMachineData.setOperatingFrequency(160);
                    metricAndEnglishSwitch(160);
                    break;
                case KEYCODE_PBJ_SPEED_3:
                    mMachineData.setOperatingFrequency(30);
                    metricAndEnglishSwitch(30);
                    break;
                case KEYCODE_PBJ_SPEED_6:
                    mMachineData.setOperatingFrequency(60);
                    metricAndEnglishSwitch(60);
                    break;
                case KEYCODE_PBJ_SPEED_9:
                    mMachineData.setOperatingFrequency(90);
                    metricAndEnglishSwitch(90);
                    break;
                case KEYCODE_PBJ_SPEED_15:
                    mMachineData.setOperatingFrequency(150);
                    metricAndEnglishSwitch(150);
                    break;
                case KEYCODE_PBJ_SPEED_2:
                    mMachineData.setOperatingFrequency(20);
                    metricAndEnglishSwitch(20);
                    break;
                case KEYCODE_PBJ_SPEED_10:
                    mMachineData.setOperatingFrequency(100);
                    metricAndEnglishSwitch(100);
                    break;
                case KEYCODE_PBJ_INCLINE_2:
                    mMachineData.setIncline(2);
                    gradientShow(2);
                    break;
                case KEYCODE_PBJ_INCLINE_5:
                    mMachineData.setIncline(5);
                    gradientShow(5);
                    break;
                case KEYCODE_PBJ_INCLINE_8:
                    mMachineData.setIncline(8);
                    gradientShow(8);
                    break;
                case KEYCODE_PBJ_INCLINE_12:
                    mMachineData.setIncline(12);
                    gradientShow(12);
                    break;
                case KEYCODE_PBJ_INCLINE_3:
                    mMachineData.setIncline(3);
                    gradientShow(3);
                    break;
                case KEYCODE_PBJ_INCLINE_6:
                    mMachineData.setIncline(6);
                    gradientShow(6);
                    break;
                case KEYCODE_PBJ_INCLINE_9:
                    mMachineData.setIncline(9);
                    gradientShow(9);
                    break;
                case KEYCODE_PBJ_INCLINE_15:
                    mMachineData.setIncline(15);
                    gradientShow(15);
                    break;
                case KEYCODE_PBJ_INCLINE_4:
                    mMachineData.setIncline(4);
                    gradientShow(4);
                    break;
                case KEYCODE_PBJ_INCLINE_10:
                    mMachineData.setIncline(10);
                    gradientShow(10);
                    break;
                case KEYCODE_PBJ_SPEED_OK:
                    break;
                case KEYCODE_PBJ_INCLINE_OK:
                    break;
                case KEYCODE_PBJ_INCLINE_16:
                    mMachineData.setIncline(16);
                    break;
                default:
                    break;
            }
        }

        ;
    };

    private Handler uiUpdateHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            data = (ServiceRepliedData) msg.obj;

            // Log.i(TAG, "LYJ, mMachineData.getCurrentStatus():" +
            //	 mMachineData.getCurrentStatus());
//			 Log.i("data.getAlarmCode()" , " "+data.getAlarmCode());
//			 Log.i("isWarningOpened" ," "+isWarningOpened);
            if (locked && switchFragment != 13) {
                switchFragment = 13;
                switchFragment(switchFragment);
            }

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
                        pressStopShowConsumeDialogue();
                    }
                }
            } else if (currentStatus == 3) {
                if (data.getSelfCheckStatus() == 1) {
                    warning();
                } else if (locked == false) {
                    switchFragment = 0;
                    switchFragment(switchFragment);
                }
            }
            // Log.i("data.getAlarmCode()", ""+data.getAlarmCode());
            if ((data.getAlarmCode() != 0) && (isWarningOpened == 0)) {
                isWarningOpened = 1;
                numCode = data.getAlarmCode();
                //Log.i("data.getAlarmCode()", "" + data.getAlarmCode());

                // 警告
                warning();
            }
            //	Log.i("data.getSelfCheckStatus()", ""+data.getSelfCheckStatus());

            switch (msg.what) {
                case UPDATE_RECEIVED_DATA:

                    TV_Heart_Show_Top.setText("" + (data.getHeartBeat() & 0xff));
                    TV_Time_Show_Top.setText(formatIntToTime(data.getTimeCount()));
                    // Log.i("repliedBackup[0]", ""+repliedBackup[0]);
                    // Log.i("repliedBackup[1]", ""+repliedBackup[1]);
                    // Log.i("repliedBackup[2]", ""+repliedBackup[2]);
                    // Log.i("repliedBackup[3]", ""+repliedBackup[3]);
                    // Log.i("时间显示", formatIntToTime(data.getTimeCount()));
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
                                setPasswordFlieDate();//进入就写入文件，防止断电数据丢失，保证重启后跑步机不能用
                                if (switchFragment != 13) {
                                    switchFragment = 13;
                                    switchFragment(switchFragment);
                                }
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stubs
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commen_top);

        Log.i("fragmentActivity onCreate", "fragmentActivity执行了onCreate");
        if (mMachineData == null)
            mMachineData = MachineData.getInstance();
        AppContext context = (AppContext) getApplicationContext();
        context.setThisInstance(setActivity.this);
        Log.i("onCreate switch", "" + switchFragment);
        am = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);

        findViewById();
        listionEvent();
        initConnection();
        initReceiver();
        startService();
        switchFragment(switchFragment);
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

        inItUi();
        resetBackup();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();

        Log.i("fragmentActivity onStop", "fragmentActivity执行了onStop");
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        if (first) {
            editor.putBoolean(FIRST_RUN, false);
        }
        // Commit the edits!
        editor.commit();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // TODO Auto-generated method stub
        super.onNewIntent(intent);
        setIntent(intent);
        Bundle bundle = this.getIntent().getExtras();
        Log.i("bundle", " " + bundle);
        if (null != bundle) {
            repliedBackup[0] = getIntent().getExtras().getInt("timeCount");
            repliedBackup[1] = (int) getIntent().getExtras().getLong("miles");
            repliedBackup[2] = getIntent().getExtras().getInt("calories");
            repliedBackup[3] = getIntent().getExtras().getInt("speed");
            Log.i("repliedBackup[0]", " " + repliedBackup[0]);
            Log.i("repliedBackup[1]", " " + repliedBackup[1]);
            Log.i("repliedBackup[2]", " " + repliedBackup[2]);
            Log.i("repliedBackup[3]", " " + repliedBackup[3]);
            pressStopShowConsumeDialogue();
            return;
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Log.i("fragmentActivity  onResume", "fragmentActivity执行了onResume");

        findViewById();
        inItUi();
        listionEvent();
        if (null == mService || null == data) {
            initConnection();
            startService();
        }
        resetBackup();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        Log.i("fragmentActivity onDestroy()", "fragmentActivity 执行了onDestroy()");
        unregisterReceiver(receiver);
        exitService();
        programType = 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub

        return super.dispatchTouchEvent(ev);
    }

    /*
     * 判断当前运行的应用是不是嘉沃
     * */
    private boolean isJwActivityTop() {
        cn = am.getRunningTasks(1).get(0).topActivity;
        String packageName = cn.getPackageName();
//        Log.i(TAG, "checkTopActivity Get package name:" + packageName);
        if (packageName.equals("com.ada.jw") || packageName.equals("com.example.jw"))
            return true;
        else return false;
    }

    /*
     * 报警
     */
    public void warning() {

        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
        switchFragment = 8;
        switchFragment(switchFragment);
    }

    /*
     * 注册广播
     */
    private void initReceiver() {
        receiver = new OnKeypadDownReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.ada.keydown.service");
        registerReceiver(receiver, filter);
        // Log.i("registerReceiver", ""+111);
    }

    /*
     * 回报数据清零
     */
    private void resetBackup() {
        for (int i = 0; i < repliedBackup.length; i++) {
            repliedBackup[i] = 0;
        }
    }

    /*
     * 连接后台
     */
    private void initConnection() {
        mCallback = new IServiceCallback.Stub() {

            @Override
            public void responseToUi(ServiceRepliedData data)
                    throws RemoteException {
                Message message = Message.obtain();
                message.obj = data;
                message.what = UPDATE_RECEIVED_DATA;
                uiUpdateHandler.sendMessage(message);
            }

            @Override
            public void sendSettingData(SettingData data)
                    throws RemoteException {
                // TODO Auto-generated method stub

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
                    mService.registerCallback("com.example.jw", mCallback);

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

    /*
     * 启动后台
     */
    private void startService() {
        // TODO Auto-generated method stub
        Intent intent = new Intent();
        //绑定服务端的service
        intent.setAction("com.ada.mcu.service.BIND_SERVICE");
        //新版本（5.0后）必须显式intent启动 绑定服务
        intent.setComponent(new ComponentName("com.ada.jw", "com.ada.mcu.service.BIND_SERVICE"));
        //绑定的时候服务端自动创建
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

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
            case 0: // 寰呮満
                resetAllText();
                mMachineData.setInitStandbyDatas();
                break;
            case 1: // 杩愯
                break;
            case 2: // 鏆傚仠
                break;
            case 3: // 鑷
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

    /*
     * 初始化界面
     */
    private void inItUi() {

        // new按键板对像
        mKeypadControl = new KeypadControl();
        // 得到MachineData对象
        mProgramData = ProgramData.getInstance();

        PB_Gradient.setMaxProgress(mMachineData.getInclineMax());
        PB_Gradient.setFloatPointEnable(false);
        PB_Gradient.setProgress(mMachineData.getIncline(),
                circle_point_imgGradient);

        PB_Speed.setFloatPointEnable(true);
        PB_Speed.setMaxProgress(mMachineData.getSpeedMax());
        PB_Speed.setProgress(mMachineData.getOperatingFrequency(),
                circle_point_imgSpeed);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC); // 获取系统最大音量
        SeekBar_Voice.setMax(maxVolume); // 拖动条最高值与系统最大声匹配
        currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC); // 获取当前值
        SeekBar_Voice.setProgress(currentVolume);

        if (TypeMark == 2) {
            CircleProgressBarSpeed.setTextColor(Color.parseColor("#FFFFFF"));
            ProgressBarGradient.setTextColor(Color.parseColor("#FFCC00"));
        } else {
            CircleProgressBarSpeed.setTextColor(Color.parseColor("#FFCC00"));
            ProgressBarGradient.setTextColor(Color.parseColor("#FFFFFF"));
        }

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        first = settings.getBoolean(FIRST_RUN, true);
        if (first) {
//                 Toast.makeText(this, "The Application is first run",
//                                 Toast.LENGTH_LONG).show();
            setPasswordFlieDate();
        } else {
            getPasswordFileDate();
//                 Toast.makeText(this, "The Application is not first run",
//                                 Toast.LENGTH_LONG).show();
        }
    }

    /*
     * 閸掓繂顫愰崠锟�
     */
    public void findViewById() {
        // TODO Auto-generated method stub

        RL_commenTop = (RelativeLayout) findViewById(R.id.RL_commenTop);

        slidingDrawerSet = (SlidingDrawer) findViewById(R.id.slidingDrawerSet);
        handle = (ImageButton) findViewById(R.id.handle);

        // 婢逛即鐓堕敍灞煎瘨鎼达拷
        SeekBar_Voice = (VerticalSeekBar) findViewById(R.id.SeekBar_Voice);
        TB_toggleButtonMute = (ToggleButton) findViewById(R.id.TB_toggleButtonMute);
        SeekBar_screenBrightness = (VerticalSeekBar) findViewById(R.id.SeekBar_screenBrightness);

        SbWifi = (SeekBar) findViewById(R.id.ldm_bottom_btn2_ssb);
        WiFi_ON = (ImageView) findViewById(R.id.WiFi_ON);
        WiFi_OFF = (ImageView) findViewById(R.id.WiFi_OFF);
        SB_Load = (SeekBar) findViewById(R.id.SB_Load);
        Load_ON = (ImageView) findViewById(R.id.Load_ON);
        Load_OFF = (ImageView) findViewById(R.id.Load_OFF);
        // WiFi_offState = (ImageView)findViewById(R.id.WiFi_offState);

        Btn_SlidingSetting = (Button) findViewById(R.id.Btn_SlidingSetting);
        Btn_SlidingMenu = (Button) findViewById(R.id.Btn_SlidingMenu);

        LL_BackAndPause = (LinearLayout) findViewById(R.id.LL_BackAndPause);
        IB_downSmall = (ImageButton) findViewById(R.id.IB_downSmall);
        RL_consumptionInformation = (RelativeLayout) findViewById(R.id.RL_consumptionInformation);

        // 左右加减对调，左减右加
        IB_leftOnly = (ImageButton) findViewById(R.id.IB_rightOnly);
        IB_leftBoth = (ImageButton) findViewById(R.id.IB_rightBoth);
        IB_rightOnly = (ImageButton) findViewById(R.id.IB_leftOnly);
        IB_rightBoth = (ImageButton) findViewById(R.id.IB_leftBoth);

        Back = (Button) findViewById(R.id.Back);
        pause = (Button) findViewById(R.id.Pause);
        TV_GradiendShow_Top = (TextView) findViewById(R.id.TV_GradiendShow_Top);

        // 閸р�冲
        PB_Gradient = (CircleProgressBar) findViewById(R.id.PB_Gradient);
        IV_GradientSliding = (ImageView) findViewById(R.id.IV_GradientSliding);
        this.circle_point_imgGradient = (ImageView) findViewById(R.id.circle_point_imgGradient);
        ProgressBarGradient = (TextView) findViewById(R.id.ProgressBarGradient);

        // 闁喎瀹�
        PB_Speed = (CircleProgressBar) findViewById(R.id.PB_Speed);
        IV_SpeedSliding = (ImageView) findViewById(R.id.IV_SpeedSliding);
        this.circle_point_imgSpeed = (ImageView) findViewById(R.id.circle_point_imgSpeed);
        CircleProgressBarSpeed = (TextView) findViewById(R.id.CircleProgressBarSpeed);

        // 閺堬拷娑撳﹪娼�1鐞涘苯鐫嶇粈鐑樻殶閹癸拷
        TV_Speed_Show_Top = (TextView) findViewById(R.id.TV_Speed_Show_Top);
        TV_Speed = (TextView) findViewById(R.id.TV_Speed);
        TV_Heart_Show_Top = (TextView) findViewById(R.id.TV_Heart_Show_Top);
        TV_Heat_Show_Top = (TextView) findViewById(R.id.TV_Heat_Show_Top);
        TV_Distance_Show_Top = (TextView) findViewById(R.id.TV_Distance_Show_Top);
        TV_Distance = (TextView) findViewById(R.id.TV_Distance);
        TV_Time_Show_Top = (TextView) findViewById(R.id.TV_Time_Show_Top);
        TV_GradiendShow_Top = (TextView) findViewById(R.id.TV_GradiendShow_Top);
    }

    @SuppressWarnings("deprecation")
    protected void listionEvent() {
        // TODO Auto-generated method stub

        SeekBar_screenBrightness
                .setOnSeekBarChangeListener(new VerticalSeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onStopTrackingTouch(VerticalSeekBar seekBar) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onStartTrackingTouch(VerticalSeekBar seekBar) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onProgressChanged(VerticalSeekBar seekBar,
                                                  int progress, boolean fromUser) {
                        // TODO Auto-generated method stub

                        // ScreenBrightnessTool screenBrightnessTool = new
                        // ScreenBrightnessTool(setActivity.this, 0,
                        // sysAutomaticMode);

                        // Log.i("progress", "" + progress);
                        float progressFloat = (float) progress / 100;
                        //
                        //
                        // Log.i("progressFloat", "" + progressFloat);

                        ScreenBrightnessTool.brightnessPreviewFromPercent(
                                setActivity.this, progressFloat);
                        // ScreenBrightnessTool screenBrightnessTool = new
                        // ScreenBrightnessTool(setActivity.this, 0, fromUser);
                        // screenBrightnessTool.setBrightness(progress);
                    }
                });

        // 音量控制
        SeekBar_Voice
                .setOnSeekBarChangeListener(new VerticalSeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onStopTrackingTouch(
                            VerticalSeekBar VerticalSeekBar) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onStartTrackingTouch(
                            VerticalSeekBar VerticalSeekBar) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onProgressChanged(
                            VerticalSeekBar VerticalSeekBar, int progress,
                            boolean fromUser) {
                        // TODO Auto-generated method stub

                        // audioManager.setStreamMute(AudioManager.STREAM_MUSIC,
                        // false);
                        // TB_toggleButtonMute.setBackgroundResource(R.drawable.voice_on);
                        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                                progress, 0);
                        currentVolume = audioManager
                                .getStreamVolume(AudioManager.STREAM_MUSIC); // 获取当前值
                        SeekBar_Voice.setProgress(currentVolume);
                    }
                });

        TB_toggleButtonMute
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        // TODO Auto-generated method stub
                        Beep.beep(getApplicationContext());

                        if (isChecked) {
                            TB_toggleButtonMute
                                    .setBackgroundResource(R.drawable.voice_on);
                        } else {
                            TB_toggleButtonMute
                                    .setBackgroundResource(R.drawable.voice_off);
                        }
                        audioManager.setStreamMute(AudioManager.STREAM_MUSIC,
                                !isChecked);
                    }
                });

        // wifi閸旂喕鍏橀崚鍥ㄥ床
        SbWifi.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Beep.beep(getApplicationContext());
                WifiAdmin wifiAdmin = new WifiAdmin(setActivity.this);
                if (seekBar.getProgress() > 50) {
                    seekBar.setProgress(100);
                    WiFi_OFF.setVisibility(View.GONE);
                    WiFi_ON.setVisibility(View.VISIBLE);
                    wifiAdmin.openWifi();
                    // wifiAdmin.getWifiList();
                    //
                    // Log.i("ssid", wifiAdmin.getWifiList().get(1).SSID);
                    // wifiState = wifiAdmin.getBSSID();
                    // while(!wifiState.equals("true")){
                    // WiFi_offState.setVisibility(View.GONE);
                    // }

                } else {
                    seekBar.setProgress(0);
                    wifiAdmin.closeWifi();
                    WiFi_OFF.setVisibility(View.VISIBLE);
                    WiFi_ON.setVisibility(View.GONE);
                }
            }

            // 瀵拷婵袝閹芥瓕鎷烽煪顏呮閺傝纭�
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            // 閹锋牕濮╅弶鈩冩暭閸欐ɑ妞傞惃鍕煙濞夛拷
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub

            }
        });

		/*
		 * 娑撳娴囬惃鍕瀼閹广垺瀵滈柦锟�
		 */
        SB_Load.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

                Beep.beep(getApplicationContext());
                if (seekBar.getProgress() > 50) {
                    seekBar.setProgress(100);
                    Load_OFF.setVisibility(View.GONE);
                    Load_ON.setVisibility(View.VISIBLE);
                } else {

                    seekBar.setProgress(0);
                    Load_OFF.setVisibility(View.VISIBLE);
                    Load_ON.setVisibility(View.GONE);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub

            }
        });

        // 閹貉冨煑閺堬拷娑撳娼伴惃鍕箲閸ョ儑绱濋弳鍌氫粻鐢啫鐪惃鍕▔缁�锟�
        IB_downSmall.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Beep.beep(getApplicationContext());
                LL_BackAndPause.setVisibility(View.VISIBLE);
            }
        });

        // 鏉╂柨娲栭幐澶愭尦
        Back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                back();
            }
        });

        pause.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Beep.beep(getApplicationContext());
                switch (mMachineData.currentStatus) {
                    case 0:
                        break;
                    case 1:
                        mMachineData.setCurrentStatus(2);
                        pause.setText(R.string.Continue);
                        break;
                    case 2:
                        mMachineData.setCurrentStatus(1);
                        pause.setText(R.string.Pause);

                        break;
                    default:
                        break;
                }

                try {
                    if (null != mService) {
                        mService.updateToMcu(mMachineData);
                    }
                } catch (RemoteException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                updateToService();
            }
        });

        // 坡度标记
        IV_GradientSliding.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Beep.beep(getApplicationContext());
                TypeMark = 2;
                CircleProgressBarSpeed.setTextColor(Color.parseColor("#FFFFFF"));
                ProgressBarGradient.setTextColor(Color.parseColor("#FFCC00"));
            }
        });

        // 速度标记
        IV_SpeedSliding.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Beep.beep(getApplicationContext());
                TypeMark = 1;
                CircleProgressBarSpeed.setTextColor(Color.parseColor("#FFCC00"));
                ProgressBarGradient.setTextColor(Color.parseColor("#FFFFFF"));
            }
        });

        // 右边+1
        IB_leftOnly.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Beep.beep(getApplicationContext());
                // Log.i("TypeMark", ""+TypeMark);
                // TODO Auto-generated method stub
                Log.i("IB_leftOnly programType", "" + programType);
                if (programType >= HANDMOVE && programType <= BURN) {

                } else {
                    if (TypeMark == 1) {
                        progressSpeed += 1;
                        progressSpeed = (progressSpeed < defaultMaxSpeed * 10) ? progressSpeed
                                : defaultMaxSpeed * 10;
                        metricAndEnglishSwitch(progressSpeed);
                    } else if (TypeMark == 2) {
                        // Log.i("progressGradient", ""+progressGradient);
                        progressGradient += 1;
                        progressGradient = (progressGradient < defaultMaxLncline) ? progressGradient
                                : defaultMaxLncline;
                        gradientShow(progressGradient);
                        mMachineData.setIncline(progressGradient);
                    }
                    // Log.i("LYJ", "progressGradient:" + progressGradient
                    // + " getIncline():" + mMachineData.getIncline());
                    // Log.i("progressGradient", ""+progressGradient);
                    mMachineData.setOperatingFrequency(progressSpeed);
                    updateToService();
                }
            }
        });

        // 右减1
        IB_rightOnly.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Beep.beep(getApplicationContext());
                Log.i("IB_rightOnly programType", "" + programType);
                if (programType >= HANDMOVE && programType <= BURN) {

                } else {
                    if (TypeMark == 1) {

                        progressSpeed -= 1;
                        progressSpeed = (progressSpeed > defaultMinSpeed * 10) ? progressSpeed
                                : defaultMinSpeed * 10;

                        metricAndEnglishSwitch(progressSpeed);
                        mMachineData.setOperatingFrequency(progressSpeed);
                    } else if (TypeMark == 2) {
                        if (progressGradient >= 1) {
                            progressGradient -= 1;
                        }
                        progressGradient = (progressGradient > 0) ? progressGradient
                                : 0;
                        gradientShow(progressGradient);
                        mMachineData.setIncline(progressGradient);
                    }
                    // Log.i("LYJ", "progressGradient:" + progressGradient
                    // + " getIncline():" + mMachineData.getIncline());
                    updateToService();
                }
            }
        });

        // 左加2
        IB_leftBoth.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Beep.beep(getApplicationContext());
                Log.i("IB_leftBoth programType", "" + programType);
                if (programType >= HANDMOVE && programType <= BURN) {

                } else {
                    if (TypeMark == 1) {
                        progressSpeed += 10;
                        progressSpeed = (progressSpeed < defaultMaxSpeed * 10) ? progressSpeed
                                : defaultMaxSpeed * 10;
                        metricAndEnglishSwitch(progressSpeed);
                        mMachineData.setOperatingFrequency(progressSpeed);
                    } else if (TypeMark == 2) {
                        progressGradient += 2;
                        progressGradient = (progressGradient < defaultMaxLncline) ? progressGradient
                                : defaultMaxLncline;
                        gradientShow(progressGradient);
                        mMachineData.setIncline(progressGradient);
                    }
                    // Log.i("LYJ", "progressGradient:" + progressGradient
                    // + " getIncline():" + mMachineData.getIncline());
                    updateToService();
                }
            }
        });

        // 右减2
        IB_rightBoth.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Beep.beep(getApplicationContext());
                Log.i("IB_rightBoth programType", "" + programType);
                if (programType >= HANDMOVE && programType <= BURN) {
                } else {
                    if (TypeMark == 1) {

                        progressSpeed -= 10;
                        progressSpeed = (progressSpeed > defaultMinSpeed * 10) ? progressSpeed
                                : defaultMinSpeed * 10;
                        metricAndEnglishSwitch(progressSpeed);
                        mMachineData.setOperatingFrequency(progressSpeed);
                    } else if (TypeMark == 2) {

                        if (progressGradient >= 1) {
                            progressGradient -= 2;
                        }
                        progressGradient = (progressGradient > 0) ? progressGradient
                                : 0;
                        gradientShow(progressGradient);
                        mMachineData.setIncline(progressGradient);
                    }
                    updateToService();
                }
            }
        });

        // handle展开
        slidingDrawerSet.setOnDrawerOpenListener(new OnDrawerOpenListener() {

            @Override
            public void onDrawerOpened() {
                // TODO Auto-generated method stub
                // Log.i(TAG,
                // "onDrawerOpened "
                // + mMachineData.getOperatingFrequency() + " "
                // + mMachineData.getIncline());
                handle.setBackgroundResource(R.drawable.sliding_down);

                getPasswordFileDate();
                metricAndEnglishSwitch(progressSpeed);
                gradientShow(progressGradient);

            }
        });

        // handle关闭
        slidingDrawerSet.setOnDrawerCloseListener(new OnDrawerCloseListener() {

            @Override
            public void onDrawerClosed() {
                // TODO Auto-generated method stub
                // Log.i(TAG, "onDrawerClosed");
                handle.setBackgroundResource(R.drawable.sliding_up);
            }
        });

        // 参数设置
        Btn_SlidingSetting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Beep.beep(getApplicationContext());

                Log.i("Btn_SlidingSetting", "close");

                switchFragment = 9;
                switchFragment(switchFragment);
                slidingDrawerSet.close();
                handle.setBackgroundResource(R.drawable.sliding_down);

            }
        });

        Btn_SlidingMenu.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Beep.beep(getApplicationContext());
                slidingDrawerSet.close();
                Intent intent = new Intent(setActivity.this, AllApp.class);
                startActivity(intent);

            }
        });

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
                mService.unregisterCallback("com.example.jw", mCallback);
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
     * 按下停止出现个人消耗信息框,任务结束也需要显示消耗信息
     */
    public void pressStopShowConsumeDialogue() {

        Log.i("dialog", "进入消耗信息");
        // 按键板停止不用发给后台状态
        TypeMark = 1;// 让焦点回到速度上
        this.programType = 0;
        startEndMark = 0;
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.consumption_information, null);

        TV_consumptionTimeShow = (TextView) layout
                .findViewById(R.id.TV_consumptionTimeShow);
        TV_consumptionDistanceShow = (TextView) layout
                .findViewById(R.id.TV_consumptionDistanceShow);
        TV_consumptionSpeedShow = (TextView) layout
                .findViewById(R.id.TV_consumptionSpeedShow);
        TV_consumptionCalorieShow = (TextView) layout
                .findViewById(R.id.TV_consumptionCalorieShow);
        TV_consumptionOk = (TextView) layout
                .findViewById(R.id.TV_consumptionOk);

        Log.i("repliedBackup[0]", "" + repliedBackup[0]);
        Log.i("repliedBackup[1]", "" + repliedBackup[1]);
        Log.i("repliedBackup[2]", "" + repliedBackup[2]);
        Log.i("repliedBackup[3]", "" + repliedBackup[3]);
        Log.i("repliedBackup[4]", "" + repliedBackup[4]);

        Log.i(" Integer.parseInt(Time_Text)", Time_Text);

        if (isOrNoProgram) {
            if (repliedBackup[4] == TIME || repliedBackup[4] == HANDMOVE
                    || repliedBackup[4] == FLUCTUATE
                    || repliedBackup[4] == CLIMBING
                    || repliedBackup[4] == INTERVAL
                    || repliedBackup[4] == LOSTWEIGHT
                    || repliedBackup[4] == MOUNTION || repliedBackup[4] == BURN
                    || repliedBackup[4] == HEART65
                    || repliedBackup[4] == HEART75
                    || repliedBackup[4] == HEART85) {
                if (isOrNoClickStop) {
                    TV_consumptionTimeShow
                            .setText(":   "
                                    + formatIntToTime(((Integer
                                    .parseInt(Time_Text) * 60) - repliedBackup[0])));
                } else {
                    TV_consumptionTimeShow
                            .setText(":   "
                                    + formatIntToTime(Integer
                                    .parseInt(Time_Text) * 60));
                }
            } else {
                TV_consumptionTimeShow.setText(":   "
                        + formatIntToTime(repliedBackup[0]));
            }
            isOrNoProgram = false;
        } else {
            TV_consumptionTimeShow.setText(":   "
                    + formatIntToTime(repliedBackup[0]));
        }

        String temp;
        int distanceResultString;
        if (repliedBackup[4] == DISTANCE) {
            if (isOrNoClickStop) {
                distanceResultString = (Integer.parseInt(Distance_Text) * 1000 - repliedBackup[1]);
            } else {
                distanceResultString = (Integer.parseInt(Distance_Text) * 1000);
            }
        } else {
            distanceResultString = repliedBackup[1];
        }
        Log.i("mProgramData.getGoal() / 100", "" + mProgramData.getGoal() / 100);

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
        temp = head + "." + pointRightText;
        if (defaultSing == 0) {
            TV_consumptionDistanceShow.setText(":  " + temp + " km");
        } else {
            TV_consumptionDistanceShow.setText(":  " + temp + " Mile");
        }

        int currentCalorie;
        if (repliedBackup[4] == HOT) {
            if (isOrNoClickStop) {
                currentCalorie = Integer.parseInt(Hot_Text) * 1000
                        - repliedBackup[2];
                Log.i("currentCalorie", "热量1" + currentCalorie);
            } else {
                currentCalorie = Integer.parseInt(Hot_Text) * 1000;
                Log.i("currentCalorie", "热量2" + currentCalorie);
            }
        } else {
            currentCalorie = repliedBackup[2];
        }

        tail = (int) currentCalorie % 1000;
        if (tail / 100 > 0) {
            pointRightText = "" + tail;
        } else if (tail / 10 > 0) {
            pointRightText = "0" + tail;
        } else {
            pointRightText = "00" + tail;
        }
        temp = "" + currentCalorie / 1000 + "." + pointRightText;
        TV_consumptionCalorieShow.setText(":   " + temp + " kCal");

        if (defaultSing == 0) {
            TV_consumptionSpeedShow.setText(":   " + repliedBackup[3] / 10
                    + "." + repliedBackup[3] % 10 + " km/h");
        } else {
            TV_consumptionSpeedShow.setText(":   " + repliedBackup[3] / 100 * 6
                    + "." + repliedBackup[3] % 100 * 6 + " Mile/h");
        }

        isOrNoClickStop = false;
        if (null == dialog) {
            dialog = new Dialog(setActivity.this);
        } else {
            dialog.show();
        }

        TV_consumptionOk.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Beep.beep(getApplicationContext());
                switchFragment = 0;
                dialog.dismiss();
                dialog = null;
                switchFragment(switchFragment);
            }
        });

        dialog.setContentView(layout);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

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

    /*
     * 公英制转换
     */
    public void metricAndEnglishSwitch(int progressSpeed) {

        if (defaultSing == 0) {

            PB_Speed.metricAndEnglish = 0;
            TV_Speed.setText(R.string.Speed);
            TV_Distance.setText(R.string.Distance);
            Intent intent = new Intent();
            intent.setAction("defaultSing");
            intent.putExtra("defaultSing", defaultSing);
            sendBroadcast(intent);
            speedShow(progressSpeed);
        } else {

            PB_Speed.metricAndEnglish = 1;
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

        this.progressGradient = progressGradient;// 按键板选择的时候可以喝界面调节加减的同步
        TV_GradiendShow_Top.setText("" + progressGradient);
        PB_Gradient.setProgress(progressGradient, circle_point_imgSpeed);
    }

    private void back() {

        if (isOrNoAtsetFragment && mMachineData.currentStatus != 1) {
            Log.i("执行了finish", "" + 0);
            switchFragment = 0;
            switchFragment(switchFragment);
        } else {

            if (isOrNoAtNowModeProjectActivity) {
                programType = 0;
            }
            Log.i("没有执行了finish", "" + 1);
            switchFragment = 6;
            switchFragment(switchFragment);
        }
        Beep.beep(getApplicationContext());

    }

    /*
     * 判断文件是否存在
     */
    public boolean fileIsExists() {
        try {
            File f = new File(
                    "/data/data/com.ada.JW2/shared_prefs/password.xml");
            if (!f.exists()) {

                return false;
            }
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
        return true;
    }

    /*
     * 数据库写数据
     */
    public void setPasswordFlieDate() {

        SharedPreferences sharedPreferences = getSharedPreferences("password",
                0);
        // aredPreferences.Editor
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // 以int形式存储，键值对
        editor.putInt("password", password);
        editor.putInt("totalDistanceValue", totalDistanceValue);
        editor.putInt("totalTimeValue", totalTimeValue);

        editor.putInt("defaultSing", defaultSing);
        editor.putInt("defaultMinSpeed", defaultMinSpeed);
        editor.putInt("defaultMaxSpeed", defaultMaxSpeed);
        editor.putInt("defaultMaxLncline", defaultMaxLncline);
        editor.putInt("defaultSpeedTime", defaultSpeedTime);

        editor.putInt("defaultWhell", defaultWhell);
        editor.putInt("defaultAutoOilDistance", defaultAutoOilDistance);
        editor.putInt("defaultoilTime", defaultoilTime);
        editor.putInt("defaultAddOliNumber", defaultAddOliNumber);

        editor.putInt("setDistanceLimit", setDistanceLimit);// 最大距离(锁机距离)
        editor.putInt("lockDistanceTemp", lockDistanceTemp);// 中间值
        editor.putInt("lockDistance", lockDistance);// 设定锁机后计算距离
        editor.putBoolean("lock", lock);
        editor.putBoolean("locked", locked);

        editor.putInt("setNumberSectionSpeed", setNumberSectionSpeed);// 段速

        // 提交数据
        editor.commit();
        // Toast.makeText(this, "写入", Toast.LENGTH_LONG).show();
    }

    /*
     * 数据库读数据
     */
    public void getPasswordFileDate() {

        SharedPreferences sharedPreferences = getSharedPreferences("password",
                0);
        //
        // 数据库取出数据
        password = sharedPreferences.getInt("password", 0);
        totalDistanceValue = sharedPreferences.getInt("totalDistanceValue", 0);
        totalTimeValue = sharedPreferences.getInt("totalTimeValue", 0);

        defaultSing = sharedPreferences.getInt("defaultSing", 0);
        defaultMinSpeed = sharedPreferences.getInt("defaultMinSpeed", 0);
        defaultMaxSpeed = sharedPreferences.getInt("defaultMaxSpeed", 0);
        defaultMaxLncline = sharedPreferences.getInt("defaultMaxLncline", 0);
        defaultSpeedTime = sharedPreferences.getInt("defaultSpeedTime", 0);

        defaultWhell = sharedPreferences.getInt("defaultWhell", 0);
        defaultAutoOilDistance = sharedPreferences.getInt(
                "defaultAutoOilDistance", 0);
        defaultoilTime = sharedPreferences.getInt("defaultoilTime", 0);
        defaultAddOliNumber = sharedPreferences
                .getInt("defaultAddOliNumber", 0);
        lockDistance = sharedPreferences.getInt("lockDistance", 0);
        lockDistanceTemp = sharedPreferences.getInt("lockDistanceTemp", 0);
        setDistanceLimit = sharedPreferences.getInt("setDistanceLimit", 0);
        lock = sharedPreferences.getBoolean("lock", false);
        locked = sharedPreferences.getBoolean("locked", false);

        setNumberSectionSpeed = sharedPreferences.getInt(
                "setNumberSectionSpeed", 0);
    }

    /*
     * 按键广播
     */
    private class OnKeypadDownReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            int keyCode = intent.getIntExtra("keyCode", 0);
            if (action.equals("com.ada.keydown.service")) {

                dataUpdateHandler.sendEmptyMessage(keyCode);
            }
        }
    }

    /*
     * 切换fragment
     */
    public void switchFragment(int switchFragment) {

        Log.i("切换 switchFragment", "" + switchFragment);
        switch (switchFragment) {
            case 0:
                if (null == mMainFragment) {
                    mMainFragment = new mainFragment();
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, mMainFragment).commitAllowingStateLoss();
                break;
            case 1:
                if (null == mSetPerFragment) {
                    mSetPerFragment = new setPerFragment();
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, mSetPerFragment).commitAllowingStateLoss();
                break;

            case 2:
                if (null == mNowModeProjectFragment) {
                    mNowModeProjectFragment = new nowModeProjectFragment().newInstance(this);
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, mNowModeProjectFragment)
                        .commitAllowingStateLoss();
                break;

            case 3:
                if (null == mNowModeGoalFragment) {
                    mNowModeGoalFragment = new nowModeGoalFragment().newInstance(this);
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, mNowModeGoalFragment)
                        .commitAllowingStateLoss();
                break;
            case 4:
                if (null == mNowModeHeartFragment) {
                    mNowModeHeartFragment = new nowModeHeartFragment();
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, mNowModeHeartFragment)
                        .commitAllowingStateLoss();
                break;
            case 5:
                if (null == mTaskFragment) {
                    mTaskFragment = new taskFragment();
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, mTaskFragment).commitAllowingStateLoss();
                break;
            case 6:
                if (null == mSetFragment) {
                    mSetFragment = new setFragment();
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, mSetFragment).commitAllowingStateLoss();
                isOrNoAtsetFragment = true;
                break;

            case 7:
                if (null == mCountDownFragment) {
                    mCountDownFragment = new countDownFragment();
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, mCountDownFragment)
                        .commitAllowingStateLoss();
                break;
            case 8:
                if (null == mWarningFragment) {
                    mWarningFragment = new warningFragment().newInstance(this);
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, mWarningFragment).commitAllowingStateLoss();
                break;
            case 9:

                if (null == mParameterSettingFragment) {
                    mParameterSettingFragment = new ParameterSettingFragment();
                }

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, mParameterSettingFragment)
                        .addToBackStack(null).commitAllowingStateLoss();
                break;
            case 10:
                if (null == mFactoryPatternSetFragment) {
                    mFactoryPatternSetFragment = new FactoryPatternSetFragment()
                            .newInstance(this);
                }
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, mFactoryPatternSetFragment)
                        .commitAllowingStateLoss();
                break;
            case 11:
                if (null == mExplorerFragment) {
                    mExplorerFragment = new explorerFragment().newInstance(this);
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, mExplorerFragment)
                        .commitAllowingStateLoss();
                break;
            case 12:
                if (null == mMovieFragment) {
                    mMovieFragment = new movieFragment().newInstance(this);
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, mMovieFragment).commitAllowingStateLoss();
                break;
            case 13:
                if (null == mLockFragment) {
                    mLockFragment = new lockFragment().newInstance(this);
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, mLockFragment).commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }
}
