package com.miun.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

//cd C:\Users\anton\Documents\db-derby-10.15.2.0-bin\db-derby-10.15.2.0-bin\bin
//startNetworkServer.bat

public interface retrofitClient {

    //@GET("posts")
    @GET("person?id=2")
    Call<PersonTemp>getAllPersons();
}
