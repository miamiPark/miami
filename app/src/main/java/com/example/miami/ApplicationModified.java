package com.example.miami;

import android.app.Application;
import android.content.Context;

import com.example.miami.network.AuthApi;
import com.example.miami.network.RegistrationApi;
import com.example.miami.repository.AuthRepo;
import com.example.miami.repository.RegistrationRepo;

public class ApplicationModified extends Application {
    private AuthApi authApi;
    private AuthRepo mAuthRepo;
    private RegistrationRepo mRegistrationRepo;
    private RegistrationApi mRegistrationApi;

    @Override
    public void onCreate() {
        super.onCreate();

        authApi = new AuthApi();
        mAuthRepo = new AuthRepo(authApi);

        mRegistrationApi = new RegistrationApi();
        mRegistrationRepo = new RegistrationRepo(mRegistrationApi);
    }

    public AuthRepo getAuthRepo() {
        return mAuthRepo;
    }

    public RegistrationRepo getRegistrationRepo() {
        return mRegistrationRepo;
    }

    public AuthApi getApis() {
        return authApi;
    }

    public static ApplicationModified from(Context context) {
        return (ApplicationModified) context.getApplicationContext();
    }
}
