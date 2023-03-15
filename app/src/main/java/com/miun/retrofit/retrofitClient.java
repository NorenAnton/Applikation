package com.miun.retrofit;

import com.miun.retrofit.models.CalenderEventModel;
import com.miun.retrofit.models.LogModel;
import com.miun.retrofit.models.Message;
import com.miun.retrofit.models.MessageModelPost;
import com.miun.retrofit.models.Person;
import com.miun.retrofit.models.ReparationModel;
import com.miun.retrofit.models.ReservationModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface retrofitClient {

    @GET("person/all")
    Call<List<Person> >getAllPersons();

    @GET("person")
    Call<Person> getPerson (@Query("Id") String Id);

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

    @GET("reservation/all")
    Call<List<ReservationModel> >getAllReservation();

    @GET("reservation")
    Call<List<ReservationModel> >getReservationByPersonId(@Query("persId") String id);

    @GET("log")
    Call<List<LogModel>> getLogByPersonId(@Query("persId") String id);

    @GET("calender/all")
    Call<List<CalenderEventModel>> getAllCalenderEvent();


    @POST("messages/add")
    Call<MessageModelPost> addMessage(@Body MessageModelPost message);

    @POST("reparation/add")
    Call<ReparationModel> addReparation(@Body ReparationModel newReparation);

    @POST("log/add")
    Call<LogModel> addLogEntry(@Body LogModel newLogEntry);

    @POST("calender/add")
    Call<CalenderEventModel> addCalenderEvent(@Body CalenderEventModel newCalenderEvent);


    @DELETE("calender/delete")
    Call<Integer> deleteCalenderEvent(@Query("id") Integer id);


    @PUT("reparation/change")
    Call<ReparationModel> changeReparation(@Body ReparationModel newReparation);

}
