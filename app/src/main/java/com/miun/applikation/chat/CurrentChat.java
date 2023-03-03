package com.miun.applikation.chat;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class CurrentChat {


    int chatId;
    String name;
    String date;
    String message;

    public CurrentChat(int chatId, String name, String message, Timestamp time){
        this.chatId = chatId;
        this.name = name;
        setDate(time);
        this.message = message;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
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
