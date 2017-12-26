package com.ada.jw;

import android.app.Application;



/**
 * Created by ada on 2015/5/30.
 */
public class AppContext extends Application{
    private setActivity mBaseActivity;


    @Override
    public void onCreate() {
        super.onCreate();
//        c = new CrashHandler() {
//
//			@Override
//			public void uncaughtException(Thread thread, Throwable ex) {
//				// TODO Auto-generated method stub
//				mBaseActivity.exitService();
//				super.uncaughtException(thread, ex);
//			}
//        	
//        };
//        c.init(this);
    }
/*
    public void addActivity(Activity activity)  {
        activityList.add(activity);
    }

    public void exit(){
        for(Activity activity:activityList) {
            activity.finish();
        }
        System.exit(0);
    }
*/
    public void setThisInstance(setActivity mBaseActivity) {
        this.mBaseActivity = mBaseActivity;
    }

    public setActivity getBaseActivityInstance() {
        return mBaseActivity;
    }
}
