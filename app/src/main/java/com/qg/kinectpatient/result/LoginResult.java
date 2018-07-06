package com.qg.kinectpatient.result;

import com.qg.kinectpatient.model.PUser;

/**
 * Created by HWF on 2018/5/13..
 */
public class LoginResult extends Result{
//    private int status;//状态，1为登陆成功，0为密码错误，2为不存在用户
    public PUser pUser;//仅在登陆成功时数据有效

    public int getStatus() {
        return status;
    }

    public PUser getPUser() {
        return pUser;
    }

    @Override
    protected String otherErrMsg() {
        switch (status) {
            case 0:
                return "密码错误";
            case 2:
                return "用户不存在";
            default:
                return super.otherErrMsg();
        }
    }

}
