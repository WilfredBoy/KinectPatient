package com.qg.kinectpatient.param;


import com.qg.kinectpatient.model.PUser;

public class UpdatePUserParam extends Param {
    public PUser pUser;

    public UpdatePUserParam(PUser user) {
        pUser = user;
    }
}
