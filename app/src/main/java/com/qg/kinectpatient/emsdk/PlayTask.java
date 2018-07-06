package com.qg.kinectpatient.emsdk;


import com.qg.kinectpatient.model.VoiceBean;

/**
 * Created by HWF on 2018/5/13..
 */
public class PlayTask {

    private VoiceBean voiceBean;

    public PlayTask(VoiceBean voiceBean){
        this.voiceBean = voiceBean;
    }

    public VoiceBean getVoiceBean() {
        return voiceBean;
    }

    public void setVoiceBean(VoiceBean voiceBean) {
        this.voiceBean = voiceBean;
    }
}
