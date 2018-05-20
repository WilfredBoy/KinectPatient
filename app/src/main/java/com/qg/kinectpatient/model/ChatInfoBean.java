package com.qg.kinectpatient.model;

import com.qg.kinectpatient.emsdk.EMConstants;

import java.io.Serializable;

/**
 * Created by ZH_L on 2016/10/28.
 */

public class ChatInfoBean implements Serializable{

    private DUser dUser;

    public ChatInfoBean(DUser dUser){
        this.dUser = dUser;
    }

    public DUser getDUser() {
        return dUser;
    }

    public void setDUser(DUser dUser) {
        this.dUser = dUser;
    }

    public String getIMUsername(){
        if(dUser == null){
            throw new NullPointerException("PUser is null");
        }
        return EMConstants.DOCTOR_USERNAME_PREFIX + dUser.getPhone();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }else if(obj instanceof ChatInfoBean){
            ChatInfoBean bean = (ChatInfoBean)obj;
            return bean.getIMUsername().equals(getIMUsername());
        }
        return false;
    }

}
