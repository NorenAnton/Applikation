package com.miun.applikation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.miun.retrofit.ClientCreator;
import com.miun.retrofit.models.MessageModelPost;
import com.miun.retrofit.models.PersonTemp;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("WAT");
        Log.d("pop", "hej");

        //retrofitClient client = setUpClient("https://jsonplaceholder.typicode.com/");
        // Instance for retrofit api class
        //retrofitClient client = setUpClient("http://10.82.237.144:8080/");
        retrofitClient client = new ClientCreator("http://10.82.237.144:8080/").createRetrofitClient();
        API_responseTest(client);

        MessageModelPost newMessage =
                new MessageModelPost(1, 2, "HEEEEJ", "fancystuff.png");


        //  data = datareader.GET_data()
        // view
        // R

        // layouts:
        // main
        // chat
        // log
        // kalander

        // GET_data(appElement)
        // user newUser
        /*Call<MessageModelPost> caller = client.storeMessage(newMessage);
        caller.enqueue(new Callback<MessageModelPost>() {
            @Override
            public void onResponse(Call<MessageModelPost> call, Response<MessageModelPost> response) {
                System.out.println("DATA ADDED!");

                MessageModelPost responseFromAPI = response.body();
                System.out.println(response.isSuccessful());
                System.out.println(response.errorBody());
                System.out.println(response.code());
                //System.out.println(response.body().getPersId());

                // newUser = *lägg i data här*
            }

            @Override
            public void onFailure(Call<MessageModelPost> call, Throwable t) {
                System.out.println("ERROR: " + t);
            }
        });*/



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

    // !TA BORT!
    private retrofitClient setUpClient(String UrlBase) {

        // Create builder
        Retrofit bob = new Retrofit.Builder()
                .baseUrl(UrlBase)
                .addConverterFactory(GsonConverterFactory.create()).build();

        return bob.create(retrofitClient.class);
    }

    private void API_responseTest(retrofitClient client) {

        Call<List<PersonTemp>> caller = client.getAllPersons();
        //MutableLiveData<List<PersonTemp>> submissions = new MutableLiveData<>();


        caller.enqueue(new Callback<List<PersonTemp>>() {
            @Override
            public void onResponse(Call<List<PersonTemp>> call, Response<List<PersonTemp>> response) {
                System.out.println("DATA! HURRA");

                List<PersonTemp> responsData = response.body();
                for (PersonTemp p : responsData) {
                    System.out.println(p.getId());
                    System.out.println(p.getFname());
                    System.out.println("-----------------------------------------");
                }
            }
            @Override
            public void onFailure(Call<List<PersonTemp>> call, Throwable t) {
                System.err.println("ERROR, ingen kontakt" + t);
            }
        });
    }

}