package com.miun.retrofit;

import android.util.Log;

import com.miun.retrofit.models.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// ____Anteckningar____
// Denna metod funkade inte att hämta data från API. På grund av okända andledningar
// så exekveras inte 'enqueue' så 'return APIdata;' kommer alltid att vara NULL. Har
// testat om det har med trådar att göra men efter ha låtat main tråden stannat 10sek
// så kördes 'enqueue' iallafall inte.
public class PersonRequest {

    retrofitClient client;
    List<Person> APIdata;

    public PersonRequest(retrofitClient client) {
        this.client = client;
    }

    public List<Person> getData() {
        Call<List<Person>> caller = client.getAllPersons();
        System.out.println("getData");

        caller.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                System.out.println("DATA! HURRA");
                APIdata = response.body();
                for (Person p : APIdata) {
                    System.out.println(p.getId());
                    System.out.println(p.getFname());
                }
            }
            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                System.err.println("ERROR, ingen kontakt" + t);
            }
        });
        Log.d("stuff", "ingen async, nehepp");
        return APIdata;
    }
}
