package com.ada.mcu.service;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by ada on 2015/5/26.
 */
public class ServiceRepliedData implements Parcelable {
    private int selfCheckStatus = 0;
    private int alarmCode = 0;
    private int heartBeat = 0;
    private int oilTime = 0;
    private int miles = 0;
    private int calories = 0;
    private int timeCount = 0;
    private int executionTime = 0;
    private long executionMiles = 0;
    private final static ServiceRepliedData mServiceRepliedData = new ServiceRepliedData();
    private MachineData mMachineData = MachineData.getInstance();

    public ServiceRepliedData() {
    }

    public ServiceRepliedData(Parcel in) {
        //readFromParcel(in);
    }

    public static ServiceRepliedData getInstance() {
        return mServiceRepliedData;
    }

    public static final Creator<ServiceRepliedData> CREATOR
            = new Creator<ServiceRepliedData>() {

        @Override
        public ServiceRepliedData createFromParcel(Parcel source) {
            mServiceRepliedData.readFromParcel(source);
            return mServiceRepliedData;
        }

        @Override
        public ServiceRepliedData[] newArray(int size) {
            return new ServiceRepliedData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(selfCheckStatus);
        dest.writeInt(alarmCode);
        dest.writeInt(heartBeat);
        dest.writeInt(oilTime);
        dest.writeLong(miles);
        dest.writeLong(calories);
        dest.writeInt(timeCount);
        if(mMachineData == null)
            mMachineData = MachineData.getInstance();

        dest.writeInt(mMachineData.getOperatingFrequency());
        dest.writeInt(mMachineData.getIncline());
        dest.writeInt(mMachineData.getCurrentStatus());
    }

    public void readFromParcel(Parcel in) {
        selfCheckStatus = in.readInt();
        alarmCode = in.readInt();
        heartBeat = in.readInt();
//        oilTime = in.readInt();
        miles = in.readInt();
        calories = in.readInt();
        timeCount = in.readInt();
        executionMiles = in.readLong();
        executionTime = in.readInt();
        if(mMachineData == null)
            mMachineData = MachineData.getInstance();
        mMachineData.setOperatingFrequency(in.readInt());
        mMachineData.setIncline(in.readInt());
        mMachineData.setCurrentStatus(in.readInt());
        
        
        
//        Log.i("LYJ----", "speed:" + mMachineData.getOperatingFrequency()
//        		+ " incline:" + mMachineData.getIncline()
//        		+ " status:" + mMachineData.getCurrentStatus());
    }

    public void setMcuRepliedData(byte[] data) {
        selfCheckStatus = data[2] >> 6 & 0x3;
        alarmCode = data[2] & 0x3f;
        heartBeat = data[3];
        oilTime = (data[4] << 8) | data[5];
    }

    public int getSelfCheckStatus() {
        return selfCheckStatus;
    }

    public int getAlarmCode() {
        return alarmCode;
    }

    public int getHeartBeat() {
        return heartBeat;
    }

    public int getOilTime() {
        return oilTime;
    }

    public void setOilTime(int oilTime) {
        this.oilTime = oilTime;
    }

    public int getMiles() {
        return miles;
    }

    public void setMiles(int miles) {
        this.miles = miles;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getTimeCount() {
        return timeCount;
    }

    public void setTimeCount(int timeCount) {
        this.timeCount = timeCount;
    }
    
    public int getExecutionTime() {
        return executionTime;
    }

    public long getExecutionMiles() {
        return executionMiles;
    }

    public void reset() {
        miles = 0;
        calories = 0;
    }
}
