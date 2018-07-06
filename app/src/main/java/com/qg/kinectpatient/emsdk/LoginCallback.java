package com.qg.kinectpatient.emsdk;

/**
 * Created by HWF on 2018/5/13..
 */
public interface LoginCallback {
    void onSuccess();
    void onError(String errorMsg);
}
