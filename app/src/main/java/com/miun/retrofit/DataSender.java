package com.miun.retrofit;

import com.miun.retrofit.models.MessageModelPost;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataSender {

    retrofitClient client;

    public DataSender(retrofitClient client) {
        this.client = client;
    }

    public void storeMessage(int fromID, int toID, String text, String imageUrl) {
        MessageModelPost newMessage = new MessageModelPost(fromID, toID, text, imageUrl);
        Call<MessageModelPost> caller = client.storeMessage(newMessage);

        caller.enqueue(new Callback<MessageModelPost>() {
            @Override
            public void onResponse(Call<MessageModelPost> call, Response<MessageModelPost> response) {
                System.out.println("POST status: " + response.isSuccessful());
                System.out.println("Response code: " + response.code());
            }

            @Override
            public void onFailure(Call<MessageModelPost> call, Throwable t) {
                System.out.println("ERROR: " + t);
            }
        });

    }
}
