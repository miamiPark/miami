package com.example.miami.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.miami.ApplicationModified;
import com.example.miami.models.feed.FeedProgress;
import com.example.miami.models.feed.UserFeed;
import com.example.miami.network.FeedApi;
import com.example.miami.network.LikeDisLikeApi;
import com.example.miami.network.UsersApi;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedRepo {
    private final FeedApi feedApi;
    private static Context MyContext;
    private final static MutableLiveData<List<UserFeed>> mUsers = new MutableLiveData<>();

    static  {
        mUsers.setValue(Collections.<UserFeed>emptyList());
    }


    public FeedRepo(FeedApi feed) {
        feedApi = feed;
    }

    public static FeedRepo getInstance(Context context) {
        MyContext = context;
        return ApplicationModified.from(context).getmFeedRepo();
    }

    public LiveData<List<UserFeed>> getFeed() {
        feedApi.getUsersApi().getFeed().enqueue(new Callback<UsersApi.Users>() {
            @Override
            public void onResponse(Call<UsersApi.Users> call, Response<UsersApi.Users> response) {
                    Log.w("response", response.toString());
                    Log.w("response", response.body().toString());
                    if(response.isSuccessful() && response.body() != null) {
//                        Log.e("response is []", transform(response.body().user_feed).toString());
                        mUsers.postValue(transform(response.body().user_feed));
                    } else {
                        Log.w("response", "ya tut");
                        UserFeed user = new UserFeed();
                        user.id = -1;
                        List<UserFeed> result = new ArrayList<>();
                        result.add(user);
                        mUsers.postValue(result);
                    }
            }

            @Override
            public void onFailure(Call<UsersApi.Users> call, Throwable t) {
                Log.e("FeedRepo", "Failed to load", t);
                UserFeed user = new UserFeed();
                user.id = -1;
                List<UserFeed> result = new ArrayList<>();
                result.add(user);
                mUsers.postValue(result);
            }
        });

        return mUsers;
    }

    private static List<UserFeed> transform(List<UsersApi.User> users) {
        List<UserFeed> result = new ArrayList<>();
        // error here if users = []
        if (users == null) {
            return result;
        }
        for (UsersApi.User user : users) {
            try {
                UserFeed userFeed = map(user);
                result.add(userFeed);
                Log.e("FeedRepo", "Loaded " + userFeed.name + " #" + userFeed.id);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static UserFeed map(UsersApi.User user) throws ParseException {
        return new UserFeed(
                user.id,
                user.name,
                user.date_birth,
                user.job,
                user.education,
                user.aboutMe,
                user.linkImages,
                user.is_superlike,
                user.filter
        );
    }

    public void postLike(int id) {
        LikeDisLikeApi.Like like = new LikeDisLikeApi.Like();
        like.user_id2 = id;
        feedApi.getLikeDisLikeApi().like(like).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.w("response", response.toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.w("Failure Like", t);
            }
        });
    }

    public void postDisLike(int id) {
        LikeDisLikeApi.Like like = new LikeDisLikeApi.Like();
        like.user_id2 = id;
        feedApi.getLikeDisLikeApi().dislike(like).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.w("response", response.toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.w("Failure DisLike", t);
            }
        });
    }
}
