package com.sample.openweather.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by venugopalraog on 10/15/17.
 */

public class DateTimeUtils {

    public static String getDayOfWeek(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
        return simpleDateformat.format(cal.getTime());
    }

    public static String getDate(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("MMM dd yyyy hh:mm");
        return simpleDateformat.format(cal.getTime());
    }
}
