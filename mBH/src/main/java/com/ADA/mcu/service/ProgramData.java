package com.ADA.mcu.service;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ada on 2015/6/25.
 */
public class ProgramData implements Parcelable {
    private int programIndex;
    private int weight;
    private int timeCount;
    private int age;
    private boolean enable;
    private int goal;
    private final static ProgramData mProgramData = new ProgramData();
    
    public int[][] programSpeedArray = {
            {30, 30, 30, 50, 50, 50, 70, 70, 70, 90, 90, 90, 90, 30, 30, 30, 30, 50, 50, 50},
            {30, 30, 40, 50, 60, 70, 70, 60, 50, 40, 40, 50, 60, 70, 70, 60, 50, 40, 30, 30},
            {30, 30, 30, 70, 70, 70, 40, 40, 40, 70, 70, 70, 40, 40, 40, 70, 70, 70, 30, 30},
            {30, 30, 50, 70, 90, 90, 70, 50, 70, 90, 90, 70, 50, 70, 90, 90, 70, 50, 30, 30},
            {30, 30, 30, 90, 90, 50, 50, 90, 90, 50, 50, 90, 90, 50, 50, 90, 90, 30, 30, 30},
            {30, 30, 40, 40, 50, 50, 60, 60, 70, 70, 70, 70, 60, 60, 50, 50, 40, 40, 30, 30},
            {30, 30, 30, 40, 50, 60 ,70, 80, 80, 80, 80, 80, 80 ,70, 60, 50, 40, 30, 30, 30}
    };

    public int[][] programInclineArray = {
            {0, 0, 0, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 0, 0, 0, 0, 2, 2, 2},
            {0, 0, 1, 2, 3, 4, 4, 3, 2, 1, 1, 2, 3, 4, 4, 3, 2, 1, 0, 0},
            {0, 0, 0, 4, 4, 4, 1, 1, 1, 4, 4, 4, 1, 1, 1, 4, 4, 4, 0, 0},
            {0, 0, 2, 4, 6, 6, 4, 2, 4, 6, 6, 4, 2, 4, 6, 6, 4, 2, 0, 0},
            {0, 0, 0, 6, 6, 2, 2, 6, 6, 2, 2, 6, 6, 2, 2, 6, 6, 0, 0, 0},
            {0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 4, 4, 3, 3, 2, 2, 1, 1, 0, 0},
            {0, 0, 0, 1, 2, 3, 4, 5, 5, 5, 5, 5, 5, 4, 3, 2, 1, 0, 0, 0}
    };

    private ProgramData() {
    }

    public static ProgramData getInstance() {
        return mProgramData;
    }

    public static final Creator<ProgramData> CREATOR
            = new Creator<ProgramData>() {

        @Override
        public ProgramData createFromParcel(Parcel source) {
            mProgramData.readFromParcel(source);
            return mProgramData;
        }

        @Override
        public ProgramData[] newArray(int size) {
            return new ProgramData[size];
        }
    };

    private void readFromParcel(Parcel in) {
        programIndex = in.readInt();
        weight = in.readInt();
        timeCount = in.readInt();
        age = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(programIndex);
        dest.writeInt(weight);
        dest.writeInt(timeCount);
        dest.writeInt(age);
        dest.writeInt(goal);
    }

    public void setProgramData(ProgramData data) {
    	this.programIndex = data.getProgramIndex();
        this.weight = data.getWeight();
        this.timeCount = data.getTimeCount();
        this.age = data.getAge();
    }

    public int getProgramIndex() {
        return programIndex;
    }

    public void setProgramIndex(int programIndex) {
        this.programIndex = programIndex;
        if(programIndex > 0) enable = true;
        
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getTimeCount() {
        return timeCount;
    }
    
    public void setAge(int age ){
    	this.age = age;
    }
    
    public int getAge(){
    	return age;
    }

    public void setTimeCount(int time) {
        this.timeCount = time * 60;
    }
    
    public boolean isEnable() {
    	return enable;
    }
    
    public void setEnable(boolean enable) {
    	this.enable = enable;
    }
    
    public int getGoal() {
    	return goal;
    }
    
    public void setGoal(int goal) {
    	this.goal = goal;
    }

	public void reset() {
		// TODO Auto-generated method stub
		programIndex = 0;
	    weight = 0;
	    timeCount = 0;
	    age = 0;
	    goal = 0;
	}
}
