package com.example.miami.network;

import com.example.miami.models.match.Like;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MatchApi {
    private LikeApi mLikeApi;
    private final OkHttpClient mOkHttpClient;

    public MatchApi() {
        mOkHttpClient = new OkHttpClient()
                .newBuilder()
                .build();
    }
    public LikeApi getLikeApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(new HttpUrl.Builder().scheme("http")
                        .host("35.228.245.99")
                        .build())
                .client(mOkHttpClient)
                .build();

        mLikeApi = retrofit.create(LikeApi.class);
        return mLikeApi;
    }

    public LikeApi getmLileApi() {
        return mLikeApi;
    }
}
