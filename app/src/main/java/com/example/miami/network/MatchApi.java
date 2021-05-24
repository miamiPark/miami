package com.example.miami.network;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MatchApi {
    private final OkHttpClient mOkHttpClient;
    private MatchRequestApi mMatchRequestApi;

    public MatchApi() {
        mOkHttpClient = new OkHttpClient()
                .newBuilder()
                .build();
    }

    public MatchRequestApi getMatchRequestApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(new HttpUrl.Builder().scheme("http")
                        .host("35.228.245.99")
                        .build())
                .client(mOkHttpClient)
                .build();

        mMatchRequestApi = retrofit.create(MatchRequestApi.class);

        return mMatchRequestApi;
    }
}
