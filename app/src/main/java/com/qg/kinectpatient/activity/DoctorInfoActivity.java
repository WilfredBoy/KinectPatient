package com.qg.kinectpatient.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qg.kinectpatient.R;
import com.qg.kinectpatient.adapter.DoctorProAdapter;
import com.qg.kinectpatient.emsdk.EMConstants;
import com.qg.kinectpatient.model.DUser;
import com.qg.kinectpatient.view.TopbarL;

/**
 * Created by ZH_L on 2016/10/30.
 */

public class DoctorInfoActivity extends BaseActivity{
    private static final String TAG = DoctorInfoActivity.class.getSimpleName();

    private TopbarL mTopbar;
    private RecyclerView mRecyclerView;
    private DoctorProAdapter mAdapter;
    private String[] properties = new String[]{"年龄", "性别", "医院", "科室", "职称"};
    private String[] values;

    private DUser dUser;

    private TextView mNameTv;
    private Button mDeleteBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_info);

        Intent intent = getIntent();
        dUser = (DUser) intent.getSerializableExtra(EMConstants.EXTRA_DUSER);
        initUI();
    }

    private void initUI(){
        mTopbar = (TopbarL)findViewById(R.id.doctor_info_topbar);
        initTopbar();

        mRecyclerView = (RecyclerView) findViewById(R.id.doctor_info_recyclerview);
        initRecyclerView();

        mDeleteBtn = (Button) findViewById(R.id.delete_btn);
        mDeleteBtn.setOnClickListener(this);
    }

    private void initTopbar(){
        String name = dUser.getName();
        mTopbar.setCenterText(true, name, null);
    }

    private void initRecyclerView(){
        String age = ""+dUser.getAge();
        String sex = (dUser.getSex() == 1)?"男":"女";
        String hospital = dUser.getHospital();
        String department = dUser.getDepartment();
        String jobTitle = dUser.getJobTitle();
        values = new String[]{age, sex, hospital, department, jobTitle};
        mAdapter = new DoctorProAdapter(this, R.layout.item_doctor_info, properties, values);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {

    }
}
