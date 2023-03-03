package com.miun.applikation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.miun.retrofit.MessageModelPost;
import com.miun.retrofit.PersonApiCall;
import com.miun.retrofit.PersonModel;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button btn_Chatt, btn_Log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("WAT");
        Log.d("pop", "hej");

        //Retrofit Builder
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.82.252.220:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //Instance for interface
        PersonApiCall call1 = retrofit.create(PersonApiCall.class);
        Call<PersonModel> call = call1.getPerson();

        call.enqueue(new Callback<PersonModel>() {
            @Override
            public void onResponse(Call<PersonModel> call, Response<PersonModel> response) {
                //check for response
                if(response.code() != 200){
                    System.out.println("Check the connection");
                    return;
                }
                //Get the data into prints
                System.out.println(response.body().getFname());

            }

            @Override
            public void onFailure(Call<PersonModel> call, Throwable t) {

            }
        });



        MessageModelPost newMessage =
                new MessageModelPost(1, 2, "HEEEEJ", "fancystuff.png");


        btn_Chatt = findViewById(R.id.chat);
        btn_Log = findViewById(R.id.logg);

        btn_Chatt.setOnClickListener(view -> {
            Intent intentChat = new Intent(MainActivity.this, Chat.class);
            startActivity(intentChat);
        });

        btn_Log.setOnClickListener(view -> {
            Intent intentLog = new Intent(MainActivity.this, Log.class);
            startActivity(intentLog);
        });

    }

}