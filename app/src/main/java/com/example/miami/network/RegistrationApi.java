package com.example.miami.network;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class RegistrationApi {
    private UploadAvatarApi mUploadAvatarApi;
    private OkHttpClient mOkHttpClient;

    public RegistrationApi() {
        mOkHttpClient = new OkHttpClient()
                .newBuilder()
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(new HttpUrl.Builder().scheme("http")
                        .host("35.228.245.99")
                        .build())
                .client(mOkHttpClient)
                .build();

        mUploadAvatarApi = retrofit.create(UploadAvatarApi.class);
    }

    public UploadAvatarApi getUploadAvatarApi() {
        return mUploadAvatarApi;
    }
}
