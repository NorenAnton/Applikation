package com.miun.retrofit;

import com.miun.retrofit.models.Message;
import com.miun.retrofit.models.MessageModelPost;
import com.miun.retrofit.models.Person;
import com.miun.retrofit.models.ReparationModel;
import com.miun.retrofit.models.ReservationModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface retrofitClient {

    @GET("persons")
    Call<List<Person> >getAllPersons();


    @GET("person/admin")
    Call<Person> getAdmin();

    @GET("messages/{persId}")
    Call<List<Message> >getMessages(@Path("persId") String persId);

    @GET("messages/all")
    Call<List<Message> >getAllMessages();

    @GET("reparation/all")
    Call<List<ReparationModel> >getAllReparations();

    @GET("reparation")
    Call<List<ReparationModel> >getReparationById(@Query("persId") String id);

    @GET("reservation")
    Call<List<ReservationModel> >getAllReservation();



    @POST("messages/add")
    Call<MessageModelPost> addMessage(@Body MessageModelPost message);

}
