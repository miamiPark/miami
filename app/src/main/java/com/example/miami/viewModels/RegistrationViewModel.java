package com.example.miami.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.miami.models.registration.RegistrationData;
import com.example.miami.models.registration.RegistrationProgress;
import com.example.miami.models.registration.RegistrationState;
import com.example.miami.repository.RegistrationRepo;

public class RegistrationViewModel extends AndroidViewModel {

    private final RegistrationData mRegistrationData = new RegistrationData();
    private final MediatorLiveData<RegistrationState> mRegistrationState = new MediatorLiveData<>();

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

    public void date(int day, int month, int year) {
        mRegistrationData.setDate(day, month, year);

        if (!mRegistrationData.isValidDate()) {
            mRegistrationState.postValue(RegistrationState.ERROR);
            return;
        }

        mRegistrationState.postValue(RegistrationState.DATE_SUCCESS);
    }

    public void gender(String sex) {
        mRegistrationData.setSex(sex);
        mRegistrationState.postValue(RegistrationState.GENDER_SUCCESS);
    }

    public void detailInfo(String job, String university, String aboutMe) {
        mRegistrationData.setDetailInfo(job, university, aboutMe);
        mRegistrationState.postValue(RegistrationState.DETAIL_INFO_SUCCESS);
    }

    public void uploadAvatar(String path) {
        if (mRegistrationState.getValue() != RegistrationState.IN_PROGRESS) {
            requestUploadAvatar(path);
        }
    }

    private void requestUploadAvatar(String path) {
        mRegistrationState.postValue(RegistrationState.IN_PROGRESS);
        final LiveData<RegistrationProgress> progressLiveData =
                RegistrationRepo
                        .getInstance(getApplication())
                        .uploadAvatar(path);

        mRegistrationState.addSource(progressLiveData, new Observer<RegistrationProgress>() {
            @Override
            public void onChanged(RegistrationProgress registrationProgress) {
                if (registrationProgress == RegistrationProgress.AVATAR_SUCCESS) {
                    mRegistrationState.postValue(RegistrationState.AVATAR_SUCCESS);
                    mRegistrationState.removeSource(progressLiveData);
                } else if (registrationProgress == RegistrationProgress.FAILED) {
                    mRegistrationState.postValue(RegistrationState.FAILED);
                    mRegistrationState.removeSource(progressLiveData);
                }
            }
        });
    }
}
