package com.qg.kinectpatient.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.exceptions.HyphenateException;
import com.qg.kinectpatient.R;
import com.qg.kinectpatient.emsdk.IMManager;
import com.qg.kinectpatient.model.MedicalRecord;
import com.qg.kinectpatient.view.TopbarZ;

import java.text.SimpleDateFormat;

/**
 * Created by 攀登者 on 2016/10/31.
 */
public class RecordsDetailActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "RecordsDetailActivity";
    private TopbarZ topbar;
    private TextView hospity, keshi_name, time, medicalCondition;
    private Button rc;
    private static MedicalRecord medicalRecord;
    private static final int REQUEST_CODE_R = 4;
    private SimpleDateFormat sfd = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");

    public static void start(Activity context, int requestCode, MedicalRecord data) {
        medicalRecord = data;
        Intent intent = new Intent(context, RecordsDetailActivity.class);
        context.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordsdetails);
        initUI();
    }

    private void initUI() {
        topbar = (TopbarZ) findViewById(R.id.topbar);
        topbar.setLeftButton(true, R.drawable.back_selector, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
        topbar.setTitle(true, R.string.detail, null);
        topbar.setRightButton(true, R.drawable.message, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 聊天
                try{
                    IMManager.getInstance(RecordsDetailActivity.this).addContact(medicalRecord.getDphone());
                    Toast.makeText(RecordsDetailActivity.this, "已发起聊天请求，待医生确认", Toast.LENGTH_SHORT).show();
                }catch (HyphenateException e){
                    e.printStackTrace();
                }
            }
        });
        hospity = (TextView) findViewById(R.id.hospital);
        keshi_name = (TextView) findViewById(R.id.keshi_name);
        time = (TextView) findViewById(R.id.time);
        medicalCondition = (TextView) findViewById(R.id.medical_condition);
        rc = (Button) findViewById(R.id.rc);
        rc.setOnClickListener(this);

        // 设置名字，年龄，性别
        hospity.setText(medicalRecord.getHospital());
        keshi_name.setText(medicalRecord.getDepartment() + "，" + medicalRecord.getDname());
        time.setText(sfd.format(medicalRecord.getSetTime()));
        medicalCondition.setText(medicalRecord.getConditions());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rc:
                RehabilitationProgrammesActivity.start(RecordsDetailActivity.this, REQUEST_CODE_R, medicalRecord);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK)
            return;

        switch (requestCode) {
            case REQUEST_CODE_R:
                finish();
                break;
            default:
                break;
        }
    }
}
