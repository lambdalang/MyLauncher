package com.ada.mcu.service;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ada on 2015/5/25.
 */
public class MachineData implements Parcelable {
    public int currentStatus = 0;
    private int operatingFrequency = 0;
    private int incline = 0;
    private int currentPosition = 20;
    private int blowingRate = 0;
    private int accelerationTime = 0;
    private int heartBeat = 0;
    private int speedMax = 250;
    private int inclineMax = 20;
    private float personHeight = 0.0f;
    private float personWeight = 0.0f;
    private float userHeight = 0.0f;
    private float userWeight = 0.0f;
    private long userCalorie = 0;
    private long userDistance = 0;
    private int keyCode = 0;
    private ProgramData mProgramData;

    private static MachineData mMachinData = new MachineData();

    private int[] initStandbyParameters = {
            0, 0,  0, 0, 0
    };

    private int[] initRunParameters = {
            1, 20,  0, 0, 0
    };

    private int[] initSelfCheckParameters = {
            3, 0,  0, 0, 0
    };

    public MachineData() {
        setInitStandbyDatas();
    }

    public static MachineData getInstance() {
        return mMachinData;
    }

    public void setInitStandbyDatas() {
        currentStatus = initStandbyParameters[0];
        operatingFrequency = initStandbyParameters[1];
       // currentPosition = initStandbyParameters[2];
        incline = initStandbyParameters[2];
        blowingRate = initStandbyParameters[3];
        accelerationTime = initStandbyParameters[4];
    }

    public void setInitRunDatas() {
        currentStatus = initRunParameters[0];
        operatingFrequency = initRunParameters[1];
      
        incline = initRunParameters[2];
        blowingRate = initRunParameters[3];
        accelerationTime = initRunParameters[4];
    }

    public void setInitSelfCheckDatas() {
        currentStatus = initSelfCheckParameters[0];
        operatingFrequency = initSelfCheckParameters[1];
        //currentPosition = initSelfCheckParameters[2];
        incline = initSelfCheckParameters[2];
        blowingRate = initSelfCheckParameters[3];
        accelerationTime = initSelfCheckParameters[4];
    }

    public MachineData(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(currentStatus);
        dest.writeInt(operatingFrequency);
        dest.writeInt(incline);
        dest.writeInt(blowingRate);
    }

    public void readFromParcel(Parcel in) {
        currentStatus = in.readInt();
        operatingFrequency = in.readInt();
        currentPosition = in.readInt();
        incline = in.readInt();
        blowingRate = in.readInt();
        accelerationTime = in.readInt();
        keyCode = in.readInt();
    }

    public void setMachineData(MachineData data) {
        currentStatus = data.currentStatus;
        operatingFrequency = data.operatingFrequency;
        incline = data.incline;
        currentPosition = data.currentPosition;
        blowingRate = data.blowingRate;
        accelerationTime = data.accelerationTime;
    }

    public static final Creator<MachineData> CREATOR
            = new Creator<MachineData>() {

        @Override
        public MachineData createFromParcel(Parcel source) {
            return new MachineData(source);
        }

        @Override
        public MachineData[] newArray(int size) {
            return new MachineData[size];
        }
    };

    public void setHeartBeat(int heartBeat) {
        this.heartBeat = heartBeat;
    }

    public int getheartBeat() {
        return heartBeat;
    }

    public void setOperatingFrequency(int value) {
        operatingFrequency = value;
    }

    public int getOperatingFrequency() {
        return operatingFrequency;
    }

    public void setIncline(int value) {
        incline = value;
    }

    public int getIncline() {
        return incline;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setBlowingRate(int value) {
        blowingRate = value;
    }

    public int getBlowingRate() {
        return blowingRate;
    }

    public int getAccelerationTime() {
        return accelerationTime;
    }

    public void setCurrentStatus(int value) {
        currentStatus = value;
    }

    public void setCurrentPosion(int value) {
    	currentPosition = value;
    	//currentPosition = 20;
    }
    public int getCurrentStatus() {
        return currentStatus;
    }


    public int getSpeedMax() {
        return speedMax;
    }

    public void setSpeedMax(int speedMax) {
        this.speedMax = speedMax;
    }

    public int getInclineMax() {
        return inclineMax;
    }

    public void setInclineMax(int inclineMax) {
        this.inclineMax = inclineMax;
    }


    public float getPersonHeight() {
        return personHeight;
    }

    public void setPersonHeight(float personHeight) {
        this.personHeight = personHeight;
    }

    public float getPersonWeight() {
        return personWeight;
    }

    public void setPersonWeight(float personWeight) {
        this.personWeight = personWeight;
    }

    public float getUserHeight() {
        return userHeight;
    }

    public void setUserHeight(float userHeight) {
        this.userHeight = userHeight;
    }

    public float getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(float userWeight) {
        this.userWeight = userWeight;
    }

    public long getUserCalorie() {
        return userCalorie;
    }

    public void setUserCalorie(long userCalorie) {
        this.userCalorie = userCalorie;
    }

    public long getUserDistance() {
        return userDistance;
    }

    public void setUserDistance(long userDistance) {
        this.userDistance = userDistance;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

}
