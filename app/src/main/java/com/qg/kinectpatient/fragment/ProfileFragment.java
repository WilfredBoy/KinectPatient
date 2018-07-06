package com.qg.kinectpatient.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.qg.kinectpatient.activity.RecordsDetailActivity;

import com.qg.kinectpatient.R;
import com.qg.kinectpatient.activity.App;
import com.qg.kinectpatient.adapter.MyRecyclerAdapter;
import com.qg.kinectpatient.logic.LogicHandler;
import com.qg.kinectpatient.logic.LogicImpl;
import com.qg.kinectpatient.model.MedicalRecord;
import com.qg.kinectpatient.param.GetMRParam;
import com.qg.kinectpatient.param.LoginParam;
import com.qg.kinectpatient.result.GetMRResult;
import com.qg.kinectpatient.result.LoginResult;
import com.qg.kinectpatient.view.DividerItemDecoration;
import com.qg.kinectpatient.view.TopbarZ;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by HWF on 2018/5/13..
 */
public class ProfileFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "ProfileFragment";
    private View view;
    private RecyclerView recyclerView;
    private ArrayList<MedicalRecord> medicalRecordList;
    private MyRecyclerAdapter mAdapter;
    private final static int RECORDS_DETAIL = 0; // 病历详情
    private TopbarZ topbar;

    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        initUI();
        initData();
        return view;
    }

    public void initData() {
        Log.e(TAG, "init");
        medicalRecordList = new ArrayList<>();
        // 获取联系人列表
        GetMRParam param = new GetMRParam();
        param.pUserId = App.getInstance().getUser().getId();
        LogicImpl.getInstance().getMR(param, new LogicHandler<GetMRResult>() {

            @Override
            public void onResult(GetMRResult result, boolean onUIThread) {
                if(result.isOk() && onUIThread) {
                    medicalRecordList.addAll(result.medicalRecords);
                    mAdapter = new MyRecyclerAdapter(getActivity(), medicalRecordList);
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.setOnItemClickListener(new MyRecyclerAdapter.OnRecyclerViewItemClickListener() {
                        @Override
                        public void onItemClick(View view, MedicalRecord data) {
                            if(data != null) {
                                RecordsDetailActivity.start(getActivity(), RECORDS_DETAIL, data);
                            } else {
                                Log.e(TAG, "null");
                            }
                        }
                    });
                } else if(onUIThread && !result.isOk()) {
                    Log.d("黄伟烽", result.isOk() + ":" + onUIThread);
                    Toast.makeText(getActivity(), R.string.getMRfail, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // super.onSaveInstanceState(outState);
    }

    private void initUI() {
        topbar = (TopbarZ) view.findViewById(R.id.topbar);
        topbar.setTitle(true, App.getInstance().getUser().getName(), null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:

                break;
        }
    }
}
