package com.qg.kinectpatient.emsdk;

/**
 * Created by HWF on 2018/5/13..
 */
public class MediaExectutor {
    private static final String TAG = MediaExectutor.class.getSimpleName();
    private MediaPlayWorker mpWorker;
    private MediaRecordWorker mrWorker;


    private MediaExectutor(){
        mpWorker = new MediaPlayWorker();
        mrWorker = new MediaRecordWorker();
        mpWorker.start();
        mrWorker.start();
    }

    private static MediaExectutor instance;

    public static MediaExectutor getInstance(){
        if(instance == null){
            synchronized (MediaExectutor.class){
                if(instance == null){
                    instance = new MediaExectutor();
                }
            }
        }
        return instance;
    }

    public void executePlayTask(PlayTask task){
        mpWorker.exectute(task);
    }

    public void executeRecordTask(RecordTask task){
        mrWorker.exectute(task);
    }

    public void setPlayStatusChangedListener(MediaPlayWorker.PlayStatusChangedListener listener){
        if(mpWorker != null){
            mpWorker.setPlayStatusChangedListener(listener);
        }
    }

    public MediaExectutor setMediaRecordListener(MediaRecordWorker.MediaRecordListener listener){
        if(mrWorker != null){
            mrWorker.setMediaRecordListener(listener);
        }
        return this;
    }




}
