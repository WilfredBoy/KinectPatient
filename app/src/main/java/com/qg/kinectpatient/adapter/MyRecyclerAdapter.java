package com.qg.kinectpatient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qg.kinectpatient.R;
import com.qg.kinectpatient.model.MedicalRecord;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by HWF on 2018/5/13..
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> implements View.OnClickListener {

    private ArrayList<MedicalRecord> medicalRecordList;
    private Context mContext;
    private SimpleDateFormat sfd = new SimpleDateFormat("yyyy/MM/dd");

    public MyRecyclerAdapter(Context context, ArrayList<MedicalRecord> objects) {
        this.mContext = context;
        this.medicalRecordList = new ArrayList<>();
        this.medicalRecordList.addAll(objects);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    // define interface
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, MedicalRecord data);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_item, viewGroup, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        // 将创建的View注册点击事件
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        viewHolder.hospital.setText(medicalRecordList.get(position).getHospital());
        viewHolder.keshi_name.setText(medicalRecordList.get(position).getDepartment() + "，" + medicalRecordList.get(position).getDname() + "医生");
        viewHolder.date.setText(sfd.format(medicalRecordList.get(position).getSetTime()));
        // 将数据保存在itemView的Tag中，以便点击时进行获取
        viewHolder.itemView.setTag(medicalRecordList.get(position));
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            // 注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (MedicalRecord) v.getTag());
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    //获取数据的数量
    @Override
    public int getItemCount() {
        return medicalRecordList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView hospital;
        private TextView keshi_name;
        private TextView date;

        public MyViewHolder(View itemView) {
            super(itemView);
            hospital = (TextView) itemView.findViewById(R.id.hospital);
            keshi_name = (TextView) itemView.findViewById(R.id.keshi_name);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }
}