package com.qg.kinectpatient.logic;


import com.qg.kinectpatient.param.GetDUserByPhoneParam;
import com.qg.kinectpatient.param.GetMRParam;
import com.qg.kinectpatient.param.GetRcStageParam;
import com.qg.kinectpatient.param.LoginParam;
import com.qg.kinectpatient.param.UpdatePUserParam;
import com.qg.kinectpatient.result.GetDUserByPhoneResult;
import com.qg.kinectpatient.result.GetMRResult;
import com.qg.kinectpatient.result.GetRcStageResult;
import com.qg.kinectpatient.result.LoginResult;
import com.qg.kinectpatient.result.UpdatePUserResult;

/**
 * Created by HWF on 2018/5/13..
 */
public interface Logic {

    void getDUserByPhone(GetDUserByPhoneParam param, LogicHandler<GetDUserByPhoneResult> handler);

    void login(LoginParam param, LogicHandler<LoginResult> handler);

    void updatePUser(UpdatePUserParam param, LogicHandler<UpdatePUserResult> handler);

    public void getMR(GetMRParam param, LogicHandler<GetMRResult> handler);

    public void getRcStage(GetRcStageParam param, LogicHandler<GetRcStageResult> handler);
}
