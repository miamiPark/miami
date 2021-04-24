package com.example.miami.repository;


import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.miami.ApplicationModified;
import com.example.miami.models.authorization.AuthProgress;
import com.example.miami.network.AuthApi;
import com.example.miami.network.LoginApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepo {
    private final AuthApi authApi;

    public AuthRepo(AuthApi api) {
        authApi = api;
    }

    public static AuthRepo getInstance(Context context) {
        return ApplicationModified.from(context).getAuthRepo();
    }

    private String mCurrentUser;
    private MutableLiveData<AuthProgress> mAuthProgress;

    public LiveData<AuthProgress> login(@NonNull String telephone, @NonNull String password) {
        if (TextUtils.equals(telephone, mCurrentUser) && mAuthProgress.getValue() == AuthProgress.IN_PROGRESS) {
            return mAuthProgress;
        } else if (!TextUtils.equals(telephone, mCurrentUser) && mAuthProgress != null) {
            mAuthProgress.postValue(AuthProgress.FAILED);
        }
        mCurrentUser = telephone;
        mAuthProgress = new MutableLiveData<>();
        login(mAuthProgress, telephone, password);
        return mAuthProgress;
    }

    private void login(final MutableLiveData<AuthProgress> progress, @NonNull final String telephone, @NonNull final String password) {
        LoginApi api = authApi.getmLoginApi();
        api.login(new LoginApi.UserLogin(telephone, password)).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.w("response", response.toString());
                if (response.isSuccessful()) {
                    progress.postValue(AuthProgress.SUCCESS);
                } else {
                    progress.postValue(AuthProgress.FAILED);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progress.postValue(AuthProgress.FAILED);
            }
        });
    }
}
