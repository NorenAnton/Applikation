package com.miun.applikation.utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.miun.applikation.misc.User;
import com.miun.applikation.chat.CurrentChat;
import com.miun.applikation.log.CurrentLog;
import com.miun.retrofit.InterfaceAPI;
import com.miun.retrofit.models.Person;
import com.miun.retrofit.retrofitClient;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ChatLogUtils {
    public List<User> users = new ArrayList<>();
    public List<CurrentChat> chatter = new ArrayList<>();
    public List<CurrentLog> logger = new ArrayList<>();
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

    public void fillList(){
        String baseurl = "http://10.82.227.191:8080/";

        retrofitClient client = new InterfaceAPI(baseurl).createRetrofitClient();

        Call<List<Person>> caller = client.getAllPersons();

        caller.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                List<Person> APIdata = response.body();
                for (Person p : APIdata){
                    System.out.println(p.getFname());
                    users.add(new User(p.getFname(), p.getLname(), p.getId()));
                }
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                System.out.println("error");
            }
        });
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
