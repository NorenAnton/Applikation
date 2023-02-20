package com.miun.applikation;

import java.util.ArrayList;
import java.util.List;

public class Fillers {
    List<User> users = new ArrayList<>();

    List<CurrentChat> chatter = new ArrayList<>();

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
}
