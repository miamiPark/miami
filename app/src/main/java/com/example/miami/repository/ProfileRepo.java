package com.example.miami.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.miami.ApplicationModified;
import com.example.miami.models.feed.UserFeed;
import com.example.miami.models.user.User;
import com.example.miami.network.ProfileApi;
import com.example.miami.network.UsersApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileRepo {
    private ProfileApi profileApi;
    private static Context MyContext;
    private final static MutableLiveData<User> mUser = new MutableLiveData<>();


    static {
        mUser.setValue(new User());
    }

    public ProfileRepo(ProfileApi profile) {
        profileApi = profile;
    }

    public static ProfileRepo getInstance(Context context) {
        MyContext = context;
        return ApplicationModified.from(context).getProfileRepo();
    }

    public LiveData<User> getUser() {
        profileApi.getUsersApi().getMe().enqueue(new Callback<UsersApi.User>() {
            @Override
            public void onResponse(Call<UsersApi.User> call, Response<UsersApi.User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mUser.postValue(transform(response.body()));
                } else {
                    Log.w("reponse", "какие-то ошибки");
                    User user = new User();
                    mUser.postValue(user);
                }
            }

            @Override
            public void onFailure(Call<UsersApi.User> call, Throwable t) {
                Log.w("reponse", "какие-то ошибки");
                User user = new User();
                mUser.postValue(user);
            }
        });

        return mUser;
    }

    public static User transform(UsersApi.User user) {
        User result = new User();

        result.name = user.name;
        result.dateOfBirth = Integer.toString(user.date_birth);
        result.photo = user.linkImages[0];
        result.allPhotos = user.linkImages;

        // можно еще расширить, типа описание добавить и тд

        return result;
    }
}
