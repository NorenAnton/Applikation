package com.miun.applikation.utils;

import com.miun.applikation.misc.User;
import com.miun.applikation.chat.CurrentChat;
import com.miun.applikation.log.CurrentLog;

import java.util.ArrayList;
import java.util.List;

public class ChatLogUtils {
    public static List<User> users = new ArrayList<>();
    public static List<CurrentChat> chatter = new ArrayList<>();
    public static List<CurrentLog> logger = new ArrayList<>();

    public static void fillList() {
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

    public static void fillChat(){
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
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world"));
        chatter.add(new CurrentChat(0, "Anton Noren", "Hello world"));
    }

    public static void fillLog(){
        logger.add(new CurrentLog(0, "Gillian Persson", "Janne"));
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
        logger.add(new CurrentLog(0, "Anton Noren", "Janne"));

    }
}
