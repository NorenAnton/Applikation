package com.miun.applikation.utils;

import java.text.SimpleDateFormat;
import java.lang.String;
import java.util.Date;

public class CalendarUtils {
    public static String selectedDate;

    public static String formattedDate(String date)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy MMMM dd");
        date = formatter.format(new Date());
        return date;
    }

    public static String formattedTime(String time)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        time = formatter.format(new Date());
        return time;
    }

    public static String formattedShortTime(String time)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        time = formatter.format(new Date());
        return time;
    }
}
