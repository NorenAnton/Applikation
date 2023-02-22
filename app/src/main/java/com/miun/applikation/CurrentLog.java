package com.miun.applikation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CurrentLog {


    int logId;
    String name;

    String date;

    String message;

    public CurrentLog(int chatId, String name, String message){
        this.logId = chatId;
        this.name = name;
        setDate(date);
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

    public void setDate(String dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        dateTime = sdf.format(new Date());
        this.date = dateTime;
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