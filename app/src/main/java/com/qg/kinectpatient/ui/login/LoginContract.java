package com.qg.kinectpatient.ui.login;

import com.qg.kinectpatient.model.DUser;
import com.qg.kinectpatient.ui.BasePresenter;
import com.qg.kinectpatient.ui.BaseView;
import com.qg.kinectpatient.model.PUser;

/**
 * Created by HWF on 2018/5/13..
 */
public class LoginContract {

    interface View extends BaseView<Presenter> {
        void showInputError();

        void showError(String error);

        void setPhone(String phone);

        void setPassword(String password);

        void setPasswordVisibility(boolean visible);

        void showMain(PUser dUser);

        boolean isActive();
    }

    interface Presenter extends BasePresenter {
        void login(String phone, String password, boolean rememberPassword);
    }
}
