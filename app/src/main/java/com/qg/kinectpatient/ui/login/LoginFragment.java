package com.qg.kinectpatient.ui.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.qg.kinectpatient.R;
import com.qg.kinectpatient.activity.HomeActivity;
import com.qg.kinectpatient.fragment.BaseFragment;
import com.qg.kinectpatient.model.PUser;
import com.qg.kinectpatient.ui.information.PersonalInfoActivity;
import com.qg.kinectpatient.util.ServerIpGetter;
import com.qg.kinectpatient.util.ToastUtil;

import static com.qg.kinectpatient.util.Preconditions.checkNotNull;

/**
 * Main UI for the login screen.
 * Created by TZH on 2016/10/27.
 */
public class LoginFragment extends BaseFragment implements LoginContract.View {

    private LoginContract.Presenter mPresenter;

    private TextView mPhone;

    private TextView mPassword;

    private CheckBox mRememberPassword;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.login_fragment, container, false);
        mPhone = (TextView) root.findViewById(R.id.phone_input);
        mPassword = (TextView) root.findViewById(R.id.password_input);
        mRememberPassword = (CheckBox) root.findViewById(R.id.rmb_pwd_cb);

        // Set up see password view.
        View spv = root.findViewById(R.id.see_pwd);
        spv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Show password.
                        setPasswordVisibility(true);
                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_OUTSIDE:
                        // Hide password.
                        setPasswordVisibility(false);
                        return true;
                }
                return false;
            }
        });
        setPasswordVisibility(false);

        // Set up login button.
        View lb = root.findViewById(R.id.login);
        lb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.login(
                        mPhone.getText().toString(),
                        mPassword.getText().toString(),
                        mRememberPassword.isChecked()
                );
            }
        });
        View bg = root.findViewById(R.id.content_login);
        bg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                final EditText editText = new EditText(getActivity());
                editText.setHint(ServerIpGetter.getIpPort());
                dialog.setTitle("服务器IP、Port")
                        .setView(editText)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ServerIpGetter.setIpPort(editText.getText().toString());
                                ToastUtil.showToast(getActivity(), "已保存");
                                dialogInterface.dismiss();
                            }
                        })
                        .show();

                return true;
            }
        });
        return root;
    }

    @Override
    public void showInputError() {
        ToastUtil.showToast(getContext(), R.string.login_input_error);
    }

    @Override
    public void showError(String error) {
        ToastUtil.showToast(getContext(), error);
    }

    @Override
    public void setPhone(String phone) {
        mPhone.setText(phone);
    }

    @Override
    public void setPassword(String password) {
        mPassword.setText(password);
    }

    @Override
    public void setPasswordVisibility(boolean visible) {
        if (visible) {
            mPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            mPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
        }
    }

    @Override
    public void showMain(PUser pUser) {
        Intent intent = new Intent(getContext(), HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

}
