package com.example.miami.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.miami.models.user.User;
import com.example.miami.repository.ProfileRepo;

import org.jetbrains.annotations.NotNull;

public class ProfileViewModel extends AndroidViewModel {
    private ProfileRepo profileRepo = ProfileRepo.getInstance(getApplication());

    public ProfileViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    public LiveData<User> getUser() {
        return profileRepo.getUser();
    }
}
