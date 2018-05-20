package com.qg.kinectpatient.logic;


import com.qg.kinectpatient.result.Result;

/**
 * Created by ZH_L on 2016/10/22.
 */
public interface LogicHandler<R extends Result> {
    void onResult(R result, boolean onUIThread);
}
