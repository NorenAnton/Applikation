package com.miun.applikation.utils;

import com.miun.applikation.misc.User;
import com.miun.applikation.chat.CurrentChat;
import com.miun.applikation.log.CurrentLog;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ChatLogUtils {
    public List<User> users = new ArrayList<>();
    public List<CurrentChat> chatter = new ArrayList<>();
    public List<CurrentLog> logger = new ArrayList<>();

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
    }

    public void fillChat(){
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
    }

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

    }
}
