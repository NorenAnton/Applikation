package com.miun.applikation.calendar;

import java.time.LocalTime;
import java.util.ArrayList;

public class HourEvent {

    public ArrayList<HourEvent> hourEvents;
    private Integer eventID;
    private Integer personID;
    private String subject;
    private String freetext;
    private String date;
    private String startEndTime;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime hour;

    public HourEvent(Integer personID, String subject, String freetext, String date, LocalTime startTime, LocalTime endTime)
    {
        this.eventID = null;
        this.personID = personID;
        this.subject = subject;
        this.freetext = freetext;
        this.date = date;
        this.startEndTime = getStartEndTime();
    }

    public ArrayList<HourEvent> getHourEvents() {
        return hourEvents;
    }

    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

    public Integer getPersonID() {
        return personID;
    }

    public void setPersonID(Integer personID) {
        this.personID = personID;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getFreetext() {
        return freetext;
    }

    public void setFreetext(String freetext) {
        this.freetext = freetext;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getStartEndTime() {
        return startTime + " - " + endTime;
    }

    public void setStartEndTime(String startEndTime) {
        this.startEndTime = startEndTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }
}
