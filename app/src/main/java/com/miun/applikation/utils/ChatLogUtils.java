package com.miun.applikation.utils;

import com.miun.applikation.misc.User;
import com.miun.applikation.chat.CurrentChat;
import com.miun.applikation.log.CurrentLog;

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
        chatter.add(new CurrentChat(0, "Gillian Persson", "Hello world"));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world"));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world"));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world"));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world"));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world"));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world"));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world"));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world"));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world"));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world"));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world"));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world"));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world"));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world"));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world"));
        chatter.add(new CurrentChat(0, "Vincent Johansson", "Hello world"));
    }

    public void fillLog(){
        logger.add(new CurrentLog(0, "Gillian Persson", "VÄLKOMMEN"));
        logger.add(new CurrentLog(0, "Anton Noren", "Hello world"));
        logger.add(new CurrentLog(0, "Anton Noren", "Hello world"));
        logger.add(new CurrentLog(0, "Anton Noren", "Hello world"));
        logger.add(new CurrentLog(0, "Anton Noren", "Hello world"));
        logger.add(new CurrentLog(0, "Anton Noren", "Hello world"));
        logger.add(new CurrentLog(0, "Anton Noren", "Hello world"));
        logger.add(new CurrentLog(0, "Anton Noren", "Hello world"));
        logger.add(new CurrentLog(0, "Anton Noren", "Hello world"));
        logger.add(new CurrentLog(0, "Anton Noren", "Hello world"));
        logger.add(new CurrentLog(0, "Anton Noren", "Hello world"));
        logger.add(new CurrentLog(0, "Anton Noren", "Hello world"));
        logger.add(new CurrentLog(0, "Anton Noren", "Hello world"));
        logger.add(new CurrentLog(0, "Anton Noren", "Hello world"));
        logger.add(new CurrentLog(0, "Vincent Johansson", "HELLO THERE"));

    }
}
