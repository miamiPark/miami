package com.example.miami.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.miami.models.match.MatchProgress;
import com.example.miami.network.LikeApi;
import com.example.miami.network.MatchApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchRepo {
    private final MatchApi mMatchApi;
    private MutableLiveData<MatchProgress> mMatchProgress;

    public MatchRepo(MatchApi match) {
        mMatchApi = match;
    }

    public LiveData<MatchProgress> like(int uid1, int uid2) {
        like(mMatchProgress, uid1, uid2);
        return  mMatchProgress;
    }

    public void like(final MutableLiveData<MatchProgress> progress, int uid1, int uid2) {
        mMatchApi.getLikeApi().like(new LikeApi.UserLike(uid1, uid2))
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            progress.postValue(MatchProgress.SUCCESS);
                        } else {
                            progress.postValue(MatchProgress.FAILED);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        progress.postValue(MatchProgress.FAILED);

                    }
                });
    }
}
