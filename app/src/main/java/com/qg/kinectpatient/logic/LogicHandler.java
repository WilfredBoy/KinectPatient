package com.qg.kinectpatient.logic;


import com.qg.kinectpatient.result.Result;

/**
 * Created by HWF on 2018/5/13..
 */
public interface LogicHandler<R extends Result> {
    void onResult(R result, boolean onUIThread);
}
