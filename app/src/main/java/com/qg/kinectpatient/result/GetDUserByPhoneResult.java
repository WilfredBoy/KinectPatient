package com.qg.kinectpatient.result;



import com.qg.kinectpatient.model.DUser;

import java.util.Map;

/**
 * Created by HWF on 2018/5/13..
 */
public class GetDUserByPhoneResult extends Result{
    private Map<String,DUser> phoneToDUser;

    public Map<String, DUser> getPhoneToDUser() {
        return phoneToDUser;
    }
}
