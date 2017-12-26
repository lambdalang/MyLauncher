package com.ada.mcu.ui;

import android.app.ActivityManager;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ada.jw.R;
import com.ada.mcu.service.MachineData;
import com.ada.mcu.service.ServiceRepliedData;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ada on 2015/6/27.
 */
public class ExtraWindow {
    private final String TAG = "ExtraWindow";
    private Context mContext;
    private View bottomLineView;
    private View extraView;
    private ImageView iv;
    private WindowManager wm;
    private ActivityManager am;
    private WindowManager.LayoutParams bottomLineWL;
    private WindowManager.LayoutParams extraViewWL;
    private Timer mTimer;
    private TimeCounterTask mTimerTask;
    private ImageButton backBtn;
    private ImageButton homeBtn;
    private ImageButton menuBtn;
    private ImageButton volumeUpBtn;
    private ImageButton volumeDownBtn;
    private ImageButton muteBtn;
    private ImageButton channelPlusBtn;
    private ImageButton channelMinusBtn;
    private TextView inclineText;
    private TextView timeText;
    private TextView distanceText;
    private TextView caloriesText;
    private TextView heartBitText;
    private TextView speedText;
    private LinearLayout topView;
    private LinearLayout bottomView;
    private AudioManager mAudioManager;
    private static boolean windowHasAdd = false;
    private boolean muteStatus = false;
    private final int EXTRA_VIEW_REMOVE = 1000;
    private final int EXTRA_VIEW_ADD = 1001;
    private final int EXTRA_STATUS_VIEW_UPDATE = 1002;

    public ExtraWindow(Context context, WindowManager wm) {
        mContext = context;
        this.wm = wm;
        am = (ActivityManager) mContext.getSystemService(mContext.ACTIVITY_SERVICE);
        findView();
        initWindow();
    }

    private void findView() {
        bottomLineView = LayoutInflater.from(mContext).inflate(R.layout.bottomline, null);
        extraView = LayoutInflater.from(mContext).inflate(R.layout.extra_view, null);

        iv = (ImageView) bottomLineView.findViewById(R.id.bottom_lines);
        backBtn = (ImageButton) extraView.findViewById(R.id.back);
        homeBtn = (ImageButton) extraView.findViewById(R.id.home);
        menuBtn = (ImageButton) extraView.findViewById(R.id.menu);
        volumeUpBtn = (ImageButton) extraView.findViewById(R.id.volume_up);
        volumeDownBtn = (ImageButton) extraView.findViewById(R.id.volume_down);
        muteBtn = (ImageButton) extraView.findViewById(R.id.mute);
        channelPlusBtn = (ImageButton) extraView.findViewById(R.id.channel_plus);
        channelMinusBtn = (ImageButton) extraView.findViewById(R.id.channel_minus);

        inclineText = (TextView) extraView.findViewById(R.id.incline);
        timeText = (TextView) extraView.findViewById(R.id.time);
        distanceText = (TextView) extraView.findViewById(R.id.distance);
        caloriesText = (TextView) extraView.findViewById(R.id.calories);
        heartBitText = (TextView) extraView.findViewById(R.id.heart_rate);
        speedText = (TextView) extraView.findViewById(R.id.speed);

        topView = (LinearLayout) extraView.findViewById(R.id.top);
        bottomView = (LinearLayout) extraView.findViewById(R.id.bottom);

        backBtn.setOnClickListener(InjectKeycodeClickListener);
        homeBtn.setOnClickListener(InjectKeycodeClickListener);
        menuBtn.setOnClickListener(InjectKeycodeClickListener);
        volumeUpBtn.setOnClickListener(InjectKeycodeClickListener);
        volumeDownBtn.setOnClickListener(InjectKeycodeClickListener);
        muteBtn.setOnClickListener(InjectKeycodeClickListener);

        backBtn.setOnTouchListener(ImageButtonTouchListener);
        homeBtn.setOnTouchListener(ImageButtonTouchListener);
        menuBtn.setOnTouchListener(ImageButtonTouchListener);
        volumeUpBtn.setOnTouchListener(ImageButtonTouchListener);
        volumeDownBtn.setOnTouchListener(ImageButtonTouchListener);
        muteBtn.setOnTouchListener(ImageButtonTouchListener);
    }

    private void initWindow() {
        bottomLineWL = new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                15, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_TOUCHABLE_WHEN_WAKING
                | WindowManager.LayoutParams.FLAG_SPLIT_TOUCH, PixelFormat.TRANSLUCENT);
        bottomLineWL.gravity = Gravity.BOTTOM;
        bottomLineView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (checkTopActivity())
                    return false;

