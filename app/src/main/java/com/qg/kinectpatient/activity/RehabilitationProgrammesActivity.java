package com.qg.kinectpatient.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.qg.kinectpatient.R;
import com.qg.kinectpatient.adapter.RehabilitationProgrammesAdapter;
import com.qg.kinectpatient.logic.LogicHandler;
import com.qg.kinectpatient.logic.LogicImpl;
import com.qg.kinectpatient.model.MedicalRecord;
import com.qg.kinectpatient.model.RcStage;
import com.qg.kinectpatient.param.GetRcStageParam;
import com.qg.kinectpatient.result.GetRcStageResult;
import com.qg.kinectpatient.view.TopbarZ;

import java.util.ArrayList;


/**
 * Created by 攀登者 on 2016/10/31.
 */
public class RehabilitationProgrammesActivity extends BaseActivity implements View.OnClickListener {

    private TopbarZ topbar;
    private ListView mListView;
    private ArrayList<RcStage> list;
    private static MedicalRecord medicalRecord;
    private RehabilitationProgrammesAdapter adapter;
    private AlertDialog dialog;

    public static void start(Activity context, int requestCode, MedicalRecord data) {
        medicalRecord = data;
        Intent intent = new Intent(context, RehabilitationProgrammesActivity.class);
        context.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rehabilitation_programmes);
        initUI();
        getRcStage();
    }

    private void initUI() {
        topbar = (TopbarZ) findViewById(R.id.topbar);
        topbar.setTitle(true, R.string.rehabilitation_programmes, null);
        topbar.setLeftButton(true, R.drawable.back_selector, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mListView = (ListView) findViewById(R.id.listview);
    }

    private void getRcStage() {
        GetRcStageParam param = new GetRcStageParam();
        param.mrId = medicalRecord.getId();
        LogicImpl.getInstance().getRcStage(param, new LogicHandler<GetRcStageResult>() {
            @Override
            public void onResult(GetRcStageResult result, boolean onUIThread) {
                if (result.isOk() && onUIThread) {
                    list = new ArrayList<RcStage>();
                    if (result.rcStages != null) {
                        list.addAll(result.rcStages);
                    }
                    adapter = new RehabilitationProgrammesAdapter(RehabilitationProgrammesActivity.this, R.layout.rehabilitation_programmes_item, list);
                    mListView.setAdapter(adapter);
                } else if (!result.isOk() && onUIThread) {

                    Toast.makeText(RehabilitationProgrammesActivity.this, R.string.getRCfail, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
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
