package com.example.miami.repository;

import android.content.Context;
import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.miami.ApplicationModified;
import com.example.miami.models.feed.MatchProgress;
import com.example.miami.models.feed.UserFeed;
import com.example.miami.network.MatchApi;
import com.example.miami.network.MatchRequestApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchRepo {
    private static Context MyContext;
    private final MatchApi mMatchApi;
    private MutableLiveData<MatchProgress> mMatchProgress;
    private String mCurrentUser;

    public MatchRepo(MatchApi match) {
        mMatchApi = match;
    }

    public static MatchRepo getInstance(Context context) {
        MyContext = context;
        return ApplicationModified.from(context).getMatchRepo();
    }

    public LiveData<MatchProgress> match(int id1, int id2, String msg, String t) {
        if (TextUtils.equals(String.valueOf(id1), mCurrentUser) && mMatchProgress.getValue() == MatchProgress.IN_PROGRESS) {
            return mMatchProgress;
        } else if (!TextUtils.equals(String.valueOf(id1), mCurrentUser) && mMatchProgress != null) {
            mMatchProgress.postValue(MatchProgress.FAILED);
        }
        mCurrentUser = String.valueOf(id1);
        mMatchProgress = new MutableLiveData<>();
        match(id1, id2, msg, t);
        return mMatchProgress;
    }

    public void match(final MutableLiveData<MatchProgress> progress,
                      int id, String target) {
        mMatchApi
                .getMatchRequestApi()
                .match(new MatchRequestApi.MatchBody(
                        id, target
                ))
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
