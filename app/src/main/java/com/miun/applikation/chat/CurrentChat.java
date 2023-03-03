package com.miun.applikation.chat;

import android.media.Image;
import android.net.Uri;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CurrentChat {


    private int chatId;
    private String name;

    private Uri image;

    private String date;

   private String message;

    public CurrentChat(int chatId, String name, String message, Uri image){
        this.chatId = chatId;
        this.name = name;
        setDate(date);
        this.message = message;
        this.image = image;
    }

    public CurrentChat(int chatId, String name, String message){
        this.chatId = chatId;
        this.name = name;
        setDate(date);
        this.message = message;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public Uri getImage() {
        return image;
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
