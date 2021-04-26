package com.example.miami.network;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class FeedApi {
    private final UsersApi usersApi;
    private final OkHttpClient mOkHttpClient;

    public FeedApi() {
        mOkHttpClient = new OkHttpClient()
                .newBuilder()
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(new HttpUrl.Builder().scheme("http")
                        .host("35.228.245.99")
                        .build())
                .client(mOkHttpClient)
                .build();

        usersApi = retrofit.create(UsersApi.class);
    }

    public UsersApi getUsersApi() {
        return usersApi;
    }
}
