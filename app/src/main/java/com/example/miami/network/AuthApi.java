package com.example.miami.network;

import com.example.miami.network.LoginApi;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.Retrofit;

public class AuthApi {
    private final LoginApi mLoginApi;
    private final OkHttpClient mOkHttpClient;

    public AuthApi() {
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

        mLoginApi = retrofit.create(LoginApi.class);
    }

    public LoginApi getmLoginApi() {
        return mLoginApi;
    }
}
