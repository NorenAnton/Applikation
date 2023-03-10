package com.miun.applikation.utils;

import android.widget.TextView;

import com.miun.applikation.calendar.HourEvent;
import com.miun.applikation.calendar.NewEvent;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CalendarUtils {
    NewEvent newEvent = new NewEvent();
    public static LocalDate selectedDate;
    List<HourEvent> hourEvents;

    public static String formattedShortTime(LocalTime time)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return time.format(formatter);
    }

    public static String formattedTime(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return time.format(formatter);
    }

    public void FillCalendar(){
        hourEvents.add();
    }
}
