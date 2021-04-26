package com.example.miami;

import android.app.Application;
import android.content.Context;

import com.example.miami.network.AuthApi;
import com.example.miami.network.FeedApi;
import com.example.miami.network.UsersApi;
import com.example.miami.repository.AuthRepo;
import com.example.miami.repository.FeedRepo;

import retrofit2.Call;

public class ApplicationModified extends Application {
    private AuthApi authApi;
    private FeedApi feedApi;

    private AuthRepo mAuthRepo;
    private FeedRepo mFeedRepo;


    private String cookie;

    @Override
    public void onCreate() {
        super.onCreate();

        authApi = new AuthApi();
        feedApi = new FeedApi();


        mAuthRepo = new AuthRepo(authApi);
        mFeedRepo = new FeedRepo(feedApi);
    }

    public AuthRepo getAuthRepo() {
        return mAuthRepo;
    }

    public FeedRepo getmFeedRepo() { return mFeedRepo; }

    public AuthApi getApis() {
        return authApi;
    }

    public void setCookie(String cookies) {
        cookie = cookies;
    }

    public String getCookie() {
        return cookie;
    }

    public static ApplicationModified from(Context context) {
        return (ApplicationModified) context.getApplicationContext();
    }
}
