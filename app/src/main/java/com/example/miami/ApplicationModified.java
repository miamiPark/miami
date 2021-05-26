package com.example.miami;

import android.app.Application;
import android.content.Context;

import com.example.miami.network.AuthApi;

import com.example.miami.network.ProfileApi;
import com.example.miami.network.RegistrationApi;
import com.example.miami.repository.AuthRepo;
import com.example.miami.repository.ProfileRepo;
import com.example.miami.repository.RegistrationRepo;

import com.example.miami.network.FeedApi;
import com.example.miami.network.UsersApi;
import com.example.miami.repository.AuthRepo;
import com.example.miami.repository.FeedRepo;

import retrofit2.Call;


public class ApplicationModified extends Application {
    private AuthApi authApi;
    private FeedApi feedApi;
    private ProfileApi profileApi;

    private AuthRepo mAuthRepo;

    private RegistrationRepo mRegistrationRepo;
    private RegistrationApi mRegistrationApi;
    private ProfileRepo profileRepo;

    private FeedRepo mFeedRepo;


    private String cookie;


    @Override
    public void onCreate() {
        super.onCreate();

        authApi = new AuthApi();
        feedApi = new FeedApi();
        profileApi = new ProfileApi();

        feedApi.setContext(this);
        profileApi.setContext(this);

        mAuthRepo = new AuthRepo(authApi);
        profileRepo = new ProfileRepo(profileApi);


        mRegistrationApi = new RegistrationApi();
        mRegistrationRepo = new RegistrationRepo(mRegistrationApi);

        mFeedRepo = new FeedRepo(feedApi);

    }

    public AuthRepo getAuthRepo() {
        return mAuthRepo;
    }


    public RegistrationRepo getRegistrationRepo() {
        return mRegistrationRepo;
    }

    public FeedRepo getmFeedRepo() {
        return mFeedRepo;
    }

    public ProfileRepo getProfileRepo() {
        return profileRepo;
    }


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
