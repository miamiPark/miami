package com.example.miami.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.miami.models.feed.ChatModel;
import com.example.miami.repository.MatchRepo;

import org.jetbrains.annotations.NotNull;

public class MatchViewModel extends AndroidViewModel {
    private MatchRepo matchRepo = MatchRepo.getInstance(getApplication());

    public MatchViewModel(@NonNull @NotNull Application application) { super(application); }

    public LiveData<ChatModel> getMatch() {
        return matchRepo.getMatch();
    }
}
