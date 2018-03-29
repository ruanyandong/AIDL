package com.example.aidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ai.aidl.IRemoteService;

/**
 * 操作流程
 * 1.定义AIDL文件
 * 2.实现服务端
 * 3.实现客户端
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditNum1;
    private EditText mEditNum2;
    private EditText mEditResult;

    private Button mButtonAdd;

    private IRemoteService iRemoteService;

    private ServiceConnection conn=new ServiceConnection() {

        /**
         *
         * 绑定上服务的时候执行
         *
         * @param name
         * @param service
         */

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            //拿到了远程的服务代理
            iRemoteService=IRemoteService.Stub.asInterface(service);
        }


        /**
         *
         * 断开服务的时候执行
         * @param name
         */

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //回收资源
            iRemoteService=null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvents();

        /**
         * 软件一启动就绑定服务
         */

        bindService();
    }

    private void initEvents() {
        mButtonAdd.setOnClickListener(this);
    }


    private void initView() {
        mEditNum1=findViewById(R.id.et_num1);
        mEditNum2=findViewById(R.id.et_num2);
        mEditResult=findViewById(R.id.et_res);
        mButtonAdd=findViewById(R.id.btn_add);
    }


    @Override
    public void onClick(View v) {
        int num1=Integer.valueOf(mEditNum1.getText().toString());
        int num2=Integer.valueOf(mEditNum2.getText().toString());

        try {
            /**
             * 调用远程的服务
             */
            int res=iRemoteService.add(num1,num2);
            mEditResult.setText(res+"");

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }


    //CTRAL+ALT+m 抽取代码
    private void bindService() {
        //获取到服务端
        /**
         * android在5.0后 已经不允许隐式启动
         */
        Intent intent=new Intent();

        /**
         * 第一个参数是服务端包名，第二个是包名加类名
         *

         打开内部的活动
         intent.setClass(当前组件.this, 目标组件.class);

         那如何我要打开外部应用呢？

         比如我要通过按钮点击后，打开系统闹钟

         intent.setClassName(包名, 包名+activity名);或者
         intent.setComponent(包名, 包名+activity名)

         setClassName内部也是调用的setComponent
         */
        intent.setComponent(new
                ComponentName("com.example.ai.aidl","com.example.ai.aidl.RemoteService"));

        bindService(intent,conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        /**
         * 解绑服务
         */
        unbindService(conn);
    }
}
