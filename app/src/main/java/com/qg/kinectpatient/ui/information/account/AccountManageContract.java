package com.qg.kinectpatient.ui.information.account;

import com.qg.kinectpatient.ui.BasePresenter;
import com.qg.kinectpatient.ui.BaseView;

/**
 * @deprecated
 */
public class AccountManageContract {
    interface View extends BaseView<Presenter> {
        void showPhone(String phone);

        void showInputPassword(int patientId);

        void showEditPassword(int patientId);

        boolean isActive();
    }

    interface Presenter extends BasePresenter {
        void editPassword();
    }
}
