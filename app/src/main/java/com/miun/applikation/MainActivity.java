package com.miun.applikation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.miun.retrofit.PersonTemp;
import com.miun.retrofit.retrofitClient;

import java.util.List;

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

        // TESTAR klient, bara så ni vet!
        //retrofitClient client = setUpClient("https://jsonplaceholder.typicode.com/");
        // 10.0.2.2
        // 10.82.237.144
        retrofitClient client = setUpClient("http://10.82.237.144:8080/");
        Call<PersonTemp > caller = client.getAllPersons();
        caller.enqueue(new Callback<PersonTemp>() {
            @Override
            public void onResponse(Call<PersonTemp> call, Response<PersonTemp> response) {
                System.out.println("DATA! HURRA");

                PersonTemp responsData = response.body();
                //for (PersonTemp p : responsData) {
                    System.out.println(responsData.getId());
                    System.out.println(responsData.getFname());
                    /*System.out.println(p.getId());
                    System.out.println(p.getUserId());
                    System.out.println(p.getTitle());
                    System.out.println(p.getText());*/
                    System.out.println("-----------------------------------------");
                //}
            }
            @Override
            public void onFailure(Call<PersonTemp> call, Throwable t) {
                System.err.println("ERROR, ingen kontakt" + t);
            }
        });


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

    private retrofitClient setUpClient(String UrlBase) {

        // Create builder
        Retrofit bob = new Retrofit.Builder()
                .baseUrl(UrlBase)
                .addConverterFactory(GsonConverterFactory.create()).build();

        return bob.create(retrofitClient.class);
    }

}