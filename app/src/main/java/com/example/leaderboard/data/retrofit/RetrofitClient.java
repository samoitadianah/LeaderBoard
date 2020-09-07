package com.example.leaderboard.data.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://gadsapi.herokuapp.com";
    private static final String GOOGLE_FORMS_URL = "https://docs.google.com/forms/d/e/";
    private static Retrofit retrofit;
    private static Retrofit retrofit_google;

    public static Retrofit getRetrofitInstance() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit;
    }

    public static Retrofit getGoogleFormsRetrofitInstance() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (retrofit_google == null) {
            retrofit_google = new Retrofit.Builder()
                    .baseUrl(GOOGLE_FORMS_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit_google;
    }
}
