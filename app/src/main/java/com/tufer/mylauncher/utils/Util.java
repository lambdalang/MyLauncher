package com.tufer.mylauncher.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.Handler;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.util.Log;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.tufer.mylauncher.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by Administrator on 2017/9/21 0021.
 */

public class Util {

    public final static String getCity = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=";
    public final static String worldWeatherUrl = "http://apis.baidu.com/heweather/weather/free?city=";
    public final static String worldWeatherUrlw = "http://wthrcdn.etouch.cn/weather_mini?city=";
    public final static String worldWeatherUrlz = "https://api.thinkpage.cn/v3/weather/daily.json?key=8egfcibu0jpnkpje&location=";
    public final static String weatherUrl = "http://www.sojson.com/open/api/weather/json.shtml?city=";
    public final static String bbcUrl = "http://www.bbc.com/";
    public final static String bbcUrlFirst = "http://www.bbc.com/sport/tennis/35319202";

    private static HttpUtils httpUtils;
    public static String city = "深圳";
    public static String weatherType;
    public static String low;
    public static String high;

    public static void getCity(final Handler handler) {
        httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, getCity,
                new RequestCallBack<String>() {
                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        handler.sendEmptyMessage(MainActivity.NETWORK_ERROR);
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {
                        String result = arg0.result;
                        if (!result.contains("<")) {
                            try {
                                if (myJsons(result) != null) {
                                    city = myJsons(result).getString("city");
                                    handler.sendEmptyMessage(MainActivity.UPDATA_CITY);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    public static void getWeather(final String strCity, final Handler handler) {
        httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET,
                worldWeatherUrlw + strCity,
                new RequestCallBack<String>() {
                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        handler.sendEmptyMessage(MainActivity.NETWORK_ERROR);
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {
                        String result = arg0.result;
                        JSONObject isgetweter = myJson1(result);
                        if (isgetweter != null) {
                            handler.sendEmptyMessage(MainActivity.UPDATA_WEATHER);
                        } else {
                            httpUtils.send(HttpRequest.HttpMethod.GET,
                                    weatherUrl + city,
                                    new RequestCallBack<String>() {
                                        @Override
                                        public void onFailure(HttpException arg0, String arg1) {
                                            //ToastUtils.showShort(WeaterActivity.this, "network error ! ");
                                            handler.sendEmptyMessage(MainActivity.NETWORK_ERROR);
                                        }

                                        @Override
                                        public void onSuccess(ResponseInfo<String> arg0) {
                                            String result = arg0.result;
                                            JSONObject isgetweter = myJson2(result);
                                            if (isgetweter != null) {
                                                handler.sendEmptyMessage(MainActivity.UPDATA_WEATHER);
                                            } else {
                                                handler.sendEmptyMessage(2);
                                            }
                                        }
                                    });
                        }
                    }
                });
    }

    public static JSONObject myJsons(String responseString) {
        JSONObject json = null;
        if (responseString != null) {
            try {
                json = new JSONObject(responseString);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            return null;
        }
        return json;
    }

    public static JSONObject myJson1(String responseString) {
        JSONObject json = null;
        if (responseString != null) {
            try {
                json = new JSONObject(responseString);
                String isdescok = json.getString("desc");
                if (isdescok.equals("OK")) {
//                    if(WeatherInfoList.size()>0){
//                        WeatherInfoList=new ArrayList<ForecastWeatherInfo>();
//                    }
                    JSONObject reson = json.getJSONObject("data");
//                    city=reson.getString("city");
//                    tmp=reson.getString("wendu");
//                    ganmao=reson.getString("ganmao");
//					pm25=reson.getDouble("pm25");
//					pm10=reson.getDouble("pm10");
//					quality=reson.getString("quality");
                    JSONObject forecast;
                    forecast = reson.getJSONArray("forecast").getJSONObject(0);
                    if (forecast.getString("high") != null) {
                        weatherType = forecast.getString("type");
                        low = forecast.getString("low");
                        high = forecast.getString("high");
//                            weatherInfo.setDate(forecast.getString("date"));
//                            weatherInfo.setHigh(forecast.getString("high"));
////                            String str=forecast.getString("fengli");
//                            weatherInfo.setFengli(str.substring(9,str.length()-3));
//                            weatherInfo.setLow(forecast.getString("low"));
//                            weatherInfo.setType(forecast.getString("type"));
//                            weatherInfo.setFengxiang(forecast.getString("fengxiang"));
////							weatherInfo.setNotice(forecast.getString("notice"));
                    }
//                    for(int i=0;i<5;i++){
////                        ForecastWeatherInfo weatherInfo=new ForecastWeatherInfo();
//
////                        WeatherInfoList.add(weatherInfo);
//                    }
                } else {
                    return null;
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return json;
    }

    public static JSONObject myJson2(String responseString) {
        JSONObject json = null;
        if (responseString != null) {
            try {
                json = new JSONObject(responseString);
                String isdescok = json.getString("message");
                if (isdescok.equals("Success !")) {
//                    if(WeatherInfoList.size()>0){
//                        WeatherInfoList=new ArrayList<ForecastWeatherInfo>();
//                    }
//                    city=json.getString("city");
                    JSONObject reson = json.getJSONObject("data");
//                    tmp=reson.getString("wendu");
//                    ganmao=reson.getString("ganmao");
//                    pm25=reson.getDouble("pm25");
//                    pm10=reson.getDouble("pm10");
//                    quality=reson.getString("quality");
                    JSONObject forecast;
                    forecast = reson.getJSONArray("forecast").getJSONObject(0);
                    if (forecast.getString("high") != null) {
                        weatherType = forecast.getString("type");
                        low = forecast.getString("low");
                        high = forecast.getString("high");
//                            weatherInfo.setDate(forecast.getString("date"));
//                            weatherInfo.setHigh(forecast.getString("high"));
////                            String str=forecast.getString("fengli");
//                            weatherInfo.setFengli(str.substring(9,str.length()-3));
//                            weatherInfo.setLow(forecast.getString("low"));
//                            weatherInfo.setType(forecast.getString("type"));
//                            weatherInfo.setFengxiang(forecast.getString("fengxiang"));
////							weatherInfo.setNotice(forecast.getString("notice"));
                    }
                } else {
                    return null;
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return json;
    }


    public static final int NETWORN_NONE = 0;
    public static final int NETWORN_WIFI = 1;
    public static final int NETWORN_MOBILE = 2;
    public static final int NETWORN_ETHERNET = 3;

    public static int getNetworkState(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // Wifi
        NetworkInfo.State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState();
        if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
            return NETWORN_WIFI;
        }
//        // 3G
//        state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
//                .getState();
//        if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
//            return NETWORN_MOBILE;
//        }
        // 以太网
        state = connManager.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET)
                .getState();
        if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
            return NETWORN_ETHERNET;
        }
        return NETWORN_NONE;
    }

    public static List<ResolveInfo> getlistApp(Context context){
        PackageManager packageManager = context.getPackageManager();
        Intent mIntent = new Intent(Intent.ACTION_MAIN, null);
        mIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> listApps = packageManager.queryIntentActivities(
                mIntent, 0);
        Iterator<ResolveInfo> iterator = listApps.iterator();
        while (iterator.hasNext()) {
            ResolveInfo info = iterator.next();
            String name = info.activityInfo.applicationInfo.packageName;
            if (name.equals("com.android.contacts")
                    || name.equals("com.szada.mjylauncher")
                //				|| name.equals("com.android.settings")
                //				|| name.equals("com.ada.mylauncher")
                //	|| name.equals("com.android.wididemo")
                //|| name.equals("com.estrongs.android.pop")
                //				|| name.equals("com.android.quicksearchbox")
                    ) {
                iterator.remove();
            }
        }
        final List<ResolveInfo> listApp = listApps;
        return listApp;
    }

//    private boolean isSDMounted(Context context) {
//        boolean isMounted = false;
//        StorageManager sm = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
//
//        try {
//            Method getVolumList = StorageManager.class.getMethod("getVolumeList", null);
//            getVolumList.setAccessible(true);
//            Object[] results = (Object[])getVolumList.invoke(sm, null);
//            if (results != null) {
//                for (Object result : results) {
//                    Method mRemoveable = result.getClass().getMethod("isRemovable", null);
//                    Boolean isRemovable = (Boolean) mRemoveable.invoke(result, null);
//                    if (isRemovable) {
//                        Method getPath = result.getClass().getMethod("getPath", null);
//                        String path = (String) mRemoveable.invoke(result, null);
//                        Method getState = sm.getClass().getMethod("getVolumeState", String.class);
//                        String state = (String)getState.invoke(sm, path);
//                        if (state.equals(Environment.MEDIA_MOUNTED)) {
//                            isMounted = true;
//                            break;
//                        }
//                    }
//                }
//            }
//        } catch (NoSuchMethodException e){
//            e.printStackTrace();
//        } catch (IllegalAccessException e){
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//
//        return isMounted;
//    }


}
