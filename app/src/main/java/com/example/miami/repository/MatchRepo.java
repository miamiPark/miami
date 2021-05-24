package com.example.miami.repository;

import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.miami.models.feed.UserFeed;
import com.example.miami.network.MatchApi;
import com.example.miami.network.MatchRequestApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchRepo {
    private final MatchApi mMatchApi;
    private MutableLiveData<UserFeed.MatchProgress> mMatchProgress;
    private String mCurrentUser;

    public MatchRepo(MatchApi match) {
        mMatchApi = match;
    }

    public LiveData<UserFeed.MatchProgress> match(int id1, int id2, String msg, String t) {
        if (TextUtils.equals(String.valueOf(id1), mCurrentUser) && mMatchProgress.getValue() == UserFeed.MatchProgress.IN_PROGRESS) {
            return mMatchProgress;
        } else if (!TextUtils.equals(String.valueOf(id1), mCurrentUser) && mMatchProgress != null) {
            mMatchProgress.postValue(UserFeed.MatchProgress.FAILED);
        }
        mCurrentUser = String.valueOf(id1);
        mMatchProgress = new MutableLiveData<>();
        match(id1, id2, msg, t);
        return mMatchProgress;
    }

    public void match(final MutableLiveData<UserFeed.MatchProgress> progress,
                      int uid1, int uid2, String lastMsg, String target) {
        mMatchApi
                .getMatchRequestApi()
                .match(new MatchRequestApi.MatchBody(
                        uid1, uid2, lastMsg, target
                ))
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            progress.postValue(UserFeed.MatchProgress.SUCCESS);
                        } else {
                            progress.postValue(UserFeed.MatchProgress.FAILED);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        progress.postValue(UserFeed.MatchProgress.FAILED);
                    }
                });
    }
}
