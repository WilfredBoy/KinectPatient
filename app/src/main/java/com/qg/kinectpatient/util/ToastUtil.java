package com.qg.kinectpatient.util;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.qg.kinectpatient.activity.App;
import com.qg.kinectpatient.result.Result;


/**
 * Created by HWF on 2018/5/13..
 */
public class ToastUtil {

    private static Toast mToast = null;

    public static void showToast(Context context, String str){
        if(mToast == null){
            mToast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        }else{
            mToast.setText(str);
        }
        mToast.show();
    }

    public static void showToast(Context context, @StringRes int strId){
        showToast(context, context.getResources().getString(strId));
    }

    public static void showResultErrorToast(Result r){
        showToast(App.getInstance(), r.errMsg);
    }
}
