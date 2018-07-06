package com.qg.kinectpatient.param;

/**
 * Created by HWF on 2018/5/13..
 */
public class LoginParam extends Param{
    private String phone;
    private String password;

    public LoginParam(String phone, String password){
        this.phone = phone;
        this.password = password;
    }
}
