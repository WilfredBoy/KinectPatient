package com.qg.kinectpatient.ui.login;

import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.qg.kinectpatient.activity.App;
import com.qg.kinectpatient.data.UserRepository;
import com.qg.kinectpatient.emsdk.EMConstants;
import com.qg.kinectpatient.emsdk.IMManager;
import com.qg.kinectpatient.emsdk.LoginCallback;
import com.qg.kinectpatient.logic.LogicHandler;
import com.qg.kinectpatient.logic.LogicImpl;
import com.qg.kinectpatient.model.PUser;
import com.qg.kinectpatient.param.LoginParam;
import com.qg.kinectpatient.result.LoginResult;
import com.qg.kinectpatient.util.FormatChecker;
import com.qg.kinectpatient.util.JsonUtil;

import static com.qg.kinectpatient.util.Preconditions.checkNotNull;

/**
 * Created by HWF on 2018/5/13..
 */
public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View mLoginView;

    public LoginPresenter(LoginContract.View loginView) {
        mLoginView = checkNotNull(loginView, "loginView cannot be null!");
        mLoginView.setPresenter(this);
    }

    @Override
    public void start() {
        loadAccount();
    }

    private void loadAccount() {
        mLoginView.setPhone(UserRepository.getInstance().getPhone());
        mLoginView.setPassword(UserRepository.getInstance().getPassword());
    }

    @Override
    public void login(final String phone, final String password, final boolean rememberPassword) {
        if (!FormatChecker.isMobile(phone) || !FormatChecker.isAcceptablePassword(password)) {
            mLoginView.showInputError();
            return;
        }
        LogicImpl.getInstance().login(new LoginParam(phone, password), new LogicHandler<LoginResult>() {
            @Override
            public void onResult(LoginResult result, boolean onUIThread) {
                if (!onUIThread) {
                    return;
                }
                if (!result.isOk()) {
                    if (mLoginView.isActive()) {
                        mLoginView.showError(result.getErrMsg());
                    }
                    return;
                }
                // OK
                final PUser pUser = result.getPUser();
                android.util.
                        Log.d("Test", "onResult: " + JsonUtil.toJson(result));
                android.util.
                Log.d("Test", "onResult: " + JsonUtil.toJson(pUser));
                IMManager.getInstance(App.getInstance()).login(phone, new LoginCallback() {
                    @Override
                    public void onSuccess() {
                        if (mLoginView.isActive()) {
                            App.getInstance().setUser(pUser);
                            mLoginView.showMain(pUser);
                            if (rememberPassword) {
                                saveLogin(phone, password);
                            } else {
                                clearLogin();
                            }
                        }
                        initChatListener();
                    }

                    @Override
                    public void onError(String errorMsg) {
                        if (mLoginView.isActive()) {
                            mLoginView.showError(errorMsg);
                        }
                    }
                });
            }

        });
    }

    private void initChatListener() {
        IMManager.getInstance(App.getInstance()).setContactListener(new EMContactListener() {
            @Override
            public void onContactAdded(String s) {

            }

            @Override
            public void onContactDeleted(String s) {

            }

            @Override
            public void onContactInvited(String phone, String s1) {
                try{
                    EMClient.getInstance().contactManager().acceptInvitation(EMConstants.PATIENT_USERNAME_PREFIX + phone);
                }catch (HyphenateException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onContactAgreed(String s) {

            }

            @Override
            public void onContactRefused(String s) {

            }
        });
    }

    private void clearLogin() {
        UserRepository.getInstance().clear();
    }

    private void saveLogin(String phone, String password) {
        UserRepository.getInstance().saveUser(phone, password);
    }

}
