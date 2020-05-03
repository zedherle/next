package com.zed.next.ui.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateProvider {

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String getNowDate()
    {
        return formatter.format( new Date());
    }
}
