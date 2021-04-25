package com.example.miami.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.miami.models.registration.RegistrationData;
import com.example.miami.models.registration.RegistrationState;

public class RegistrationViewModel extends AndroidViewModel {

    private final RegistrationData mRegistrationData = new RegistrationData();
    private final MediatorLiveData<RegistrationState> mRegistrationState  = new MediatorLiveData<>();

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

        mRegistrationState.postValue(RegistrationState.IDENTITY_SUCCESS);

        // добавить фетч на номер телефона
    }

    public void name(String name) {
        mRegistrationData.setName(name);

        if (!mRegistrationData.isValidName()) {
            mRegistrationState.postValue(RegistrationState.ERROR);
            return;
        }

        mRegistrationState.postValue(RegistrationState.NAME_SUCCESS);
    }
}
