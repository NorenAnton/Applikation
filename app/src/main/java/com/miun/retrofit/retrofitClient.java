package com.miun.retrofit;

import com.miun.retrofit.models.Message;
import com.miun.retrofit.models.MessageModelPost;
import com.miun.retrofit.models.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface retrofitClient {

    @GET("persons")
    Call<List<Person> >getAllPersons();

    @GET("messages/{persId}")
    Call<List<Message> >getMessages(@Path("persId") String persId);


    @POST("messages/add")
    Call<MessageModelPost> storeMessage(@Body MessageModelPost message);

}
