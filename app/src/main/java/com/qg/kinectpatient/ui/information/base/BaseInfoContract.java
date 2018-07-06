package com.qg.kinectpatient.ui.information.base;


import com.qg.kinectpatient.ui.BasePresenter;
import com.qg.kinectpatient.ui.BaseView;

/**
 * Created by HWF on 2018/5/13..
 */

public class BaseInfoContract {
    interface View extends BaseView<Presenter> {
        void setName(String name);

        void setAge(int age);

        void setSex(String sex);

        void showEditAge(int age);

        void showError(String error);

        void showEditSex();

        void showSuccessEdit();

        boolean isActive();
    }

    interface Presenter extends BasePresenter {
        void saveBaseInfo(String name, int age, String sex);
    }
}
