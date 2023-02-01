package com.windhide.restaurant.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeUtil {

    public static String getNowTime(){
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(System.currentTimeMillis());
    }
}
