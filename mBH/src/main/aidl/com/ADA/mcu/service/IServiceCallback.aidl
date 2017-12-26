// IServiceCallback.aidl
package com.ADA.mcu.service;

import com.ADA.mcu.service.ServiceRepliedData;
import com.ADA.mcu.service.SettingData;
// Declare any non-default types here with import statements

interface IServiceCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     void responseToUi(in ServiceRepliedData data);
     void sendSettingData(in SettingData data);
}
