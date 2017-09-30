/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.tufer.mylauncher;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.EthernetManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mstar.android.tv.TvChannelManager;
import com.mstar.android.tv.TvCommonManager;
import com.mstar.android.tvapi.common.TvManager;
import com.mstar.android.tvapi.common.exception.TvCommonException;
import com.mstar.android.tvapi.common.vo.EnumFirstServiceInputType;
import com.mstar.android.tvapi.common.vo.EnumFirstServiceType;
import com.mstar.android.tvapi.common.vo.EnumScalerWindow;
import com.mstar.android.tvapi.common.vo.VideoWindowType;
import com.tufer.mylauncher.utils.NetworkChangedManager;
import com.tufer.mylauncher.utils.ResumeToSourceGlobal;
import com.tufer.mylauncher.utils.SDcardBroadcastReceiver;
import com.tufer.mylauncher.utils.SPUtils;
import com.tufer.mylauncher.utils.Util;
import com.tufer.mylauncher.utils.WifiAdmin;
import com.tufer.mylauncher.utils.networkBroadcastReceiver;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.tufer.mylauncher.R.id.TV_inputSourceType;

/*
 * MainActivity class that loads MainFragment
 */
public class MainActivity extends Activity implements View.OnClickListener, NetworkChangedManager.NetworkChengedListener, View.OnFocusChangeListener {
    /**
     * Called when the activity is first created.
     */
    private static String TAG = "MainActivity";
    public static MainActivity mainActivity;
    public static final int UPDATA_UI = 0;
    public static final int NETWORK_ERROR = -1;
    public static final int DATA_ERROR = -2;
    public static final int UPDATA_CITY = 2;
    public static final int UPDATA_WEATHER = 3;
    public static final int UPDATA_USB_GONE = 4;
    public static final int UPDATA_USB_VISIBLE = 5;

    private final static String USB_ACTION = "android.hardware.usb.action.USB_STATE";
    private final static String WIFI_ACTION = "WifiManager.WIFI_STATE_CHANGED_ACTION";


    private WifiAdmin mwAdmin;// wifi management

    private static networkBroadcastReceiver networkRec;
    private int netWorkType;
    private int level;
    private TimerTask timerTask;
    Timer mTimer = new Timer();


    private ImageView launcherThermometer;
    private TextView launcherTime;
    private TextView launcherDate;
    private TextView launcherWeatherCity;
    private ImageView launcherWeatherImg;
    private TextView launcherWeatherTemperature;
    private LinearLayout linearlayoutMedia;
    private LinearLayout linearlayoutTeaching;
    private LinearLayout linearlayoutTeachingResources;
    private LinearLayout linearlayoutAllApp;
    private LinearLayout linearlayoutBrowser;
    private LinearLayout linearlayoutBook;
    private LinearLayout linearlayoutSetting;
    private ImageView launcherWifi;
    private ImageView launcherUsb;
    private ImageView launcherNetwork;


    ViewGroup.LayoutParams para;

    private View RL_tv;
    private LinearLayout surfaceViewLayout;
    private SurfaceView surfaceView = null;
    private Callback callback;
    private boolean createsurface = false;
    private boolean firstSetPipscale = true;
    private float scale;
    private String screenname;
    private Handler handlertv = new Handler();
    private TimerTask tvTimeTask;
    Timer mTVtimet = new Timer();
    private static boolean PowerOnstate = true;
    int toChangeInputSource = -1;
    private boolean firstSelectPage = true;

    private TextView No_Signal_textview;
    private TextView TV_inputSourceType;
    private int inputSource = -1;


    DisplayMetrics dm;
    float density;    // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
    float densityDpiX,densityDpiY;


    private final static int RO_SF_LCD_DENSITY_320 = 320;

    private final static int PANEL_LINK_TYPE_VB1 = 10;


    private int[] weatherCondIcon = {R.drawable.baoxue, R.drawable.baoyu, R.drawable.baoyuzhuandabaoyu,
            R.drawable.dabaoyu, R.drawable.dabaoyuzhuantedabaoyu, R.drawable.daxue,
            R.drawable.daxuezhuanbaoxue, R.drawable.dayu, R.drawable.dayuzhuanbaoyu,
            R.drawable.dongyu, R.drawable.duoyun, R.drawable.fuchen,
            R.drawable.leizhenyu, R.drawable.leizhenyubanyoubingbao,
            R.drawable.mai, R.drawable.xiaoyu, R.drawable.qiangshachenbao, R.drawable.qing, R.drawable.shachenbao,
            R.drawable.tedabaoyu, R.drawable.wu, R.drawable.xiaoxue, R.drawable.xiaoxuezhuanzhongxue,
            R.drawable.xiaoyu, R.drawable.xiaoyuzhuanzhongyu,
            R.drawable.yangsha, R.drawable.yin, R.drawable.yujiaxue, R.drawable.zhenxue,
            R.drawable.zhenyu, R.drawable.zhongxue, R.drawable.zhongxuezhuandaxue,
            R.drawable.zhongyu, R.drawable.zhongyuzhuandayu};

