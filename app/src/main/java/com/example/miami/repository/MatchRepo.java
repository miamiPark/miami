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

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchRepo {
    private final MatchApi matchApi;
    private static Context MyContext;
    private final static MutableLiveData<ChatModel> mMatch = new MutableLiveData<>();
//
//    static {
//        ChatModel chatModel = new ChatModel();
//        chatModel.data = new ArrayList<>();
//        mMatch.setValue(chatModel);
//    }

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
                    ChatModel chatModel = transform(response.body());
                    Log.w("я ЗДЕЕЕЕЕЕСЬ", chatModel.data.get(0).partner.linkImages[0]);

                    mMatch.postValue(chatModel);
                } else {
                    Log.w("response", "response is null");
                }
            }

            @Override
            public void onFailure(Call<MatchRequestApi.ChatModel> call, Throwable t) {
                Log.w("фэйлур", "в матч репо");
            }
        });
        return mMatch;
    }

    public ChatModel transform(MatchRequestApi.ChatModel chatModel) {
        ChatModel result = new ChatModel();
        if (chatModel.data == null) {
            return result;
        }
        result.data = new ArrayList<>();
        for (int i = 0; i < chatModel.data.size(); ++i) {
            ChatModel.ChatData chatData = new ChatModel.ChatData();
            chatData.partner = new UserFeed();
            chatData.partner.name = chatModel.data.get(i).partner.name;
            chatData.partner.date_birth = chatModel.data.get(i).partner.date_birth;
            chatData.partner.linkImages = chatModel.data.get(i).partner.linkImages;

            result.data.add(chatData);
        }
        return result;
    }
}
