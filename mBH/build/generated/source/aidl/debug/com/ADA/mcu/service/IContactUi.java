/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: F:\\Tufer_workspace\\MyLauncher\\mBH\\src\\main\\aidl\\com\\ADA\\mcu\\service\\IContactUi.aidl
 */
package com.ADA.mcu.service;
// Declare any non-default types here with import statements

public interface IContactUi extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.ADA.mcu.service.IContactUi
{
private static final java.lang.String DESCRIPTOR = "com.ADA.mcu.service.IContactUi";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.ADA.mcu.service.IContactUi interface,
 * generating a proxy if needed.
 */
public static com.ADA.mcu.service.IContactUi asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.ADA.mcu.service.IContactUi))) {
return ((com.ADA.mcu.service.IContactUi)iin);
}
return new com.ADA.mcu.service.IContactUi.Stub.Proxy(obj);
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
case TRANSACTION_updateToMcu:
{
data.enforceInterface(DESCRIPTOR);
com.ADA.mcu.service.MachineData _arg0;
if ((0!=data.readInt())) {
_arg0 = com.ADA.mcu.service.MachineData.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.updateToMcu(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_setProgramData:
{
data.enforceInterface(DESCRIPTOR);
com.ADA.mcu.service.ProgramData _arg0;
if ((0!=data.readInt())) {
_arg0 = com.ADA.mcu.service.ProgramData.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.setProgramData(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_registerCallback:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
com.ADA.mcu.service.IServiceCallback _arg1;
_arg1 = com.ADA.mcu.service.IServiceCallback.Stub.asInterface(data.readStrongBinder());
this.registerCallback(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_unregisterCallback:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
com.ADA.mcu.service.IServiceCallback _arg1;
_arg1 = com.ADA.mcu.service.IServiceCallback.Stub.asInterface(data.readStrongBinder());
this.unregisterCallback(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_setSettingData:
{
data.enforceInterface(DESCRIPTOR);
com.ADA.mcu.service.SettingData _arg0;
if ((0!=data.readInt())) {
_arg0 = com.ADA.mcu.service.SettingData.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.setSettingData(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getSettingData:
{
data.enforceInterface(DESCRIPTOR);
com.ADA.mcu.service.SettingData _result = this.getSettingData();
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_beginOilTime:
{
data.enforceInterface(DESCRIPTOR);
this.beginOilTime();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.ADA.mcu.service.IContactUi
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
@Override public void updateToMcu(com.ADA.mcu.service.MachineData data) throws android.os.RemoteException
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
mRemote.transact(Stub.TRANSACTION_updateToMcu, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void setProgramData(com.ADA.mcu.service.ProgramData data) throws android.os.RemoteException
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
mRemote.transact(Stub.TRANSACTION_setProgramData, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void registerCallback(java.lang.String packageName, com.ADA.mcu.service.IServiceCallback cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(packageName);
_data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerCallback, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void unregisterCallback(java.lang.String packageName, com.ADA.mcu.service.IServiceCallback cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(packageName);
_data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterCallback, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void setSettingData(com.ADA.mcu.service.SettingData data) throws android.os.RemoteException
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
mRemote.transact(Stub.TRANSACTION_setSettingData, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public com.ADA.mcu.service.SettingData getSettingData() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.ADA.mcu.service.SettingData _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getSettingData, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = com.ADA.mcu.service.SettingData.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void beginOilTime() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_beginOilTime, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_updateToMcu = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_setProgramData = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_registerCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_unregisterCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_setSettingData = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_getSettingData = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_beginOilTime = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
}
public void updateToMcu(com.ADA.mcu.service.MachineData data) throws android.os.RemoteException;
public void setProgramData(com.ADA.mcu.service.ProgramData data) throws android.os.RemoteException;
public void registerCallback(java.lang.String packageName, com.ADA.mcu.service.IServiceCallback cb) throws android.os.RemoteException;
public void unregisterCallback(java.lang.String packageName, com.ADA.mcu.service.IServiceCallback cb) throws android.os.RemoteException;
public void setSettingData(com.ADA.mcu.service.SettingData data) throws android.os.RemoteException;
public com.ADA.mcu.service.SettingData getSettingData() throws android.os.RemoteException;
public void beginOilTime() throws android.os.RemoteException;
}
