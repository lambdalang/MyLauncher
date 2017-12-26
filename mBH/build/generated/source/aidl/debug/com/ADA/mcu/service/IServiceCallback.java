/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: F:\\Tufer_workspace\\MyLauncher\\mBH\\src\\main\\aidl\\com\\ADA\\mcu\\service\\IServiceCallback.aidl
 */
package com.ADA.mcu.service;
// Declare any non-default types here with import statements

public interface IServiceCallback extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.ADA.mcu.service.IServiceCallback
{
private static final java.lang.String DESCRIPTOR = "com.ADA.mcu.service.IServiceCallback";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.ADA.mcu.service.IServiceCallback interface,
 * generating a proxy if needed.
 */
public static com.ADA.mcu.service.IServiceCallback asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.ADA.mcu.service.IServiceCallback))) {
return ((com.ADA.mcu.service.IServiceCallback)iin);
}
return new com.ADA.mcu.service.IServiceCallback.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_responseToUi:
{
data.enforceInterface(DESCRIPTOR);
com.ADA.mcu.service.ServiceRepliedData _arg0;
if ((0!=data.readInt())) {
_arg0 = com.ADA.mcu.service.ServiceRepliedData.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.responseToUi(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_sendSettingData:
{
data.enforceInterface(DESCRIPTOR);
com.ADA.mcu.service.SettingData _arg0;
if ((0!=data.readInt())) {
_arg0 = com.ADA.mcu.service.SettingData.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.sendSettingData(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.ADA.mcu.service.IServiceCallback
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
/**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
@Override public void responseToUi(com.ADA.mcu.service.ServiceRepliedData data) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((data!=null)) {
_data.writeInt(1);
data.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_responseToUi, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void sendSettingData(com.ADA.mcu.service.SettingData data) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((data!=null)) {
_data.writeInt(1);
data.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_sendSettingData, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_responseToUi = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_sendSettingData = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
/**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
public void responseToUi(com.ADA.mcu.service.ServiceRepliedData data) throws android.os.RemoteException;
public void sendSettingData(com.ADA.mcu.service.SettingData data) throws android.os.RemoteException;
}
