package com.example.miami.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.miami.models.feed.UserFeed;
import com.example.miami.network.MatchRequestApi;
import com.example.miami.network.UsersApi;
import com.example.miami.repository.FeedRepo;
import com.example.miami.repository.MatchRepo;

import java.util.List;

public class FeedViewModel extends AndroidViewModel {
    private FeedRepo feedRepo = FeedRepo.getInstance(getApplication());

    private MatchRepo matchRepo = MatchRepo.getInstance(getApplication());

    public FeedViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<UserFeed>> getFeed() {
        return feedRepo.getFeed();
    }

    public LiveData<List<MatchRequestApi.ChatData>> getMatch() {
        return matchRepo.getMatch();
    }

    public void postLike(int id) {
        feedRepo.postLike(id);
    }

    public void postDisLike(int id) {
        feedRepo.postDisLike(id);
    }
}
