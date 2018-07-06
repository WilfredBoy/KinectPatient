package com.qg.kinectpatient.logic;

import android.os.AsyncTask;


import com.qg.kinectpatient.http.HttpProcess;
import com.qg.kinectpatient.param.GetDUserByPhoneParam;
import com.qg.kinectpatient.param.GetMRParam;
import com.qg.kinectpatient.param.GetRcStageParam;
import com.qg.kinectpatient.param.LoginParam;
import com.qg.kinectpatient.param.Param;
import com.qg.kinectpatient.param.UpdatePUserParam;
import com.qg.kinectpatient.result.GetDUserByPhoneResult;
import com.qg.kinectpatient.result.GetMRResult;
import com.qg.kinectpatient.result.GetRcStageResult;
import com.qg.kinectpatient.result.LoginResult;
import com.qg.kinectpatient.result.Result;
import com.qg.kinectpatient.result.UpdatePUserResult;
import com.qg.kinectpatient.util.CommandUtil;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by HWF on 2018/5/13..
 */
public class LogicImpl implements Logic{

    private static final String TAG = LogicImpl.class.getSimpleName();
    private Executor exec = Executors.newFixedThreadPool(CommandUtil.getCoreNum()*2);

    private static LogicImpl instance;

    private LogicImpl(){};

    public static LogicImpl getInstance(){
        if(instance == null){
            synchronized (LogicImpl.class){
                if(instance == null){
                    instance = new LogicImpl();
                }
            }
        }
        return instance;
    }

    private <P extends Param, R extends Result>void getResult(final P param, final LogicHandler<R> handler, final Class<R> clazz){
        GetResultTask<R> task = new GetResultTask<R>() {
            @Override
            public R onBackground() {
                R result = HttpProcess.sendHttp(param, clazz);
                handler.onResult(result, false);
                return result;
            }

            @Override
            public void onUI(R result) {
                handler.onResult(result, true);
            }
        };
        task.executeOnExecutor(exec);
    }

    @Override
    public void getDUserByPhone(GetDUserByPhoneParam param, LogicHandler<GetDUserByPhoneResult> handler) {
        getResult(param, handler, GetDUserByPhoneResult.class);
    }

    @Override
    public void login(LoginParam param, LogicHandler<LoginResult> handler) {
        getResult(param, handler, LoginResult.class);
    }

    @Override
    public void updatePUser(UpdatePUserParam param, LogicHandler<UpdatePUserResult> handler) {
        getResult(param, handler, UpdatePUserResult.class);
    }
    @Override
    public void getMR(GetMRParam param, LogicHandler<GetMRResult> handler) {
        getResult(param, handler, GetMRResult.class);
    }

    @Override
    public void getRcStage(GetRcStageParam param, LogicHandler<GetRcStageResult> handler) {
        getResult(param, handler, GetRcStageResult.class);
    }


    private abstract class GetResultTask<R extends Result> extends AsyncTask<Void,Void,R>{

        @Override
        protected R doInBackground(Void... ps) {
            return onBackground();
        }

        @Override
        protected void onPostExecute(R r) {
            onUI(r);
        }

        public abstract R onBackground();

        public abstract void onUI(R result);
    }

}
