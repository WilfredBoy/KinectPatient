package com.qg.kinectpatient.ui.information.base;


import com.qg.kinectpatient.activity.App;
import com.qg.kinectpatient.logic.LogicHandler;
import com.qg.kinectpatient.logic.LogicImpl;
import com.qg.kinectpatient.model.PUser;
import com.qg.kinectpatient.param.UpdatePUserParam;
import com.qg.kinectpatient.result.UpdatePUserResult;

import static com.qg.kinectpatient.util.Preconditions.checkNotNull;

/**
 * Created by TZH on 2016/10/29.
 */
public class BaseInfoPresenter implements BaseInfoContract.Presenter {

    private BaseInfoContract.View mView;

    private final int mDoctorId;

    public BaseInfoPresenter(int doctorId, BaseInfoContract.View view) {
        mDoctorId = doctorId;
        mView = checkNotNull(view);
        mView.setPresenter(this);
    }


    @Override
    public void start() {
        PUser user = App.getInstance().getUser();
        mView.setName(user.getName());
        mView.setAge(user.getAge());
        mView.setSex(user.getSex() != 0 ? "男" : "女");
    }

    @Override
    public void saveBaseInfo(String name, int age, String sex) {
        PUser user = App.getInstance().getUser();
        user.setName(name);
        user.setAge(age);
        user.setSex(sex.equals("男") ? 1 : 0);
        LogicImpl.getInstance().updatePUser(new UpdatePUserParam(user), new LogicHandler<UpdatePUserResult>() {
            @Override
            public void onResult(UpdatePUserResult result, boolean onUIThread) {
                if (onUIThread && mView.isActive()) {
                    if (result.isOk()) {
                        mView.showSuccessEdit();
                    } else {
                        mView.showError(result.getErrMsg());
                    }
                }
            }
        });
    }
}
