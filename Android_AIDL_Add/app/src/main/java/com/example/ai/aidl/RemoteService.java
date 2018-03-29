package com.example.ai.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by AI on 2018/3/27.
 */

/**
 * 这里是服务端,共享信息
 */
public class RemoteService extends Service{

    /**
     * 当客户端绑定到该服务的时候，会执行
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }


    private IBinder iBinder=new IRemoteService.Stub() {

        @Override
        public int add(int num1, int num2) throws RemoteException {

            Log.d("TAG", "收到了远程的请求，输入的参数是"+num1+"和"+num2);
            return num1+num2;
        }

    };
}
