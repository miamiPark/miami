package com.example.miami.viewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.miami.models.authorization.AuthProgress;
import com.example.miami.models.authorization.LoginData;
import com.example.miami.models.authorization.LoginState;
import com.example.miami.repository.AuthRepo;

public class AuthorizationViewModel extends AndroidViewModel {

    private LoginData mLastLoginData = new LoginData("", "");

    private MediatorLiveData<LoginState> mLoginState = new MediatorLiveData<>();

    public AuthorizationViewModel(@NonNull Application application){
        super(application);
        mLoginState.setValue(LoginState.NONE);
    }

    public LiveData<LoginState> getProgress() {
        return mLoginState;
    }

    public void login(String login, String password) {
        LoginData last = mLastLoginData;
        LoginData loginData = new LoginData(login, password);
        mLastLoginData = loginData;

        if (!loginData.isValid()) {
            mLoginState.postValue(LoginState.ERROR);
        } else if (last != null && last.equals(loginData)) {
            Log.w("LoginViewModel", "Ignoring duplicate request with login data");
        } else if (mLoginState.getValue() != LoginState.IN_PROGRESS) {
            requestLogin(loginData);
        }
    }


    private void requestLogin(final LoginData loginData) {
        mLoginState.postValue(LoginState.IN_PROGRESS);
        final LiveData<AuthProgress> progressLiveData = AuthRepo.getInstance(getApplication())
                    .login(loginData.getLogin(), loginData.getPassword());

        mLoginState.addSource(progressLiveData, new Observer<AuthProgress>() {
            @Override
            public void onChanged(AuthProgress authProgress) {
                if (authProgress == AuthProgress.SUCCESS) {
                    mLoginState.postValue(LoginState.SUCCESS);
                    mLoginState.removeSource(progressLiveData);
                } else if (authProgress == AuthProgress.FAILED) {
                    mLoginState.postValue(LoginState.FAILED);
                    mLoginState.removeSource(progressLiveData);
                }
            }
        });
    }

}
