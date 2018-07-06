package com.qg.kinectpatient.ui.information;

import android.support.annotation.NonNull;

import com.qg.kinectpatient.activity.App;
import com.qg.kinectpatient.model.PUser;

import static com.qg.kinectpatient.util.Preconditions.checkNotNull;

/**
 * Created by HWF on 2018/5/13..
 */

class PersonalInfoPresenter implements PersonalInfoContract.Presenter {
    private PersonalInfoContract.View mView;
    private int mDoctorId;

    PersonalInfoPresenter(int patientId, @NonNull PersonalInfoContract.View view) {
        mDoctorId = patientId;
        mView = checkNotNull(view);
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        loadInfo(mDoctorId);
    }

    private void loadInfo(int patientId) {
        PUser user = App.getInstance().getUser();
        mView.showName(user.getName());
        mView.showInfo(user.getAge() + "," + (user.getSex() == 0 ? "女" : "男"));
    }

    @Override
    public void baseInfo() {
        mView.showBaseInfo(mDoctorId);
    }

    public void manageAccount() {
        mView.showAccountManage(mDoctorId);
    }

}
