package android.content.pm;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IPackageMoveObserver extends IInterface {

    public static abstract class Stub extends Binder implements IPackageMoveObserver {
        private static final String DESCRIPTOR = "android.content.pm.IPackageMoveObserver";
        static final int TRANSACTION_onCreated = 1;
        static final int TRANSACTION_onStatusChanged = 2;

        private static class Proxy implements IPackageMoveObserver {
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

            public void onCreated(int moveId, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(moveId);
                    if (extras != null) {
                        _data.writeInt(Stub.TRANSACTION_onCreated);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_onCreated, _data, null, Stub.TRANSACTION_onCreated);
                } finally {
                    _data.recycle();
                }
            }

            public void onStatusChanged(int moveId, int status, long estMillis) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(moveId);
                    _data.writeInt(status);
                    _data.writeLong(estMillis);
                    this.mRemote.transact(Stub.TRANSACTION_onStatusChanged, _data, null, Stub.TRANSACTION_onCreated);
                } finally {
                    _data.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPackageMoveObserver asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IPackageMoveObserver)) {
                return new Proxy(obj);
            }
            return (IPackageMoveObserver) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case TRANSACTION_onCreated /*1*/:
                    Bundle bundle;
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0 = data.readInt();
                    if (data.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        bundle = null;
                    }
                    onCreated(_arg0, bundle);
                    return true;
                case TRANSACTION_onStatusChanged /*2*/:
                    data.enforceInterface(DESCRIPTOR);
                    onStatusChanged(data.readInt(), data.readInt(), data.readLong());
                    return true;
                case IBinder.INTERFACE_TRANSACTION /*1598968902*/:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void onCreated(int i, Bundle bundle) throws RemoteException;

    void onStatusChanged(int i, int i2, long j) throws RemoteException;
}
