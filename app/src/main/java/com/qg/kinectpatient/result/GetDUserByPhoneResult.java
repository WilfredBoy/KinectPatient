package com.qg.kinectpatient.result;



import com.qg.kinectpatient.model.DUser;

import java.util.Map;

/**
 * Created by ZH_L on 2016/10/26.
 */
public class GetDUserByPhoneResult extends Result{
    private Map<String,DUser> phoneToDUser;

    public Map<String, DUser> getPhoneToDUser() {
        return phoneToDUser;
    }
}
