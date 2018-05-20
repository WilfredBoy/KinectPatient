package com.qg.kinectpatient.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyphenate.EMContactListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.exceptions.HyphenateException;
import com.qg.kinectpatient.R;
import com.qg.kinectpatient.activity.ChatActivity;
import com.qg.kinectpatient.adapter.ChatContactListAdapter;
import com.qg.kinectpatient.emsdk.EMConstants;
import com.qg.kinectpatient.emsdk.IMFilter;
import com.qg.kinectpatient.emsdk.IMManager;
import com.qg.kinectpatient.emsdk.LoginCallback;
import com.qg.kinectpatient.logic.LogicHandler;
import com.qg.kinectpatient.logic.LogicImpl;
import com.qg.kinectpatient.model.ChatInfoBean;
import com.qg.kinectpatient.model.DUser;
import com.qg.kinectpatient.param.GetDUserByPhoneParam;
import com.qg.kinectpatient.result.GetDUserByPhoneResult;
import com.qg.kinectpatient.util.CommandUtil;
import com.qg.kinectpatient.util.ToastUtil;
import com.qg.kinectpatient.view.TopbarL;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ZH_L on 2016/10/21.
 */
public class ChatListFragment extends BaseFragment implements ChatContactListAdapter.OnChatItemClickListener, EMMessageListener, EMContactListener {

    private static final String TAG = ChatListFragment.class.getSimpleName();

    private TopbarL mTopbar;
    private RecyclerView mRecyclerView;
    private List<ChatInfoBean> mList;
    private ChatContactListAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chatlist,null);
        if(v != null){
            mTopbar = (TopbarL)v.findViewById(R.id.chat_list_topbar);
            mRecyclerView = (RecyclerView)v.findViewById(R.id.recyclerview);
            initTopbar();
            initRecyclerView();
//            initEM();
            initReceiver();
            getDataFromServer();

            loginEM();
        }
        return v;
    }

    private void initTopbar(){
        String title = getActivity().getResources().getString(R.string.chat_list);
        mTopbar.setCenterText(true, title, null);
    }

    private void initRecyclerView(){
        mList = new ArrayList<>();
        mAdapter = new ChatContactListAdapter(getActivity(), mList, R.layout.item_chatlist);
        mAdapter.setOnChatItemClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void loginEM(){
        String phone = "12345678901";
        IMManager.getInstance(getActivity()).login(phone, new LoginCallback() {
            @Override
            public void onSuccess() {
                getDataFromServer();
            }

            @Override
            public void onError(String errorMsg) {

            }
        });
    }


    private ChatInfoBean curChatingBean = null;
    private BroadcastReceiver mReceiver;


    private void initReceiver(){
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                curChatingBean = null;
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction(EMConstants.ACTION_CHAT_ACTIVITY_FINISH);
        getActivity().registerReceiver(mReceiver, filter);
    }

    private void getDataFromServer(){
        new Thread(){
            @Override
            public void run() {
                try {
                    final List<String> usernames = IMManager.getInstance(getActivity()).getFriendsList();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final List<String> phones = IMFilter.filterToPhones(usernames);
                            Log.e(TAG, "friendlist->"+phones.toString());
                            GetDUserByPhoneParam param = new GetDUserByPhoneParam(phones);
                            LogicImpl.getInstance().getDUserByPhone(param, new LogicHandler<GetDUserByPhoneResult>() {
                                @Override
                                public void onResult(GetDUserByPhoneResult result, boolean onUIThread) {
                                    if(onUIThread){
                                        if(result.isOk()){
                                            //get a Map<String, PUser>
                                            Map<String, DUser> phoneToDUser = result.getPhoneToDUser();
                                            if(phoneToDUser != null){
                                                Log.d("GetPUserByPhoneResult", phoneToDUser.toString());
                                            }
                                            for(String phone: phones) {
                                                DUser dUser = phoneToDUser.get(phone);
                                                if(dUser != null){
                                                    ChatInfoBean bean = new ChatInfoBean(dUser);
                                                    mList.add(bean);
                                                }
                                            }
                                            mAdapter.notifyDataSetChanged();
                                        }else{
                                            ToastUtil.showResultErrorToast(result);
                                        }
//
//                                        DUser dUser = new DUser("测试",1, 18,"13549991585","","广东省中医院","皮肤科","老司机");
//                                        ChatInfoBean cb = new ChatInfoBean(dUser);
//                                        mList.add(cb);
//                                        mAdapter.notifyDataSetChanged();
                                    }
                                }
                            });
                        }
                    });

                } catch (HyphenateException e) {
                    e.printStackTrace();
                    Log.e(TAG, e.getMessage());
                }
            }
        }.start();


    }


    private void initEM(){
        //监听消息回调
        IMManager.getInstance(getActivity()).addMessageListener(this);
        //监听联系人回调
        IMManager.getInstance(getActivity()).setContactListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mReceiver != null){
            getActivity().unregisterReceiver(mReceiver);
            mReceiver = null;
        }
    }


    //    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if(requestCode == EMConstants.REQCODE_START_CHAT){
