package com.ada.button;

import com.ada.mcu.service.IContactUi;
import com.ada.mcu.service.MachineData;

/**
 * Created by ada on 2015/6/25.
 */
public class KeypadControl {

    private IContactUi.Stub mBinder;
    private MachineData mMachinData;
    private enum KEYPAD_KEYCODE {
        KEYCODE_PBJ_SP_ADD,
        KEYCODE_PBJ_SP_DEC,
        KEYCODE_PBJ_INC_ADD,
        KEYCODE_PBJ_INC_DEC,
        KEYCODE_PBJ_START,
        KEYCODE_PBJ_STOP,
        KEYCODE_PBJ_ENTER,
        KEYCODE_PBJ_RESET,
        KEYCODE_PBJ_PROGRAM,
        KEYCODE_PBJ_PROGRAM_ADD,
        KEYCODE_PBJ_PROGRAM_DEC,
        KEYCODE_PBJ_FAN,
        KEYCODE_PBJ_MODE,
        KEYCODE_PBJ_ADJUST,
        KEYCODE_PBJ_FACTORY,
        KEYCODE_PBJ_SEARCH,
        KEYCODE_PBJ_SCREEN,
        KEYCODE_PBJ_START_STOP,
        KEYCODE_PBJ_SET_ADD,
        KEYCODE_PBJ_SET_DEC,
        KEYCODE_PBJ_SPEED_4,
        KEYCODE_PBJ_SPEED_8,
        KEYCODE_PBJ_SPEED_12,
        KEYCODE_PBJ_SPEED_16,
        KEYCODE_PBJ_SPEED_3,
        KEYCODE_PBJ_SPEED_6,
        KEYCODE_PBJ_SPEED_9,
        KEYCODE_PBJ_SPEED_15,
        KEYCODE_PBJ_SPEED_2,
        KEYCODE_PBJ_SPEED_10,
        KEYCODE_PBJ_INCLINE_2,
        KEYCODE_PBJ_INCLINE_5,
        KEYCODE_PBJ_INCLINE_8,
        KEYCODE_PBJ_INCLINE_12,
        KEYCODE_PBJ_INCLINE_3,
        KEYCODE_PBJ_INCLINE_6,
        KEYCODE_PBJ_INCLINE_9,
        KEYCODE_PBJ_INCLINE_15,
        KEYCODE_PBJ_INCLINE_4,
        KEYCODE_PBJ_INCLINE_10,
        KEYCODE_PBJ_SPEED_OK,
        KEYCODE_PBJ_INCLINE_OK,
        KEYCODE_PBJ_INCLINE_16
    }

    public void onKeypadPress(int keyCode) {
        switch(KEYPAD_KEYCODE.values()[keyCode - 5000]) {
            case KEYCODE_PBJ_SP_ADD: {
                int speed = mMachinData.getOperatingFrequency() + 10;
                if (speed <= 250) {
                    mMachinData.setOperatingFrequency(speed);
                }
                break;
            }
            case KEYCODE_PBJ_SP_DEC: {
                int speed = mMachinData.getOperatingFrequency() - 10;
                if(speed > 0) {
                    mMachinData.setOperatingFrequency(speed);
                }
                break;
            }
            case KEYCODE_PBJ_INC_ADD: {
                int incline = mMachinData.getIncline() + 1;
                if(incline <= mMachinData.getCurrentPosition()) {
                    mMachinData.setIncline(incline);
                }
                break;
            }
            case KEYCODE_PBJ_INC_DEC: {
                int incline = mMachinData.getIncline() - 1;
                if(incline > 0) {
                    mMachinData.setIncline(incline);
                }
                break;
            }
            case KEYCODE_PBJ_START:
                if(mMachinData.getCurrentStatus() != 1) {
                    if(mMachinData.getCurrentStatus() == 2) {
                        mMachinData.setCurrentStatus(1);
                    }else {
                        mMachinData.setInitRunDatas();
                    }
                }
                break;
            case KEYCODE_PBJ_STOP:
                if(mMachinData.getCurrentStatus() != 0) {
                    mMachinData.setCurrentStatus(0);
                }
                break;
            case KEYCODE_PBJ_RESET:
                break;
            case KEYCODE_PBJ_PROGRAM:
                break;
            case KEYCODE_PBJ_PROGRAM_ADD:
                break;
            case KEYCODE_PBJ_PROGRAM_DEC:
                break;
            case KEYCODE_PBJ_FAN:
                break;
            case KEYCODE_PBJ_MODE:
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
                mMachinData.setCurrentStatus(40);
                break;
            case KEYCODE_PBJ_SPEED_8:
                mMachinData.setCurrentStatus(80);
                break;
            case KEYCODE_PBJ_SPEED_12:
                mMachinData.setCurrentStatus(120);
                break;
            case KEYCODE_PBJ_SPEED_16:
                mMachinData.setCurrentStatus(160);
                break;
            case KEYCODE_PBJ_SPEED_3:
                mMachinData.setCurrentStatus(30);
                break;
            case KEYCODE_PBJ_SPEED_6:
                mMachinData.setCurrentStatus(60);
                break;
            case KEYCODE_PBJ_SPEED_9:
                mMachinData.setCurrentStatus(90);
                break;
            case KEYCODE_PBJ_SPEED_15:
                mMachinData.setCurrentStatus(150);
                break;
            case KEYCODE_PBJ_SPEED_2:
                mMachinData.setCurrentStatus(20);
                break;
            case KEYCODE_PBJ_SPEED_10:
                mMachinData.setCurrentStatus(100);
                break;
            case KEYCODE_PBJ_INCLINE_2:
                mMachinData.setIncline(2);
                break;
            case KEYCODE_PBJ_INCLINE_5:
                mMachinData.setIncline(5);
                break;
            case KEYCODE_PBJ_INCLINE_8:
                mMachinData.setIncline(8);
                break;
            case KEYCODE_PBJ_INCLINE_12:
                mMachinData.setIncline(12);
                break;
            case KEYCODE_PBJ_INCLINE_3:
                mMachinData.setIncline(3);
                break;
            case KEYCODE_PBJ_INCLINE_6:
                mMachinData.setIncline(6);
                break;
            case KEYCODE_PBJ_INCLINE_9:
                mMachinData.setIncline(9);
                break;
            case KEYCODE_PBJ_INCLINE_15:
                mMachinData.setIncline(15);
                break;
            case KEYCODE_PBJ_INCLINE_4:
                mMachinData.setIncline(4);
                break;
            case KEYCODE_PBJ_INCLINE_10:
                mMachinData.setIncline(10);
                break;
            case KEYCODE_PBJ_SPEED_OK:
                break;
            case KEYCODE_PBJ_INCLINE_OK:
                break;
            case KEYCODE_PBJ_INCLINE_16:
                mMachinData.setIncline(16);
                break;
            default:
                break;
        }
      
    }
}
