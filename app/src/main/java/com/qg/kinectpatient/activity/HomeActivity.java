package com.qg.kinectpatient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;

import com.qg.kinectpatient.R;
import com.qg.kinectpatient.fragment.ChatListFragment;
import com.qg.kinectpatient.fragment.DoctorFragment;
import com.qg.kinectpatient.fragment.ProfileFragment;
import com.qg.kinectpatient.ui.information.PersonalInfoFragment;

/**
 * Created by 攀登者 on 2016/10/28.
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "HomeActivity";
    private ProfileFragment mProfileFragment;
    private ChatListFragment mDoctorFragment;
    private PersonalInfoFragment mPersonalInfoFragment;

    private ImageButton profile, doctor, me;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        fragmentManager = getSupportFragmentManager();
        setTabSelection(1);
        setTabSelection(2);
        setTabSelection(0);
    }

    private void initViews() {
        profile = (ImageButton) findViewById(R.id.profile);
        doctor = (ImageButton) findViewById(R.id.doctor);
        me = (ImageButton) findViewById(R.id.me);
        profile.setImageResource(R.drawable.profile_click);
        doctor.setImageResource(R.drawable.doctor_normal);
        me.setImageResource(R.drawable.me_normal);
        profile.setOnClickListener(this);
        doctor.setOnClickListener(this);
        me.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile:
                setTabSelection(0);
                break;
            case R.id.doctor:
                setTabSelection(1);
                break;
            case R.id.me:
                setTabSelection(2);
                break;
            default:
                break;
        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index 每个tab页对应的下标。
     */
    private void setTabSelection(int index) {
        clearSelection();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case 0:
                profile.setImageResource(R.drawable.profile_click);
                if (mProfileFragment == null) {
                    mProfileFragment = new ProfileFragment();
                    transaction.add(R.id.fragmentlayout, mProfileFragment);
                } else {
                    transaction.show(mProfileFragment);
                }
                break;
            case 1:
                doctor.setImageResource(R.drawable.doctor_click);
                if (mDoctorFragment == null) {
                    mDoctorFragment = new ChatListFragment();
                    transaction.add(R.id.fragmentlayout, mDoctorFragment);
                } else {
                    transaction.show(mDoctorFragment);
                }
                break;
            case 2:
                me.setImageResource(R.drawable.me_click);
                if (mPersonalInfoFragment == null) {
                    mPersonalInfoFragment = PersonalInfoFragment.newInstanceWithPresenter();
                    transaction.add(R.id.fragmentlayout, mPersonalInfoFragment);
                } else {
                    transaction.show(mPersonalInfoFragment);
                }
                break;
            default:

                break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        profile.setImageResource(R.drawable.profile_normal);
        doctor.setImageResource(R.drawable.doctor_normal);
        me.setImageResource(R.drawable.me_normal);
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (mProfileFragment != null) {
            transaction.hide(mProfileFragment);
        }
        if (mDoctorFragment != null) {
            transaction.hide(mDoctorFragment);
        }
        if (mPersonalInfoFragment != null) {
            transaction.hide(mPersonalInfoFragment);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK)
            return;

        switch (requestCode) {

            default:
                break;
        }
    }
}