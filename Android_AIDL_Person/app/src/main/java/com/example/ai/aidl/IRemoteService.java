package com.example.ai.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AI on 2018/3/28.
 */

public class IRemoteService extends Service{

    private ArrayList<Person> persons;


    private IBinder iBinder=new IMyAidlInterface.Stub() {
        @Override
        public List<Person> add(Person person) throws RemoteException {
            persons.add(person);
            return persons;
        }
    };



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        persons=new ArrayList<>();
        return iBinder;
    }


}
