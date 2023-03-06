package com.miun.applikation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.miun.retrofit.InterfaceAPI;
import com.miun.retrofit.models.MessageModelPost;
import com.miun.retrofit.models.Person;
import com.miun.retrofit.retrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.util.Log;

public class MainActivity extends AppCompatActivity {

    Button btn_Chatt, btn_Log;
    public List<Person> classResponseData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // API TEST--------------------------------------------------
        System.out.println("WAT");
        Log.d("pop", "hej");

        String baseUrl1 = "http://10.82.237.144:8080/";
        String baseUrl2 = "http://192.168.0.145:8080/";
        String baseUrl3 = "http://10.82.252.220:8080/";
        retrofitClient client = new InterfaceAPI(baseUrl3).createRetrofitClient();
        MessageModelPost testMessages = new MessageModelPost(1, 2, "HEEEEJ", "fancystuff.png");
        //API_responseTest(client);

        //API_sendTest(client, testMessages);
        // API TEST--------------------------------------------------
        //admin

        Call<Person> caller = client.getAdmin();
        caller.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Person p = response.body();
                System.out.println(p.getFname());
                System.out.println("TEST OF ADMIN GET CALL");
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
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
/*
    private void API_sendTest(retrofitClient client, MessageModelPost newMessages) {
        Call<MessageModelPost> caller = client.storeMessage(newMessages);
        caller.enqueue(new Callback<MessageModelPost>() {
            @Override
            public void onResponse(Call<MessageModelPost> call, Response<MessageModelPost> response) {
                System.out.println("DATA ADDED!");

                MessageModelPost responseFromAPI = response.body();
                System.out.println(response.isSuccessful());
                System.out.println(response.errorBody());
                System.out.println(response.code());
                //System.out.println(response.body().getPersId());

            }

            @Override
            public void onFailure(Call<MessageModelPost> call, Throwable t) {
                System.out.println("ERROR: " + t);
            }
        });
    }

    private void API_responseTest(retrofitClient client) {

        Call<List<Person>> caller = client.getAllPersons();
        //MutableLiveData<List<PersonTemp>> submissions = new MutableLiveData<>();

        caller.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                System.out.println("DATA! HURRA");

                // Anteckningar: Det går inte att lägga datan direkt i klassen variabler
                //classResponseData = response.body();

                List<Person> responsData = response.body();
                for (Person p : responsData) {
                    System.out.println(p.getId());
                    System.out.println(p.getFname());
                    System.out.println("-----------------------------------------");
                }
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                System.err.println("ERROR, ingen kontakt" + t);

            }
        });

    }
*/
}