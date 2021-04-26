package com.example.miami.repository;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.miami.ApplicationModified;
import com.example.miami.models.authorization.AuthProgress;
import com.example.miami.models.registration.RegistrationProgress;
import com.example.miami.network.RegistrationApi;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationRepo {
    private final RegistrationApi mRegistrationApi;

    public RegistrationRepo(RegistrationApi api) {
        mRegistrationApi = api;
    }

    private String mCurrentPath;
    private MutableLiveData<RegistrationProgress> mRegistrationProgress;

    public static RegistrationRepo getInstance(Context context) {
        return ApplicationModified.from(context).getRegistrationRepo();
    }

    public LiveData<RegistrationProgress> uploadAvatar(String path) {
        if (TextUtils.equals(path, mCurrentPath) && mRegistrationProgress.getValue() == RegistrationProgress.IN_PROGRESS) {
            return mRegistrationProgress;
        } else if (!TextUtils.equals(path, mCurrentPath) && mRegistrationProgress != null) {
            mRegistrationProgress.postValue(RegistrationProgress.FAILED);
        }
        mCurrentPath = path;
        mRegistrationProgress = new MutableLiveData<>();
        uploadAvatar(mRegistrationProgress, path);
        return mRegistrationProgress;
    }

    private void uploadAvatar(final MutableLiveData<RegistrationProgress> progress, @NonNull final String path) {
        File file = new File(path);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        mRegistrationApi.getUploadAvatarApi().uploadAvatar(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    progress.postValue(RegistrationProgress.AVATAR_SUCCESS);
                } else {
                    Log.w("response", response.toString());
                    progress.postValue(RegistrationProgress.FAILED);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.w("failure ваще", t.printStackTrace(););
                t.printStackTrace();
                progress.postValue(RegistrationProgress.FAILED);
            }
        });
    }
}
