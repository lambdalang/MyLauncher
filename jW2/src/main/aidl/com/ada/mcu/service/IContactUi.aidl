// IContactUi.aidl
package com.ada.mcu.service;

import com.ada.mcu.service.MachineData;
import com.ada.mcu.service.ProgramData;
import com.ada.mcu.service.SettingData;
import com.ada.mcu.service.IServiceCallback;
// Declare any non-default types here with import statements

interface IContactUi {
    void updateToMcu(in MachineData data);
    void setProgramData(in ProgramData data);
    void registerCallback(in String packageName, in IServiceCallback cb);
    void unregisterCallback(in String packageName, in IServiceCallback cb);
    void setSettingData(in SettingData data);
    SettingData getSettingData();
    void beginOilTime();
}
