package com.miun.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// ____Anteckningar____
// TODO: Varje person måste hämta sin egen IP wifi, ska göra
//  en bat fil (eller java class) som sköter detta automatiskt.
//

public class ClientCreator {
    private String UrlBase;

    public ClientCreator(String UrlBase) {
        this.UrlBase = UrlBase;
    }

    public String getUrlBase() {
        return UrlBase;
    }

    public void setUrlBase(String urlBase) {
        this.UrlBase = urlBase;
    }

    public retrofitClient createRetrofitClient() {
        Retrofit bob = new Retrofit.Builder()
                .baseUrl(this.UrlBase)
                .addConverterFactory(GsonConverterFactory.create()).build();

        return bob.create(retrofitClient.class);
    }
}
