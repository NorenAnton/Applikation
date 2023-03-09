package com.miun.applikation.utils;

import android.net.Uri;

import com.miun.applikation.chat.CurrentChat;
import com.miun.applikation.misc.User;
import com.miun.applikation.log.CurrentLog;
import com.miun.retrofit.models.LogModel;
import com.miun.retrofit.models.Message;
import com.miun.retrofit.models.Person;


import java.sql.Timestamp;
import java.util.List;

public class ChatLogUtils {

/*
    public void fillList() {
        users.add(new User("Vincent", "Johansson", 0));
        users.add(new User("Gillian", "Persson", 1));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
    }*/

    public void fillList(List<Person> APIdata, List<User> users){
        int andersID = 1;
        for (Person p : APIdata){
            if (p.getId() == andersID) continue;
            users.add(new User(p.getFname(), p.getLname(), p.getId()));
        }
    }
    /*
    public void fillChat(int id){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world",null, timestamp));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world",null, timestamp));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world", null, timestamp));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world", null, timestamp));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world", null, timestamp));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world", null, timestamp));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world", null, timestamp));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world", null, timestamp));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world", null, timestamp));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world", null, timestamp));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world", null, timestamp));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world", null, timestamp));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world", null, timestamp));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world", null, timestamp));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world", null, timestamp));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world", null, timestamp));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world", null, timestamp));
    }*/

    public void fillChat(List<Message> APIdata, List<CurrentChat> chatter, String name){
        for (Message m: APIdata){
            int andersID = 1;
            String sender = "Anders Andersson";
            if(m.getPerson_id() != andersID){
                sender = name;
            }
            if(m.getImage() != null && m.getTimestamp() != null) {
                chatter.add(new CurrentChat(m.getPerson_id(), sender, m.getText(), Uri.parse(m.getImage()), m.getTimestamp()));
            }
            else if(m.getTimestamp() != null){
                chatter.add(new CurrentChat(m.getPerson_id(), sender, m.getText(), null, m.getTimestamp()));
            }
            else if(m.getImage() != null){
                chatter.add(new CurrentChat(m.getPerson_id(), sender, m.getText(), Uri.parse(m.getImage()), new Timestamp(System.currentTimeMillis())));
            }
            else{
                chatter.add(new CurrentChat(m.getPerson_id(), sender, m.getText(), null, new Timestamp(System.currentTimeMillis())));
            }
        }
    }
/*
    public void fillLog(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        logger.add(new CurrentLog(0, "Gillian Persson", "Janne", timestamp));
        logger.add(new CurrentLog(0, "Anton Noren", "Hello world", timestamp));
        logger.add(new CurrentLog(0, "Anton Noren", "Hello world", timestamp));
        logger.add(new CurrentLog(0, "Anton Noren", "Hello world", timestamp));
        logger.add(new CurrentLog(0, "Anton Noren", "Hello world", timestamp));
        logger.add(new CurrentLog(0, "Anton Noren", "Hello world", timestamp));
        logger.add(new CurrentLog(0, "Anton Noren", "Hello world", timestamp));
        logger.add(new CurrentLog(0, "Anton Noren", "Hello world", timestamp));
        logger.add(new CurrentLog(0, "Anton Noren", "Hello world", timestamp));
        logger.add(new CurrentLog(0, "Anton Noren", "Hello world", timestamp));
        logger.add(new CurrentLog(0, "Anton Noren", "Hello world", timestamp));
        logger.add(new CurrentLog(0, "Anton Noren", "Hello world", timestamp));
        logger.add(new CurrentLog(0, "Anton Noren", "Hello world", timestamp));
        logger.add(new CurrentLog(0, "Anton Noren", "Hello world", timestamp));
        logger.add(new CurrentLog(0, "Anton Noren", "Janne", timestamp));
    }*/

    public void fillLog(List<LogModel> APIdata, List<CurrentLog> logger, String s){
        if(APIdata != null) {
            for (LogModel l : APIdata) {
                if(l.getLogTimestamp() != null) {
                    logger.add(new CurrentLog(l.getPersonId(), "Anders Andersson", l.getText(), l.getLogTimestamp()));
                }
                else {
                    logger.add(new CurrentLog(l.getPersonId(), "Anders Andersson", l.getText(), new Timestamp(System.currentTimeMillis())));
                }
            }
        }
    }
}
