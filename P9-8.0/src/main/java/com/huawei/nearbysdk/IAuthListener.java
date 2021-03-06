package com.huawei.nearbysdk;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IAuthListener extends IInterface {

    public static abstract class Stub extends Binder implements IAuthListener {
        private static final String DESCRIPTOR = "com.huawei.nearbysdk.IAuthListener";
        static final int TRANSACTION_onAuthentificationResult = 1;

        private static class Proxy implements IAuthListener {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public void onAuthentificationResult(long authId, boolean result, byte[] sessionKey, byte[] sessionKeyIV, byte[] rsa_bytes, NearbyDevice device) throws RemoteException {
                int i = 1;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(authId);
                    if (!result) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    _data.writeByteArray(sessionKey);
                    _data.writeByteArray(sessionKeyIV);
                    _data.writeByteArray(rsa_bytes);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(1, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAuthListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IAuthListener)) {
                return new Proxy(obj);
            }
            return (IAuthListener) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    NearbyDevice _arg5;
                    data.enforceInterface(DESCRIPTOR);
                    long _arg0 = data.readLong();
                    boolean _arg1 = data.readInt() != 0;
                    byte[] _arg2 = data.createByteArray();
                    byte[] _arg3 = data.createByteArray();
                    byte[] _arg4 = data.createByteArray();
                    if (data.readInt() != 0) {
                        _arg5 = (NearbyDevice) NearbyDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg5 = null;
                    }
                    onAuthentificationResult(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void onAuthentificationResult(long j, boolean z, byte[] bArr, byte[] bArr2, byte[] bArr3, NearbyDevice nearbyDevice) throws RemoteException;
}
