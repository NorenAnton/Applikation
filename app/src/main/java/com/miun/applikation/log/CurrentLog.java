package com.miun.applikation.log;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class CurrentLog {


    int logId;
    String name;

    String date;

    String message;

    public CurrentLog(int logId, String name, String message, Timestamp time){
        this.logId = logId;
        this.name = name;
        setDate(time);
        this.message = message;
    }

    public int getChatId() {
        return logId;
    }

    public void setChatId(int chatId) {
        this.logId = chatId;
    }
    public String getDate() {
        return date;
    }

    public void setDate(Timestamp time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("Europe/Stockholm"));
        this.date = sdf.format(time);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
