package com.qg.kinectpatient.emsdk;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by HWF on 2018/5/13..
 */
public class BaseWorker<T> extends Thread{
    protected Handler handler;
    protected BlockingQueue<T> mQueue;

    public BaseWorker(Looper looper){
        handler = new Handler(looper);
        mQueue = new LinkedBlockingQueue<>();
    }

    protected void exectute(T task){
        try {
            mQueue.add(task);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("当前网络状况差，检查您的网络");
        }
    };

    public void onDestroy(){};
}
