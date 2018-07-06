package com.qg.kinectpatient.util;

/**
 * Created by HWF on 2018/5/13..
 */

public class NumberUtil {
    public static int parseInt(String n, int def) {
        try {
            return Integer.valueOf(n);
        } catch (NumberFormatException e){
            return def;
        }
    }
}
