package com.qg.kinectpatient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qg.kinectpatient.R;

import java.util.List;

/**
 * Created by ZH_L on 2016/10/30.
 */

public class DoctorProAdapter extends RecyclerView.Adapter<DoctorProAdapter.DoctorPropertyHolder> {
    private static final String TAG = DoctorProAdapter.class.getSimpleName();

    private int layoutId;
    private Context context;
    private String[] properties;
    private String[] values;


    public DoctorProAdapter(Context context, int layoutId, String[] properties, String[] values){
        this.context = context;
        this.layoutId = layoutId;
        this.properties = properties;
        this.values = values;
    }

    @Override
    public DoctorPropertyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layoutId, null);
        DoctorPropertyHolder holder = new DoctorPropertyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(DoctorPropertyHolder holder, int position) {
        if(position >= properties.length) return;
        String property = properties[position];
        String value = values[position];
        holder.proTv.setText(property);
        holder.valueTv.setText(value);
    }


    @Override
    public int getItemCount() {
        if(properties == null) return 0;
        return properties.length;
    }

    public class DoctorPropertyHolder extends RecyclerView.ViewHolder{
        TextView proTv,valueTv;


        public DoctorPropertyHolder(View itemView) {
            super(itemView);
            proTv = (TextView)itemView.findViewById(R.id.property_tv);
            valueTv = (TextView)itemView.findViewById(R.id.value_tv);
        }
    }
}
