package com.miun.retrofit;

import com.miun.retrofit.models.Message;
import com.miun.retrofit.models.MessageModelPost;
import com.miun.retrofit.models.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

// Publik API "https://jsonplaceholder.typicode.com/";
//cd C:\Users\anton\Documents\db-derby-10.15.2.0-bin\db-derby-10.15.2.0-bin\bin
//startNetworkServer.bat

public interface retrofitClient {

    @GET("persons")
    Call<List<Person> >getAllPersons();

    @GET("message/{}")
    Call<List<Message> >getMessages();


    @POST("messages/add")
    Call<MessageModelPost> storeMessage(@Body MessageModelPost message);

}