//            Log.d(TAG,"onActivityResult");
//            curChatingBean = null;
//        }
//    }


    @Override
    public void onChatItemClick(View v, int position) {
        ChatInfoBean bean = mList.get(position);
        curChatingBean  = bean;
        mAdapter.clearUnReadCount(bean);
        Bundle extra = new Bundle();
        extra.putSerializable(EMConstants.KEY_CHATINFO_BEAN, bean);
        ChatActivity.startForResult(getActivity(),extra,EMConstants.REQCODE_START_CHAT);
    }

    private Runnable r = new Runnable(){

        @Override
        public void run() {
            //显示所有联系人的消息收到状态
            mAdapter.notifyDataSetChanged();

            //把正在聊天的人的未读消息清零
            if(curChatingBean != null){
                Log.e(TAG, "clearUnReadCount");
                mAdapter.clearUnReadCount(curChatingBean);
            }
        }
    };

    @Override
    public void onMessageReceived(List<EMMessage> list) {
        Log.e(TAG, "in main thread->"+(Looper.myLooper() == Looper.getMainLooper()));
        Log.e(TAG, "onMessageReceived");
        if(list == null)return;
        CommandUtil.vibrate(1000);
        getActivity().runOnUiThread(r);
    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> list) {

    }

    @Override
    public void onMessageReadAckReceived(List<EMMessage> list) {

    }

    @Override
    public void onMessageDeliveryAckReceived(List<EMMessage> list) {

    }

    @Override
    public void onMessageChanged(EMMessage emMessage, Object o) {

    }

    private String filterUsernameToPhone(String username){
        if(username == null)return "";
        return username.replace(EMConstants.DOCTOR_USERNAME_PREFIX,"").replace(EMConstants.PATIENT_USERNAME_PREFIX, "");
    }

    @Override
    public void onContactAdded(String username) {
        if(username == null || username.equals("")) return;
        //增加了某个联系人
        final List<String> phones = IMFilter.filterToPhones(username);
        GetDUserByPhoneParam param = new GetDUserByPhoneParam(phones);
        LogicImpl.getInstance().getDUserByPhone(param, new LogicHandler<GetDUserByPhoneResult>() {
            @Override
            public void onResult(GetDUserByPhoneResult result, boolean onUIThread) {
                if(onUIThread){
                    if(result.isOk()){
                        //get a Map<String, DUser>
                        Map<String, DUser> map = result.getPhoneToDUser();
                        if(map != null){
                            for(String phone: phones){
                                DUser dUser = map.get(phone);
                                ChatInfoBean bean = new ChatInfoBean(dUser);
                                mList.add(bean);
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                    }else{
                        ToastUtil.showResultErrorToast(result);
                    }
                }
            }
        });
    }

    @Override
    public void onContactDeleted(String username) {
        //被删除时调用,理论上这个for循环只有一个匹配
//        for(ChatInfoBean bean: mList){
//            if(username.equals(bean.getIMUsername())){
//                mList.remove(bean);
//                showMessage("病人-"+ bean.getDUser().getName() + "-删除了你");
//            }
//        }
//        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onContactInvited(String s, String s1) {

    }

    @Override
    public void onContactAgreed(String s) {

    }

    @Override
    public void onContactRefused(String s) {

    }

    private void showMessage(String text){
        ToastUtil.showToast(getActivity(), text);
    }
}
