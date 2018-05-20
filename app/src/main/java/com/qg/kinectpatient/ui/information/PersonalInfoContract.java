package com.qg.kinectpatient.ui.information;

import com.qg.kinectpatient.ui.BasePresenter;
import com.qg.kinectpatient.ui.BaseView;

/**
 * Created by TZH on 2016/10/26.
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