                if (!extraViewHandler.hasMessages(EXTRA_VIEW_ADD)
                        && !extraViewHandler.hasMessages(EXTRA_VIEW_REMOVE))
                    extraViewHandler.sendEmptyMessage(EXTRA_VIEW_ADD);
                return true;
            }
        });
        wm.addView(bottomLineView, bottomLineWL);

        extraViewWL = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        extraView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                Log.i("LYJ", "extraView onTouch:" + x + ":" + y);
                Rect topRect = new Rect();
                Rect bottomRect = new Rect();
                topView.getGlobalVisibleRect(topRect);
                bottomView.getGlobalVisibleRect(bottomRect);
                if (!topRect.contains(x, y) && !bottomRect.contains(x, y)) {
                    if (!extraViewHandler.hasMessages(EXTRA_VIEW_REMOVE))
                        extraViewHandler.sendEmptyMessage(EXTRA_VIEW_REMOVE);
                }
                return false;
            }
        });
    }

    private boolean checkTopActivity() {
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        String packageName = cn.getPackageName();
        Log.i(TAG, "Get package name:" + packageName);
        if(packageName.equals("com.ada.jw") || packageName.equals("com.example.jw"))
            return true;
        else return false;
    }

    public void setMuteStatus(boolean enable) {
        Log.i(TAG, "setMuteStatus:" + enable);
        muteStatus = enable;
        muteBtn.setImageResource((enable ? R.drawable.icon_mute_on : R.drawable.icon_mute_off));
    }

    private void isMuteNow() {
        mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        setMuteStatus((mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC) == 0));
    }

    private void sendKeyDownUpSync(final int id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Instrumentation inst=new Instrumentation();
                    switch(id) {
                        case R.id.back:
                            inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                            break;
                        case R.id.home:
                            inst.sendKeyDownUpSync(KeyEvent.KEYCODE_HOME);
                            break;
                        case R.id.menu:
                            inst.sendKeyDownUpSync(KeyEvent.KEYCODE_MENU);
                            break;
                        case R.id.volume_up:
                            inst.sendKeyDownUpSync(KeyEvent.KEYCODE_VOLUME_UP);
                            break;
                        case R.id.volume_down:
                            inst.sendKeyDownUpSync(KeyEvent.KEYCODE_VOLUME_DOWN);
                            break;
                        case R.id.mute:
                            inst.sendKeyDownUpSync(KeyEvent.KEYCODE_VOLUME_MUTE);
                            break;
                        default:
                            break;
                    }
                    reScheduleTimerTask();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private View.OnClickListener InjectKeycodeClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final int id = v.getId();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Instrumentation inst=new Instrumentation();
                        switch(id) {
                            case R.id.back:
                                inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                                break;
                            case R.id.home:
                                //inst.sendKeyDownUpSync(KeyEvent.KEYCODE_HOME);
                                ComponentName c = new ComponentName(
                                        "com.example.jw", "com.ada.jw.SetActivity");
                                Intent i = new Intent();
                                i.setComponent(c);
                                mContext.startActivity(i);
                                break;
                            case R.id.menu:
                                inst.sendKeyDownUpSync(KeyEvent.KEYCODE_MENU);
                                break;
                            case R.id.volume_up:
                                inst.sendKeyDownUpSync(KeyEvent.KEYCODE_VOLUME_UP);
                                break;
                            case R.id.volume_down:
                                inst.sendKeyDownUpSync(KeyEvent.KEYCODE_VOLUME_DOWN);
                                break;
                            case R.id.mute:
                                inst.sendKeyDownUpSync(KeyEvent.KEYCODE_VOLUME_MUTE);
                                break;
                            case R.id.channel_plus:
                                inst.sendKeyDownUpSync(KeyEvent.KEYCODE_CHANNEL_UP);
                                break;
                            case R.id.channel_minus:
                                inst.sendKeyDownUpSync(KeyEvent.KEYCODE_CHANNEL_DOWN);
                                break;
                            default:
                                break;
                        }
                        reScheduleTimerTask();
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    };

    private View.OnTouchListener ImageButtonTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int id = v.getId();
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                sendKeyDownUpSync(id);
                switch(id) {
                    case R.id.back:
                        backBtn.setImageResource(R.drawable.icon_back_clicked);
                        break;
                    case R.id.home:
                        homeBtn.setImageResource(R.drawable.icon_home_clicked);
                        break;
                    case R.id.menu:
                        menuBtn.setImageResource(R.drawable.icon_menu_clicked);
                        break;
                    case R.id.volume_up:
                        volumeUpBtn.setImageResource(R.drawable.icon_volume_up_clicked);
                        break;
                    case R.id.volume_down:
                        volumeDownBtn.setImageResource(R.drawable.icon_volume_down_clicked);
                        break;
                    case R.id.mute:
                        setMuteStatus(!muteStatus);
                        break;
                    case R.id.channel_plus:
                        channelPlusBtn.setImageResource(R.drawable.icon_ch_plus_clicked);
                        //inst.sendKeyDownUpSync(KeyEvent.KEYCODE_CHANNEL_UP);
                        break;
                    case R.id.channel_minus:
                        channelMinusBtn.setImageResource(R.drawable.icon_ch_minus_clicked);
                        //inst.sendKeyDownUpSync(KeyEvent.KEYCODE_CHANNEL_DOWN);
                        break;
                    default:
                        break;
                }
            }else if(event.getAction() == MotionEvent.ACTION_UP) {
                switch(id) {
                    case R.id.back:
                        backBtn.setImageResource(R.drawable.icon_back);
                        break;
                    case R.id.home:
                        homeBtn.setImageResource(R.drawable.icon_home);
                        break;
                    case R.id.menu:
                        menuBtn.setImageResource(R.drawable.icon_menu);
                        break;
                    case R.id.volume_up:
                        volumeUpBtn.setImageResource(R.drawable.icon_volume_up);
                        break;
                    case R.id.volume_down:
                        volumeDownBtn.setImageResource(R.drawable.icon_volume_down);
                        break;
                    case R.id.mute:
                        break;
                    case R.id.channel_plus:
                        channelPlusBtn.setImageResource(R.drawable.icon_ch_plus);
                        break;
                    case R.id.channel_minus:
                        channelMinusBtn.setImageResource(R.drawable.icon_ch_minus);
                        break;
                    default:
                        break;
                }
            }
            return true;
        }
    };

    private void startTimerTask() {
        if(mTimer == null) {
            mTimer = new Timer();
        }
        mTimerTask = new TimeCounterTask();

        mTimer.schedule(mTimerTask, 3000);
    }

    private void reScheduleTimerTask() {
        mTimer.cancel();
        mTimer = new Timer();
        mTimerTask = new TimeCounterTask();
        mTimer.schedule(mTimerTask, 3000);
    }

    public void show() {
        extraViewHandler.sendEmptyMessage(EXTRA_VIEW_ADD);
    }

    private Handler extraViewHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case EXTRA_VIEW_ADD:
                    if(!windowHasAdd) {
                        wm.removeView(bottomLineView);
                        wm.addView(extraView, extraViewWL);
                        windowHasAdd = true;
                        isMuteNow();
                        startTimerTask();
                    }
                    break;
                case EXTRA_VIEW_REMOVE:
                    if(windowHasAdd) {
                        wm.removeView(extraView);
                        wm.addView(bottomLineView, bottomLineWL);
                        windowHasAdd = false;
                    }
                    break;
                case EXTRA_STATUS_VIEW_UPDATE:
                    int incline = msg.getData().getInt("incline");
                    int timeCount = msg.getData().getInt("timeCount");
                    int miles = msg.getData().getInt("miles");
                    int calories = msg.getData().getInt("calories");
                    int heartRate = msg.getData().getInt("heartRate");
                    int speed = msg.getData().getInt("speed");
                    inclineText.setText("" + incline);

                    speedText.setText("" + (speed / 10) + "." + (speed % 10));

                    int head = miles / 1000;
                    int tail = miles % 1000;
                    String pointRightText = "";
                    if (tail / 100 > 0) {
                        pointRightText = "" + tail;
                    } else if (tail / 10 > 0) {
                        pointRightText = "0" + tail;
                    } else {
                        pointRightText = "00" + tail;
                    }
                    distanceText.setText(head + "." + pointRightText);

                    tail = calories % 1000;
                    if (tail / 100 > 0) {
                        pointRightText = "" + tail;
                    } else if (tail / 10 > 0) {
                        pointRightText = "0" + tail;
                    } else {
                        pointRightText = "00" + tail;
                    }
                    caloriesText.setText(calories / 1000 + "." + pointRightText);
                    heartBitText.setText("" + (heartRate & 0xff));
                    timeText.setText(formatIntToTime(timeCount));
                    break;
                default:
                    break;
            }
        }
    };

    public void updateDataToExtraWindow(int incline, int timeCount, int miles, int calories,
                                        int heartRate, int speed) {
        Message msg = new Message();
        msg.what = EXTRA_STATUS_VIEW_UPDATE;
        Bundle b = new Bundle();
        b.putInt("incline", incline);
        b.putInt("timeCount", timeCount);
        b.putInt("miles", miles);
        b.putInt("calories", calories);
        b.putInt("heartRate", heartRate);
        b.putInt("speed", speed);
        msg.setData(b);
        extraViewHandler.sendMessage(msg);
    }

    private String formatIntToTime(int timeCount) {
        String hh = timeCount / 3600 > 9 ? timeCount / 3600 + "" : "0"
                + timeCount / 3600;
        String mm = (timeCount % 3600) / 60 > 9 ? (timeCount % 3600) / 60 + ""
                : "0" + (timeCount % 3600) / 60;
        String ss = (timeCount % 3600) % 60 > 9 ? (timeCount % 3600) % 60 + ""
                : "0" + (timeCount % 3600) % 60;
        return (hh + ":" + mm + ":" + ss);
    }

    private class TimeCounterTask extends TimerTask {

        @Override
        public void run() {
            if(!extraViewHandler.hasMessages(EXTRA_VIEW_REMOVE))
                extraViewHandler.sendEmptyMessage(EXTRA_VIEW_REMOVE);
        }
    }
}
