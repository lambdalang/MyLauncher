// IServiceCallback.aidl
package com.ada.mcu.service;

import com.ada.mcu.service.ServiceRepliedData;
import com.ada.mcu.service.SettingData;
// Declare any non-default types here with import statements

interface IServiceCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     void responseToUi(in ServiceRepliedData data);
     void sendSettingData(in SettingData data);
}
