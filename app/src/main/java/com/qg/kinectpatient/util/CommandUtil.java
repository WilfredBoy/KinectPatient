package com.qg.kinectpatient.util;

import android.content.Context;
import android.os.Vibrator;

import com.qg.kinectpatient.activity.App;


/**
 * Created by HWF on 2018/5/13..
 */
public class CommandUtil {

    public static int getCoreNum(){
        return Runtime.getRuntime().availableProcessors();
    }

    public static void vibrate(long timemillis){
        Vibrator vibrator = (Vibrator) App.getInstance().getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(timemillis);
    }
}
