package com.qg.kinectpatient.param;

import java.util.List;

/**
 * Created by HWF on 2018/5/13..
 */
public class GetDUserByPhoneParam extends Param{
    private List<String> phones;

    public GetDUserByPhoneParam(List<String> phones){
        this.phones = phones;
    }
}
