// IContactUi.aidl
package com.ADA.mcu.service;

import com.ADA.mcu.service.MachineData;
import com.ADA.mcu.service.ProgramData;
import com.ADA.mcu.service.SettingData;
import com.ADA.mcu.service.IServiceCallback;
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
