package com.qg.kinectpatient.ui.information;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qg.kinectpatient.R;
import com.qg.kinectpatient.activity.App;
import com.qg.kinectpatient.fragment.BaseFragment;
import com.qg.kinectpatient.ui.information.base.BaseInfoActivity;

import static com.qg.kinectpatient.util.Preconditions.checkNotNull;

/**
 * Created by TZH on 2016/10/26.
 */
public class PersonalInfoFragment extends BaseFragment implements PersonalInfoContract.View {

    private static final String ARGUMENT_DOCTOR_ID = "DOCTOR_ID";

    private PersonalInfoContract.Presenter mPresenter;

    private TextView mName;

    private TextView mInfo;

    // Remove the parameter.
    public static PersonalInfoFragment newInstanceWithPresenter() {
        int patientId = 0;
        PersonalInfoFragment fragment = newInstance(patientId);
        new PersonalInfoPresenter(patientId, fragment);
        return fragment;
    }

    /*package*/ static PersonalInfoFragment newInstance(int patientId) {
        Bundle args = new Bundle();
        args.putInt(ARGUMENT_DOCTOR_ID, patientId);
        PersonalInfoFragment fragment = new PersonalInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull PersonalInfoContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.personal_info_fragment, container, false);

        mName = (TextView) root.findViewById(R.id.name);
        mInfo = (TextView) root.findViewById(R.id.info);

        root.findViewById(R.id.base_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.baseInfo();
            }
        });

        root.findViewById(R.id.account_manage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.manageAccount();
            }
        });

        return root;
    }

    @Override
    public void showName(String name) {
        mName.setText(name);
    }

    @Override
    public void showInfo(String info) {
        mInfo.setText(info);
    }

    @Override
    public void showBaseInfo(int patientId) {
        BaseInfoActivity.start(getContext(), patientId);
    }

    @Override
    public void showAccountManage(int patientId) {
        /* This method was deprecated */
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
