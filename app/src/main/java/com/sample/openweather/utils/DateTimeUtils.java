package com.sample.openweather.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by venugopalraog on 10/15/17.
 */

public class DateTimeUtils {

    public static String getDayOfWeek(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time * 1000);
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE", Locale.US);
        return simpleDateformat.format(cal.getTime());
    }

    public static String getDate(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time * 1000);
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("MMM dd yyyy hh:mm", Locale.US);
        return simpleDateformat.format(cal.getTime());
    }
}
