package com.example.miami;

import android.app.Application;
import android.content.Context;

import com.example.miami.network.AuthApi;
import com.example.miami.repository.AuthRepo;

public class ApplicationModified extends Application {
    private AuthApi authApi;
    private AuthRepo mAuthRepo;

    @Override
    public void onCreate() {
        super.onCreate();
        authApi = new AuthApi();
        mAuthRepo = new AuthRepo(authApi);
    }

    public AuthRepo getAuthRepo() {
        return mAuthRepo;
    }

    public AuthApi getApis() {
        return authApi;
    }

    public static ApplicationModified from(Context context) {
        return (ApplicationModified) context.getApplicationContext();
    }
}
