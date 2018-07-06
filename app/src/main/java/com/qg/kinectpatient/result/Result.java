package com.qg.kinectpatient.result;

import android.text.TextUtils;

/**
 * Created by HWF on 2018/5/13..
 */
public class Result {
    public int status = 1;
    public String errMsg = "";

    public boolean isOk() {
        return status == 1;
    }

    public String getErrMsg() {
        String errMsg = this.errMsg;
        if (!TextUtils.isEmpty(errMsg)) {
            return errMsg;
        } else {
            return otherErrMsg();
        }
    }

    protected String otherErrMsg() {
        return "未知错误";
    }
}
