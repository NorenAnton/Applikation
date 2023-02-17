package com.miun.applikation;

public class currentChat {


    int chatId;
    String name;

    String date;

    String time;

    String message;

    public currentChat(int chatId, String name, String date, String time, String message){
        this.chatId = chatId;
        this.name = name;
        this.date = date;
        this.time = time;
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

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
