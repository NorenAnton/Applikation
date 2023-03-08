package com.miun.applikation.calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Event {
    public static ArrayList<Event> eventsList = new ArrayList<>();

    private String name;
    private String date;
    private SimpleDateFormat time;

    public Event(String name, String date, SimpleDateFormat time)
    {
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public SimpleDateFormat getTime()
    {
        return time;
    }

    public void setTime(SimpleDateFormat time)
    {
        this.time = time;
    }


    public static ArrayList<Event> eventsForDate(String date)
    {
        ArrayList<Event> events = new ArrayList<>();

        for(Event event : eventsList)
        {
            if(event.getDate().equals(date))
                events.add(event);
        }

        return events;
    }

    public static ArrayList<Event> eventsForDateAndTime(String date, SimpleDateFormat time)
    {
        ArrayList<Event> events = new ArrayList<>();

        for(Event event : eventsList)
        {
            int eventHour = event.time.getCalendar().get(Calendar.HOUR_OF_DAY);
            int cellHour = time.getCalendar().get(Calendar.HOUR_OF_DAY);
            if(event.getDate().equals(date) && eventHour == cellHour)
                events.add(event);
        }
        return events;
    }
}
