package com.example.miami.network;

import android.content.Context;

import com.example.miami.ApplicationModified;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MatchApi {
    private final OkHttpClient mOkHttpClient;
    private MatchRequestApi mMatchRequestApi;
    private ApplicationModified MyContext;

    public MatchApi() {
        mOkHttpClient = new OkHttpClient()
                .newBuilder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        final Request original = chain.request();

                        final Request authorized = original.newBuilder()
                                .addHeader("Cookie", MyContext.getCookie() + ";")
                                .build();

                        return chain.proceed(authorized);
                    }
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(new HttpUrl.Builder().scheme("http")
                        .host("35.228.245.99")
                        .build())
                .client(mOkHttpClient)
                .build();


        mMatchRequestApi = retrofit.create(MatchRequestApi.class);
    }

    public void setContext(Context context) {
        MyContext =  (ApplicationModified) context;
    }

    public MatchRequestApi getMatchApi() {
        return mMatchRequestApi;
    }
}
