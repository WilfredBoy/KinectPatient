package com.qg.kinectpatient.util;

import android.content.Context;
import android.os.Vibrator;

import com.qg.kinectpatient.activity.App;


/**
 * Created by ZH_L on 2016/10/21.
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
