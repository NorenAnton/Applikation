package com.miun.retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestInterface<T> {

    public RequestInterface(Call<T> container, final RequestReader<T> func) {
        container.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                System.out.println("YES!");
                func.run(response.body());
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                System.err.println("sendRequest ERROR: " + t);

            }
        });
    }

    public void sendRequest(Call<T> container, final RequestReader<T> func ) {


    }
}
