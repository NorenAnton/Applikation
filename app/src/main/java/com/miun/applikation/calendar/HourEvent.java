package com.miun.applikation.calendar;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;

public class HourEvent {
    SimpleDateFormat time;
    ArrayList<Event> events;

    public HourEvent(SimpleDateFormat time, ArrayList<Event> events)
    {
        this.time = time;
        this.events = events;
    }

    public SimpleDateFormat getTime()
    {
        return time;
    }

    public void setTime(SimpleDateFormat time)
    {
        this.time = time;
    }

    public ArrayList<Event> getEvents()
    {
        return events;
    }

    public void setEvents(ArrayList<Event> events)
    {
        this.events = events;
    }
}
