package com.example.miami.repository;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.miami.ApplicationModified;
import com.example.miami.models.feed.ChatModel;
import com.example.miami.models.feed.MatchProgress;
import com.example.miami.models.feed.UserFeed;
import com.example.miami.network.LoginApi;
import com.example.miami.network.MatchApi;
import com.example.miami.network.MatchRequestApi;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchRepo {
    private final MatchApi matchApi;
    private static Context MyContext;
    private MutableLiveData<MatchProgress> mMatchProgress;
    private String mCurrentUser;
    private final static MutableLiveData<ChatModel> mMatch = new MutableLiveData<>();

    public MatchRepo(MatchApi match) {
        matchApi = match;
    }

    public static MatchRepo getInstance(Context context) {
        MyContext = context;
        return ApplicationModified.from(context).getMatchRepo();
    }

    public LiveData<ChatModel> getMatch() {
        Log.w("response", "FEED_MATCH");
        matchApi.getMatchApi().match().enqueue(new Callback<MatchRequestApi.ChatModel>() {
            @Override
            public void onResponse(Call<MatchRequestApi.ChatModel> call, Response<MatchRequestApi.ChatModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                   Log.w("response", response.toString());
                   Log.w("response", response.body().toString());
                } else {
                    Log.w("response", "response is null");
                }
            }

            @Override
            public void onFailure(Call<MatchRequestApi.ChatModel> call, Throwable t) {

            }
        });
        return mMatch;
    }

//    public void match(final MutableLiveData<MatchProgress> progress,
//                      int id, String target) {
//        matchApi
//                .getMatchRequestApi()
//                .match()
//                .enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        if (response.isSuccessful()) {
//                            progress.postValue(MatchProgress.SUCCESS);
//                        } else {
//                            progress.postValue(MatchProgress.FAILED);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                        progress.postValue(MatchProgress.FAILED);
//                    }
//                });
//    }
}
