package com.example.miami.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.miami.models.registration.RegistrationData;
import com.example.miami.models.registration.RegistrationState;

public class RegistrationViewModel extends AndroidViewModel {

    private RegistrationData mRegistrationData = new RegistrationData();
    private MediatorLiveData<RegistrationState> mRegistrationState  = new MediatorLiveData<>();

    public RegistrationViewModel(@NonNull Application application) {
        super(application);

        mRegistrationState.setValue(RegistrationState.NONE);
    }

    public LiveData<RegistrationState> getProgress() {
        return mRegistrationState;
    }

    public void identity(String telephone, String password, String repeatPassword) {
        mRegistrationData.setIdentity(telephone, password, repeatPassword);

        if (!mRegistrationData.isValidIdentity()) {
            mRegistrationState.postValue(RegistrationState.ERROR);
            return;
        }

        mRegistrationState.postValue(RegistrationState.MIDDLEWARE_SUCCESS);

        // добавить фетч на номер телефона
    }

}
