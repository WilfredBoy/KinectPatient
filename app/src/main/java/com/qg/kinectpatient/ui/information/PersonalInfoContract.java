package com.qg.kinectpatient.ui.information;

import com.qg.kinectpatient.ui.BasePresenter;
import com.qg.kinectpatient.ui.BaseView;

/**
 * Created by HWF on 2018/5/13..
 */

class PersonalInfoContract {
    interface View extends BaseView<Presenter> {
        void showName(String name);

        void showInfo(String info);

        void showBaseInfo(int patientId);

        void showAccountManage(int patientId);

        boolean isActive();
    }

    interface Presenter extends BasePresenter {
        void baseInfo();

        void manageAccount();
    }
}
