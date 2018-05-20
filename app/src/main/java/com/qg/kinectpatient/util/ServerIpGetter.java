package com.qg.kinectpatient.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.qg.kinectpatient.activity.App;

/**
 * Created by jimiji on 2018/4/21.
 */

public class ServerIpGetter {


    public static String getIpPort() {
        SharedPreferences preferences = App.getInstance().getSharedPreferences("IP_PORT", Context.MODE_PRIVATE);
        return preferences.getString("value", "0.0.0.0:0000");
    }

    public static void setIpPort(String value) {
        SharedPreferences.Editor editor = App.getInstance().getSharedPreferences("IP_PORT", Context.MODE_PRIVATE).edit();
        editor.putString("value", value);
        editor.apply();
    }

}
