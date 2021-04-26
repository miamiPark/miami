package com.example.miami.network;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RegistrationApi {
    private UploadAvatarApi mUploadAvatarApi;
    private RegistrationRequestApi mRegistrationRequestApi;
    private OkHttpClient mOkHttpClient;

    public RegistrationApi() {
        mOkHttpClient = new OkHttpClient()
                .newBuilder()
                .build();
    }

    public UploadAvatarApi getUploadAvatarApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(new HttpUrl.Builder().scheme("http")
                        .host("35.228.245.99")
                        .build())
                .client(mOkHttpClient)
                .build();

        mUploadAvatarApi = retrofit.create(UploadAvatarApi.class);

        return mUploadAvatarApi;
    }

    public RegistrationRequestApi getRegistrationRequestApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(new HttpUrl.Builder().scheme("http")
                        .host("35.228.245.99")
                        .build())
                .client(mOkHttpClient)
                .build();

        mRegistrationRequestApi = retrofit.create(RegistrationRequestApi.class);

        return mRegistrationRequestApi;
    }
}
