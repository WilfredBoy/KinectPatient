package com.qg.kinectpatient.emsdk;


import com.qg.kinectpatient.model.VoiceBean;

/**
 * Created by ZH_L on 2016/10/25.
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
