package com.miun.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PersonApiCall {
    //  fixed link                    variable link
    //  http://10.82.252.220:8080/        person?id=2
    @GET("person?id=2")
    Call<PersonModel> getPerson();
}