    private int[][] videoWindowCoordinate = {
            {1042, 660, 1770, 940},  //3840*2160 density=240
            {377, 228, 362, 838},     //1680*1050 invalid value, waiting for debug
            {377, 228, 362, 838},     //1600*900  invalid value, waiting for debug
            {397, 268, 424, 884},     //1440*900
            {377, 228, 362, 838},     //1280*800  invalid value, waiting for debug
            {377, 228, 362, 838},     //1280*1024 invalid value, waiting for debug
            {377, 228, 362, 838},     //1024*768  invalid value, waiting for debug
    };

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-09-21 15:56:50 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DATA_ERROR:
                    Toast.makeText(MainActivity.this, getString(R.string.str_weather_data_error), Toast.LENGTH_SHORT).show();
                case NETWORK_ERROR:
                    Toast.makeText(MainActivity.this, getString(R.string.str_weather_network_error), Toast.LENGTH_SHORT).show();
                case UPDATA_UI:
                    launcherTime.setText(getTime());
                    launcherDate.setText(getDate());
                    break;
                case UPDATA_CITY:
                    launcherWeatherCity.setText(Util.city);
                    break;
                case UPDATA_WEATHER:
                    int condIcon = CondIcon(Util.weatherType, MainActivity.this);
                    launcherWeatherImg.setImageResource(condIcon);
                    Pattern p = Pattern.compile("\\d+");
                    Matcher m = p.matcher(Util.low);
                    Matcher d = p.matcher(Util.high);
                    d.find();
                    m.find();
                    launcherWeatherTemperature.setText(m.group() + "~" + d.group() + getString(R.string.degree) + "C");
                    break;
                case UPDATA_USB_GONE:
                    launcherUsb.setVisibility(View.GONE);
                    break;
                case UPDATA_USB_VISIBLE:
                    launcherUsb.setVisibility(View.VISIBLE);
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private View.OnFocusChangeListener myOnFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            LinearLayout linearLayout = (LinearLayout) view;
            para = (LinearLayout.LayoutParams) linearLayout.getChildAt(0).getLayoutParams();
            if (b) {
                Log.d(TAG, linearLayout.getChildAt(0).getWidth() + "");
                Log.d(TAG, linearLayout.getChildAt(0).getHeight() + "");
                para.width = (int) (getResources().getInteger(R.integer.linear_menu_big) * density + 0.5f);
                para.height = (int) (getResources().getInteger(R.integer.linear_menu_big) * density + 0.5f);
                linearLayout.getChildAt(0).setLayoutParams(para);
            } else {
                para.width = (int) (getResources().getInteger(R.integer.linear_menu) * density + 0.5f);
                para.height = (int) (getResources().getInteger(R.integer.linear_menu) * density + 0.5f);
                linearLayout.getChildAt(0).setLayoutParams(para);
                linearLayout.setFocusableInTouchMode(false);
            }
        }
    };

    private BroadcastReceiver wifiRec = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
                int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, -1);
                Log.e("WIFI状态", "wifiState:" + wifiState);
                switch (wifiState) {
                    case WifiManager.WIFI_STATE_DISABLED:
                        launcherWifi.setVisibility(View.GONE);
                        Log.e("WIFI状态", "wifiState:WIFI_STATE_DISABLED");
                        break;
                    case WifiManager.WIFI_STATE_DISABLING:
                        Log.e("WIFI状态", "wifiState:WIFI_STATE_DISABLING");
                        break;
                    case WifiManager.WIFI_STATE_ENABLED:

                        launcherWifi.setVisibility(View.VISIBLE);
                        Log.e("WIFI状态", "wifiState:WIFI_STATE_ENABLED");
                        break;
                    case WifiManager.WIFI_STATE_ENABLING:
                        Log.e("WIFI状态", "wifiState:WIFI_STATE_ENABLING");
                        break;
                    case WifiManager.WIFI_STATE_UNKNOWN:
                        Log.e("WIFI状态", "wifiState:WIFI_STATE_UNKNOWN");
                        break;
                    //
                }
            }
        }
    };

    Runnable handlerRuntv = new Runnable() {

        @Override
        public void run() {
            try {
                if (surfaceView == null) {
                    surfaceView = new SurfaceView(MainActivity.this);
                    surfaceView.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    Log.d(TAG, "surfaceView" + "start");
                    surfaceViewLayout.addView(surfaceView);
                }
                Log.d(TAG, "openSurfaceView" + "start");
                openSurfaceView();
                setPipscale();
                if (surfaceView != null) {
                    surfaceView.setBackgroundColor(Color.TRANSPARENT);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            handlertv.removeCallbacks(handlerRuntv);
        }
    };


    private void findViews() {
        launcherThermometer = (ImageView) findViewById(R.id.launcher_thermometer);
        launcherTime = (TextView) findViewById(R.id.launcher_time);
        launcherDate = (TextView) findViewById(R.id.launcher_date);
        launcherWeatherCity = (TextView) findViewById(R.id.launcher_weather_city);
        launcherWeatherImg = (ImageView) findViewById(R.id.launcher_weather_img);
        launcherWeatherTemperature = (TextView) findViewById(R.id.launcher_weather_temperature);
//        launcherTv = (LinearLayout) findViewById(R.id.launcher_tv);
//        launcherTvInfo = (Button) findViewById(R.id.launcher_tv_info);
        linearlayoutMedia = (LinearLayout) findViewById(R.id.linearlayout_media);
        linearlayoutTeaching = (LinearLayout) findViewById(R.id.linearlayout_teaching);
        linearlayoutTeachingResources = (LinearLayout) findViewById(R.id.linearlayout_teaching_resources);
        linearlayoutAllApp = (LinearLayout) findViewById(R.id.linearlayout_all_app);
        linearlayoutBrowser = (LinearLayout) findViewById(R.id.linearlayout_browser);
        linearlayoutBook = (LinearLayout) findViewById(R.id.linearlayout_book);
        linearlayoutSetting = (LinearLayout) findViewById(R.id.linearlayout_setting);
        launcherUsb = (ImageView) findViewById(R.id.launcher_usb);
        launcherWifi = (ImageView) findViewById(R.id.launcher_wifi);
        launcherNetwork = (ImageView) findViewById(R.id.launcher_network);
//        launcherTv.setOnClickListener(this);
        linearlayoutMedia.setOnClickListener(this);
        linearlayoutMedia.setOnFocusChangeListener(myOnFocusChangeListener);
        linearlayoutTeaching.setOnClickListener(this);
        linearlayoutTeaching.setOnFocusChangeListener(myOnFocusChangeListener);
        linearlayoutTeachingResources.setOnClickListener(this);
        linearlayoutTeachingResources.setOnFocusChangeListener(myOnFocusChangeListener);
        linearlayoutAllApp.setOnClickListener(this);
        linearlayoutAllApp.setOnFocusChangeListener(myOnFocusChangeListener);
        linearlayoutBrowser.setOnClickListener(this);
        linearlayoutBrowser.setOnFocusChangeListener(myOnFocusChangeListener);
        linearlayoutBook.setOnClickListener(this);
        linearlayoutBook.setOnFocusChangeListener(myOnFocusChangeListener);
        linearlayoutSetting.setOnClickListener(this);
        linearlayoutSetting.setOnFocusChangeListener(myOnFocusChangeListener);
//        launcherTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                isLauncherJumpToMTvPlayer();
//            }
//        });

        RL_tv = (View) findViewById(R.id.RL_tv);
        RL_tv.setOnClickListener(this);
        RL_tv.setOnFocusChangeListener(this);

        para = RL_tv.getLayoutParams();
        Log.d(TAG, para.width + ":" + para.height);


        surfaceViewLayout = (LinearLayout) findViewById(R.id.pipview);
//        surfaceViewLayout.setOnClickListener(this);

        No_Signal_textview = (TextView) findViewById(R.id.no_signal_text);
        TV_inputSourceType = (TextView) findViewById(R.id.TV_inputSourceType);

        getDisplayInfomation();
        getDensity();

    }

//    private boolean checkPanelTypeIsUHD() {
//        boolean bPanelTypeIsUHD = false;
//        int screenWidth = -1;
//        int screenHeight = -1;
//        int panelLinkType = -1;
//        try {
//            screenWidth = TvManager.getInstance().getPanelIniInfo("panel:m_wPanelWidth");
//            screenHeight = TvManager.getInstance().getPanelIniInfo("panel:m_wPanelHeight");
//            panelLinkType = TvManager.getInstance().getPanelIniInfo("panel:m_ePanelLinkType");
//        } catch (TvCommonException e) {
//            //TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        // Log.d(TAG, "PanelWidth :" + screenWidth + "   PanelHeight :" + screenHeight);
//        //WindowManager windowManager = getWindowManager();
//        Display display = getWindowManager().getDefaultDisplay();
//        Log.d(TAG, "x and y :" + display.getWidth() + "x" + display.getHeight());
//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//        //int densityDpi = dm.densityDpi;
//        densityDpiX=dm.xdpi;
//        densityDpiY=dm.ydpi;
//        Log.d(TAG, "densityDpiX :" + densityDpiX+"    densityDpiY :" + densityDpiY);
//        if ((screenWidth == 3840) && (screenHeight == 2160) && (panelLinkType == PANEL_LINK_TYPE_VB1)
//                && (densityDpi == RO_SF_LCD_DENSITY_320)) {
//            bPanelTypeIsUHD = true;
//        }
//        return bPanelTypeIsUHD;
//    }

    private void getDisplayInfomation() {
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        Log.d(TAG, "the screen size is " + point.toString());
        getWindowManager().getDefaultDisplay().getRealSize(point);
        Log.d(TAG, "the screen real size is " + point.toString());
    }

    private void getDensity() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Log.d(TAG, "Density is " + displayMetrics.density + " densityDpi is " + displayMetrics.densityDpi + " height: " + displayMetrics.heightPixels +
                " width: " + displayMetrics.widthPixels);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        NetworkChangedManager.getInstence().addNetworkListener(this);
        dm = getResources().getDisplayMetrics();
        density = dm.density;    // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        densityDpiX=dm.xdpi;
        densityDpiY=dm.ydpi;
        Log.d(TAG, "densityDpiX :" + densityDpiX+"    densityDpiY :" + densityDpiY);
        mwAdmin = new WifiAdmin(this);
        mainActivity = this;
        getData();
        registerReceiver();
        setFocus();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (timerTask != null) {
            timerTask.cancel();
        }
        if (firstSelectPage) {
            firstSelectPage = false;
            setPipscale();
        }
        strResumeToTvPlayer();
        startTimer();
        backHomeSource();
    }

    @Override
    protected void onDestroy() {
        if (networkRec != null) {
            unregisterReceiver(networkRec);
        }
        if (wifiRec != null) {
            unregisterReceiver(wifiRec);
        }
        NetworkChangedManager.getInstence().removeNetworkListener(this);
        setPipscale(1);
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        setPipscale(1);
        super.onStop();
    }

    @Override
    protected void onPause() {
        if (tvTimeTask != null) {
            tvTimeTask.cancel();
        }
        handlertv.removeCallbacks(handlerRuntv);
        super.onPause();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setFocus() {
        RL_tv.setFocusable(true);
        RL_tv.requestFocus();
        RL_tv.setFocusableInTouchMode(true);
        RL_tv.requestFocusFromTouch();
        linearlayoutMedia.setFocusable(true);
        linearlayoutTeaching.setFocusable(true);
        linearlayoutTeachingResources.setFocusable(true);
        linearlayoutAllApp.setFocusable(true);
        linearlayoutBook.setFocusable(true);
        linearlayoutBrowser.setFocusable(true);
        linearlayoutSetting.setFocusable(true);
    }

    private void getData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    try {
                        handler.sendEmptyMessage(UPDATA_UI);
                        Thread.sleep(60 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        Util.getCity(handler);
        Util.getWeather(Util.city, handler);
        int state = Util.getNetworkState(this);
        if (state == 3) {
            //launcherWifi.setVisibility(View.GONE);
            launcherNetwork.setVisibility(View.VISIBLE);
        } else {
            //launcherWifi.setVisibility(View.VISIBLE);
            launcherNetwork.setVisibility(View.GONE);
        }
        launcherUsb.setVisibility(SDcardBroadcastReceiver.isVisible ? View.VISIBLE : View.GONE);
    }

    private String getDate() {
        Date date = new Date();
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd EEEE");
        return dateFm.format(date);
    }

    private String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return formatter.format(curDate);
    }

    private void registerReceiver() {
        networkRec = new networkBroadcastReceiver();
        IntentFilter networkFilter = new IntentFilter();
        networkFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkRec, networkFilter);

        IntentFilter wifiFilter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(wifiRec, wifiFilter);
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (b) {
            view.setBackgroundResource(R.drawable.tvfocus);
        } else {
            view.setBackgroundResource(R.drawable.tv);
            view.setFocusableInTouchMode(false);
        }
    }


    public int CondIcon(String getWeatherCond, Context context) {
        String[] weatherCond = context.getResources().getStringArray(R.array.weather);
        if (getWeatherCond != null) {
            for (int i = 0; i < weatherCond.length; i++) {
                if (getWeatherCond.equals(weatherCond[i])) {
                    return weatherCondIcon[i];
                }
            }
        } else {
            return (Integer) null;
        }
        return weatherCondIcon[10];
    }

    @Override
    public void onClick(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.requestFocusFromTouch();
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.RL_tv:
                isLauncherJumpToMTvPlayer();
                break;
            case R.id.pipview:
                isLauncherJumpToMTvPlayer();
                break;
            case R.id.linearlayout_media:
                if (isAvilible("com.jrm.localmm")) {
                    startApp("com.jrm.localmm");
                } else {
                    Toast.makeText(this, R.string.no_app, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.linearlayout_teaching:
                break;
            case R.id.linearlayout_teaching_resources:
                break;
            case R.id.linearlayout_all_app:
                intent.setClass(this, AllApplicationActivity.class);
                startActivity(intent);
                break;
            case R.id.linearlayout_browser:
                if (isAvilible("com.android.browser")) {
                    startApp("com.android.browser");
                } else {
                    Toast.makeText(this, R.string.no_app, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.linearlayout_book:
                if (isAvilible("cn.wps.moffice_eng")) {
                    startApp("cn.wps.moffice_eng");
                } else {
                    Toast.makeText(this, R.string.no_app, Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.linearlayout_setting:
                if (isAvilible("tufer.com.menutest")) {
                    startApp("tufer.com.menutest");
                } else if (isAvilible("com.android.tv.settings")) {
                    startApp("com.android.tv.settings");
                } else {
                    Toast.makeText(this, R.string.no_app, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void startApp(String name) {
        Intent intent = new Intent();
        PackageManager manager = getPackageManager();
        intent = manager.getLaunchIntentForPackage(name);
        startActivity(intent);
    }


    /**
     * 检查是否安装了指定的软件
     *
     * @param packageName
     * @return
     */
    public boolean isAvilible(String packageName) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
            e.printStackTrace();
        }
        if (packageInfo == null) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public void onNetworkChenged(NetworkInfo netInfo, int netWorkType) {
        this.netWorkType = netWorkType;
        //launcherNetwork.setVisibility(View.VISIBLE);
        Log.i(TAG, "netWorkType: --------" + netWorkType);
        switch (netWorkType) {
            case 0:
                launcherNetwork.setVisibility(View.GONE);
                //launcherWifi.setVisibility(View.GONE);
                break;
            case 1:
                launcherNetwork.setVisibility(View.GONE);
                //launcherWifi.setVisibility(View.VISIBLE);
                // if (!mWeather.isJsonEnd() && (checkNetworkData())) {

                //			if (!mWeather.isJsonEnd()) {
                //				allNetworkRequest();
                //			}

                break;
            case 2:
                //launcherWifi.setBackgroundResource(R.drawable.wifi0);
                launcherNetwork.setVisibility(View.VISIBLE);
                //launcherNetwork.setBackgroundResource(R.drawable.network);
                //			 if (!mWeather.isJsonEnd() && (checkNetworkData())) {

                //			if (!mWeather.isJsonEnd()) {
                //				allNetworkRequest();
                //			}

                break;
            default:
                break;
        }
    }

    public Handler wifiHandler = new Handler() {

        public void handleMessage(Message msg) {
//            TV_currentTime.setText(currentTime.getCurrentTime().substring(10,
//                    18));
//            TV_date.setText(currentTime.getCurrentTime().substring(0, 10)
//                    + "   ");
//            TV_week.setText(currentTime.getWeek());
            switch (msg.what) {
                case 1:
                    // TV_wifi.setText("1");
                    launcherWifi.setBackgroundResource(R.drawable.wifi_signal_3);
                    break;
                case 2:
                    // TV_wifi.setText("2");
                    launcherWifi.setBackgroundResource(R.drawable.wifi_signal_3);
                    break;
                case 3:
                    // TV_wifi.setText("3");
                    launcherWifi.setBackgroundResource(R.drawable.wifi_signal_2);
                    break;
                case 4:
                    // TV_wifi.setText("4");
                    launcherWifi.setBackgroundResource(R.drawable.wifi_signal_1);
                    break;
                case 5:
                    // TV_wifi.setText("5");
                    launcherWifi.setBackgroundResource(R.drawable.wifi0);
                    break;
//                case 6:
////                    // TV_wifi.setText("5");
////                    wendu.setText(cTemp+getString(R.string.degree)+"C");
//                    break;
//                case 7:
//                    wendu.setVisibility(View.GONE);
//                    wendutu.setVisibility(View.GONE);
//                    break;
                default:
                    break;
            }
        }

        ;
    };

    private void startTimer() {

        timerTask = new TimerTask() {

            @Override
            public void run() {
                //
                level = mwAdmin.mWifiManager.getConnectionInfo().getRssi();
                Message message = Message.obtain();
                //
                if (level <= 0 && level >= -50) {
                    message.what = 1;
                } else if (level < -50 && level >= -70) {
                    message.what = 2;
                } else if (level < -70 && level >= -80) {
                    message.what = 3;
                } else if (level < -80 && level >= -100) {
                    message.what = 4;
                } else {
                    message.what = 5;
                }
                wifiHandler.sendMessage(message);
            }
        };
        mTimer.schedule(timerTask, 0, 1000);
    }

    private void isLauncherJumpToMTvPlayer() {
        if (isAvilible("com.mstar.tv.tvplayer.ui")) {
            Log.d(TAG, "isLauncherJumpToMTvPlayer() " + "start");
            TvCommonManager.getInstance().setInputSource(toChangeInputSource);
            ComponentName componentName = null;
            componentName = new ComponentName("com.mstar.tv.tvplayer.ui",
                    "com.mstar.tv.tvplayer.ui.RootActivity");
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setComponent(componentName);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.no_app, Toast.LENGTH_SHORT).show();
        }

    }

    private void openSurfaceView() {
        final SurfaceHolder mSurfaceHolder = surfaceView.getHolder();
        Log.d(TAG, "openSurfaceView()" + "start");
        callback = new android.view.SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                Log.d(TAG, "surfaceDestroyed==========!null");
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (holder == null || holder.getSurface() == null
                            || holder.getSurface().isValid() == false)

                        return;
                    if (TvManager.getInstance() != null) {
                        Log.d(TAG, "surfaceCreated==========!null");
                        TvManager.getInstance().getPlayerManager()
                                .setDisplay(mSurfaceHolder);
                    }
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
                Log.d(TAG, "surfaceChanged==========!null");
                createsurface = true;
            }
        };
        Log.d(TAG, "addCallback(callback)=========!null" + callback);
        mSurfaceHolder.addCallback(callback);
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    private void setPipscale() {
        Log.d(TAG, "setPipscale()" + "start");
        if (firstSetPipscale) {
            setPipscale(1);
            firstSetPipscale = false;
            return;
        }
        try {
            int width = 1280;//dp
            int heigh = 720;//dp
            int dpi=160;
            VideoWindowType videoWindowType = new VideoWindowType();
            screenname = TvManager.getInstance().getSystemPanelName();
            try {
                int screenWidth = TvManager.getInstance().getPanelIniInfo("panel:m_wPanelWidth");//px
                int screenHeight = TvManager.getInstance().getPanelIniInfo("panel:m_wPanelHeight");//px
                float fx = (screenWidth/(densityDpiX/dpi))/ width;
                float fy = (screenHeight/(densityDpiY/dpi)) / heigh;
                videoWindowType.x = (int) (getResources().getInteger(R.integer.videoWindowType_dimen_x) * fx * (densityDpiX/dpi) + 0.5f);
                videoWindowType.y = (int) (getResources().getInteger(R.integer.videoWindowType_dimen_y) * fy * (densityDpiY/dpi) + 0.5f);
                videoWindowType.height = (int) (getResources().getInteger(R.integer.videoWindowType_dimen_height) * fy * (densityDpiX/dpi) + 0.5f);
                videoWindowType.width = (int) (getResources().getInteger(R.integer.videoWindowType_dimen_width) * fx * (densityDpiY/dpi) + 0.5f);
                Log.d(TAG,"screenWidth:"+screenWidth+"screenHeight:"+screenHeight+"fx:"+fx+"fy:"+fy);
            } catch (TvCommonException e) {
                //TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (TvManager.getInstance() != null) {
                if (TvManager.getInstance().getPictureManager() != null) {
                    TvManager.getInstance().getPictureManager()
                            .selectWindow(EnumScalerWindow.E_MAIN_WINDOW);
                }
            }
            if (TvManager.getInstance() != null) {
                if (TvManager.getInstance().getPictureManager() != null) {
                    Log.i(TAG, " videoWindowType x : " + videoWindowType.x
                            + "   y : " + videoWindowType.y + "       w : "
                            + videoWindowType.width + "       h : "
                            + videoWindowType.height );
                    TvManager.getInstance().getPictureManager()
                            .setDisplayWindow(videoWindowType);
                }
            }
            if (TvManager.getInstance() != null) {
                if (TvManager.getInstance().getPictureManager() != null) {

                    TvManager.getInstance().getPictureManager().scaleWindow();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(TAG, "***************>>>>>>>>>>>>>setPipscale");
    }

    private void setPipscale(int x) {
        Log.d(TAG, "setPipscale(int x)" + "start");
        try {
            VideoWindowType videoWindowType = new VideoWindowType();
            /*
             * int[] location = new int[2];
			 * No_Signal_textview.getLocationOnScreen(location);
			 * videoWindowType.x = location[0];// x 529 videoWindowType.y =
			 * location[1];// y 321 videoWindowType.height =
			 * No_Signal_textview.getHeight();// videoWindowType.width =
			 * No_Signal_textview.getWidth();//
			 */
            WindowManager wm = (WindowManager) this
                    .getSystemService(Context.WINDOW_SERVICE);
            videoWindowType.x = 0xffff;
            videoWindowType.y = 0xffff;
            //			videoWindowType.height =wm.getDefaultDisplay().getHeight();;
            //			videoWindowType.width = wm.getDefaultDisplay().getWidth();;
            videoWindowType.height = 0xffff;
            videoWindowType.width = 0xffff;

            if (TvManager.getInstance() != null) {
                if (TvManager.getInstance().getPictureManager() != null) {
                    TvManager.getInstance().getPictureManager()
                            .selectWindow(EnumScalerWindow.E_MAIN_WINDOW);
                }
            }
            if (TvManager.getInstance() != null) {
                if (TvManager.getInstance().getPictureManager() != null) {
                    Log.i(TAG, " videoWindowType x : " + videoWindowType.x
                            + "   y : " + videoWindowType.y + "       w : "
                            + videoWindowType.width + "       h : "
                            + videoWindowType.height);
                    TvManager.getInstance().getPictureManager()
                            .setDisplayWindow(videoWindowType);
                }
            }
            if (TvManager.getInstance() != null) {
                if (TvManager.getInstance().getPictureManager() != null) {
                    TvManager.getInstance().getPictureManager().scaleWindow();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(TAG, "***************>>>>>>>>>>>>>setPipscale");
    }

    private class MyTask extends AsyncTask<Object, Void, Void> {
        String a = "";
        int tvSourceType = -1;

        public int getTvSourceType() {
            return tvSourceType;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (tvTimeTask != null) {
                tvTimeTask.cancel();
            }
        }

        @Override
        protected Void doInBackground(Object... params) {
            a = "" + params[2];
            tvSourceType = (Integer) params[0];


            Log.d(TAG, "doInBackground" + "start=================" + (Integer) params[0] + (Integer) params[1] + a);
            startSourceChange(tvSourceType);
            handlertv.postDelayed(handlerRuntv, 300);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            TV_inputSourceType.setText(a);
            No_Signal_textview.setVisibility(View.GONE);
            No_Signal_textview.setText(getString(R.string.no_signal));
            Log.d(TAG, "onPostExecute" + "start");
            tvTimeTask = new TimerTask() {
                @Override
                public void run() {
                    boolean b = TvCommonManager.getInstance().isSignalStable(
                            tvSourceType);
                    if (b) {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                No_Signal_textview.setVisibility(View.GONE);
                            }
                        });
                    } else {
                        if (tvSourceType != TvCommonManager.INPUT_SOURCE_ATV) {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    No_Signal_textview
                                            .setVisibility(View.VISIBLE);
                                }
                            });
                        }
                    }
                }
            };
            mTVtimet.schedule(tvTimeTask, 0, 1000);
        }
    }

    public void startSourceChange(final int inputsource) {
        Log.d(TAG, "startSourceChange(" + "  start");
        new Thread(new Runnable() {
            @Override
            public void run() {

                if ((inputsource >= 0)
                        && (inputsource <= TvCommonManager.INPUT_SOURCE_NONE)) {
                    Log.v(TAG, "setInputSource=====" + inputsource);
                    TvCommonManager.getInstance().setInputSource(inputsource);

                    if (inputsource == TvCommonManager.INPUT_SOURCE_ATV) {
                        int channel = TvChannelManager.getInstance()
                                .getCurrentChannelNumber();
                        if ((channel < 0) || (channel > 255)) {
                            channel = 0;
                        }
                        TvChannelManager.getInstance().setAtvChannel(channel);
                    } else if (inputsource == TvCommonManager.INPUT_SOURCE_DTV) {
                        TvChannelManager.getInstance().changeToFirstService(
                                EnumFirstServiceInputType.E_FIRST_SERVICE_DTV,
                                EnumFirstServiceType.E_DEFAULT);
                    }

                }


                /**  TvCommonManager.getInstance().setInputSource(inputsource); if
                 (inputsource == TvCommonManager.INPUT_SOURCE_ATV) { if
                 (TvCommonManager.getInstance().getCurrentTvSystem() ==
                 TvCommonManager.TV_SYSTEM_ISDB) {
                 TvIsdbChannelManager.getInstance() .genMixProgList(false); }
                 int curChannelNumber = TvChannelManager.getInstance()
                 .getCurrentChannelNumber(); if (curChannelNumber > 0xFF) {
                 curChannelNumber = 0; }
                 TvChannelManager.getInstance().setAtvChannel(
                 curChannelNumber); } else if (inputsource ==
                 TvCommonManager.INPUT_SOURCE_DTV) { if
                 (TvCommonManager.getInstance().getCurrentTvSystem() ==
                 TvCommonManager.TV_SYSTEM_ISDB) {
                 TvIsdbChannelManager.getInstance().setAntennaType(
                 TvIsdbChannelManager.DTV_ANTENNA_TYPE_AIR); }
                 TvChannelManager.getInstance().playDtvCurrentProgram(); //
                 //  TODO VGA //
                 } else if (inputsource ==
                 TvCommonManager.INPUT_SOURCE_VGA  && VGAnumber > 1) { // //
                 TvCommonManager.getInstance().setVGAStatus(VGA_position); }
                 */
            }
        }).start();
    }

    public void backHomeSource() {
        int mIsCurCource = TvCommonManager.getInstance()
                .getCurrentTvInputSource();
        Log.v(TAG, "mIsCurCource=====" + mIsCurCource);

        if ((mIsCurCource >= 0)
                && (mIsCurCource <= TvCommonManager.INPUT_SOURCE_NONE)) {
            if (mIsCurCource >= TvCommonManager.INPUT_SOURCE_STORAGE) {
                toChangeInputSource = TvCommonManager.getInstance()
                        .getPowerOnSource().ordinal();
                Log.v(TAG, "minidatabase=====" + toChangeInputSource);
            } else {
                toChangeInputSource = mIsCurCource;
                // Log.v(TAG, "other source=====" + toChangeInputSource);
            }
            Log.i(TAG, "<<<<--backHomeSource()-->>>>>:" + toChangeInputSource);


            MyTask myTask = new MyTask();
            myTask.execute(toChangeInputSource, null,
                    inputname());
            inputSource = myTask.getTvSourceType();


        }
    }

    private String inputname() {
        String[] tmpData = this.getResources().getStringArray(
                R.array.str_arr_input_source_vals);
        Log.d(TAG, "TvCommonManager.getInstance().getCurrentTvInputSource()" + TvCommonManager.getInstance().getCurrentTvInputSource());
        if (toChangeInputSource == TvCommonManager.INPUT_SOURCE_VGA) {
//            if(TvCommonManager.getInstance().getVGAStatus()==0){
//                return tmpData[toChangeInputSource];
//            }
//            if(TvCommonManager.getInstance().getVGAStatus()==1){
//                return "VGA2";
//            }
//            if(TvCommonManager.getInstance().getVGAStatus()==2){
//                return "VGA3";
//            }
        }
        if (toChangeInputSource == TvCommonManager.INPUT_SOURCE_HDMI3) {
//            if (TvCommonManager.getInstance().getHDMIStatus() == 0) {
//                return "HDMI3";
//            }
//            if (TvCommonManager.getInstance().getHDMIStatus() == 1) {
//                return tmpData[toChangeInputSource];
//            }

        }
        if (toChangeInputSource == TvCommonManager.INPUT_SOURCE_HDMI2) {
//            if (TvCommonManager.getInstance().getHDMIStatus() == 0) {
//                return tmpData[toChangeInputSource];
//            }
//            if (TvCommonManager.getInstance().getHDMIStatus() == 1) {
//                return "DP";
//            }


        }


        return tmpData[toChangeInputSource];
    }

    private void strResumeToTvPlayer() {
        String source = (String) SPUtils.get(this,
                ResumeToSourceGlobal.STR_RESUME_TO_SOURCE,
                ResumeToSourceGlobal.STR_RESUME_TO_HOME);
        Log.d(TAG, "strResumeToTvPlayer()-->>source: " + source);
        if (source.equals(ResumeToSourceGlobal.STR_RESUME_TO_TV)
                && (PowerOnstate)) {
            PowerOnstate = false;
            isLauncherJumpToMTvPlayer();
        } else {
            PowerOnstate = false;
        }
    }
}
