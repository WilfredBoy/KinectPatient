package com.qg.kinectpatient.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.qg.kinectpatient.R;
import com.qg.kinectpatient.emsdk.IMManager;
import com.qg.kinectpatient.model.ChatInfoBean;
import com.qg.kinectpatient.model.DUser;
import com.qg.kinectpatient.model.PUser;

import java.util.List;

/**
 * Created by HWF on 2018/5/13..
 */
public class ChatContactListAdapter extends ItemAdapter<ChatInfoBean, ChatContactListAdapter.ChatInfoHolder>{

    private static final String TAG = ChatContactListAdapter.class.getSimpleName();
    private List<ChatInfoBean> list;

    public ChatContactListAdapter(Context context, List<ChatInfoBean> list, int layoutId) {
        super(context, list, layoutId);
        this.list = list;
    }

    @Override
    public ChatInfoHolder createItemView(ViewGroup parent, int viewType) {
        View v = getView(parent, viewType);
        return new ChatInfoHolder(v);
    }

    private OnChatItemClickListener chatItemClickListener;
    public void setOnChatItemClickListener(OnChatItemClickListener listener){
        chatItemClickListener = listener;
    }

    public class ChatInfoHolder extends ItemViewHolder<ChatInfoBean>{
        private TextView nameTv;
        private TextView careerTv;
        private TextView unReadTv;

        public ChatInfoHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void initChilds(View itemView) {
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            careerTv = (TextView) itemView.findViewById(R.id.career_tv);
            unReadTv = (TextView)itemView.findViewById(R.id.unread_tv);
        }

        @Override
        public void bindElement(ChatInfoBean bean) {
            DUser dUser = bean.getDUser();
            String name = dUser.getName();
            nameTv.setText(dUser.getName());

            String hospital = dUser.getHospital();
            String department = dUser.getDepartment();
//            String jobTitle = dUser.getJobTitle();
            careerTv.setText(hospital + ","+ department);

            String username = bean.getIMUsername();
            int unReadCount = IMManager.getInstance(context).getUnreadMsgCount(username);
            Log.e(TAG, "unReadCount->"+unReadCount);
            if(unReadCount <= 0 ){
                unReadTv.setVisibility(View.GONE);
                unReadTv.setText("");
            }else{
                unReadTv.setText(""+unReadCount);
                unReadTv.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onClick(View view) {
            if(chatItemClickListener != null){
                chatItemClickListener.onChatItemClick(view, getPosition());
            }
        }

    }

    public interface OnChatItemClickListener{
        void onChatItemClick(View v, int position);
    }


    public void clearUnReadCount(ChatInfoBean bean){
        if(!list.contains(bean)){
            throw new RuntimeException("the ChatInfoBean is not in the DataSource List");
        }
        String username = bean.getIMUsername();
        IMManager.getInstance(context).clearUnReadMsg(username);
        notifyDataSetChanged();
    }
}
