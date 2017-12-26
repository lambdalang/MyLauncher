package com.ADA.mcu.service;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ada on 2015/7/2.
 */
public class SettingData implements Parcelable {
    private int metricInch = 0;
    private int speedMin = 0;
    private int speedMax = 0;
    private int inclineMax = 0;
    private int oilTime = 0;
    private int oilCountMax = 0;
    private int speedRatio = 0;
    private int oilDistance = 0;
    private int distanceMax = 0;
    private int languageSelect = 0;

    public SettingData() {

    }

    public SettingData(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(metricInch);
        dest.writeInt(speedMin);
        dest.writeInt(speedMax);
        dest.writeInt(inclineMax);
        dest.writeInt(oilTime);
        dest.writeInt(oilCountMax);
        dest.writeInt(speedRatio);
        dest.writeInt(oilDistance);
        dest.writeInt(distanceMax);
        dest.writeInt(languageSelect);
    }

    private void readFromParcel(Parcel in) {
    	setMetricInch(in.readInt());
        setSpeedMin(in.readInt());
        setSpeedMax(in.readInt());
        setInclineMax(in.readInt());
        setOilTime(in.readInt());
        setOilCountMax(in.readInt());
        setSpeedRatio(in.readInt());
        setOilDistance(in.readInt());
        setDistanceMax(in.readInt());
        setLanguageSelect(in.readInt());   
    }

    public static final Creator<SettingData> CREATOR
            = new Creator<SettingData>() {

        @Override
        public SettingData createFromParcel(Parcel source) {
            SettingData data = new SettingData(source);
            return data;
        }

        @Override
        public SettingData[] newArray(int size) {
            return new SettingData[size];
        }
    };

    public int getMetricInch() {
        return metricInch;
    }

    public void setMetricInch(int metricInch) {
        this.metricInch = metricInch;
    }

    public int getSpeedMin() {
        return speedMin;
    }

    public void setSpeedMin(int speedMin) {
        this.speedMin = speedMin;
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

    public int getOilTime() {
        return oilTime;
    }

    public void setOilTime(int oilTime) {
        this.oilTime = oilTime;
    }
    
    public int getOilCountMax() {
        return oilCountMax;
    }

    public void setOilCountMax(int oilCountMax) {
        this.oilCountMax = oilCountMax;
    }

    public int getSpeedRatio() {
        return speedRatio;
    }

    public void setSpeedRatio(int speedRatio) {
        this.speedRatio = speedRatio;
    }

    public int getOilDistance() {
        return oilDistance;
    }

    public void setOilDistance(int oilDistance) {
        this.oilDistance = oilDistance;
    }

    public int getDistanceMax() {
        return distanceMax;
    }

    public void setDistanceMax(int distanceMax) {
        this.distanceMax = distanceMax;
    }

    public int getLanguageSelect() {
        return languageSelect;
    }

    public void setLanguageSelect(int languageSelect) {
        this.languageSelect = languageSelect;
    }
}
