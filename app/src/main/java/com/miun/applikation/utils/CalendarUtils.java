package com.miun.applikation.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.lang.String;

public class CalendarUtils {
    public static LocalDate selectedDate;

    public static String formattedDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMMM dd");
        return date.format(formatter);
    }

    public static String formattedTime(LocalTime time)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return time.format(formatter);
    }

    public static String formattedShortTime(LocalTime time)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return time.format(formatter);
    }
}
