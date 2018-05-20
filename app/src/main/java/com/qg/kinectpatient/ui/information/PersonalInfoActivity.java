package com.qg.kinectpatient.ui.information;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.qg.kinectpatient.activity.SingleFragmentActivity;

public class PersonalInfoActivity extends SingleFragmentActivity {

    public static final String ARGUMENT_DOCTOR_ID = "DOCTOR_ID";

    private int mDoctorId;

    public static void start(Context context, int patientId) {
        Intent starter = new Intent(context, PersonalInfoActivity.class);
        starter.putExtra(ARGUMENT_DOCTOR_ID, patientId);
        context.startActivity(starter);
    }

    @Override
    protected void prepareExtraData() {
        mDoctorId = getIntent().getIntExtra(ARGUMENT_DOCTOR_ID, 0);
    }

    @Override
    protected Fragment newFragment() {
        return PersonalInfoFragment.newInstance(mDoctorId);
    }

    @Override
    protected <T extends Fragment> void createPresenter(T fragment) {
        new PersonalInfoPresenter(mDoctorId, (PersonalInfoContract.View) fragment);
    }
}
