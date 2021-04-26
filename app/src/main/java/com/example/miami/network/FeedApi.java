package com.example.miami.network;

import android.content.Context;

import com.example.miami.ApplicationModified;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class FeedApi {
    private final UsersApi usersApi;
    private final OkHttpClient mOkHttpClient;
    private ApplicationModified MyContext;

    public FeedApi() {
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

        usersApi = retrofit.create(UsersApi.class);
    }


    private static class SessionCookieJar implements CookieJar {

        private List<Cookie> cookies;

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            if (url.encodedPath().endsWith("login")) {
                this.cookies = new ArrayList<>(cookies);
            }
        }


        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            if (!url.encodedPath().endsWith("login") && cookies != null) {

                return cookies;
            }
            return Collections.emptyList();
        }
    }

    public void setContext(Context context) {
        MyContext =  (ApplicationModified) context;
    }

    public UsersApi getUsersApi() {
        return usersApi;
    }
}
