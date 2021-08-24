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
import com.example.miami.network.RegistrationRequestApi;

import java.io.File;
import java.io.IOException;

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
    private String mUrl;
    private String mCurrentUser;

    public static RegistrationRepo getInstance(Context context) {
        return ApplicationModified.from(context).getRegistrationRepo();
    }

    public LiveData<RegistrationProgress> registration(String telephone, String password, String name,
                                                       String day, String month, String year,
                                                       String sex, String job, String education,
                                                       String aboutMe, String[] linkImages) {
        if (TextUtils.equals(telephone, mCurrentUser) && mRegistrationProgress.getValue() == RegistrationProgress.IN_PROGRESS) {
            return mRegistrationProgress;
        } else if (!TextUtils.equals(telephone, mCurrentUser) && mRegistrationProgress != null) {
            mRegistrationProgress.postValue(RegistrationProgress.FAILED);
        }
        mCurrentUser = telephone;
        mRegistrationProgress = new MutableLiveData<>();
        registration(
                mRegistrationProgress, telephone, password,
                name, day, month, year, sex, job, education,
                aboutMe, linkImages
        );
        return mRegistrationProgress;
    }

    public void registration(final MutableLiveData<RegistrationProgress> progress,
                             String telephone, String password, String name,
                             String day, String month, String year,
                             String sex, String job, String education,
                             String aboutMe, String[] linkImages) {
        mRegistrationApi
                .getRegistrationRequestApi()
                .registration(new RegistrationRequestApi.RegistrationBody(
                        telephone, password, name, day, month, year, sex, job, education, aboutMe, linkImages
                ))
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            progress.postValue(RegistrationProgress.SUCCESS);
                        } else {
                            progress.postValue(RegistrationProgress.FAILED);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        progress.postValue(RegistrationProgress.FAILED);
                    }
                });
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
        MultipartBody.Part body = MultipartBody.Part.createFormData("photo", file.getName(), requestFile);

        mRegistrationApi.getUploadAvatarApi().uploadAvatar(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        mUrl = response.body().string();
                        progress.postValue(RegistrationProgress.AVATAR_SUCCESS);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    progress.postValue(RegistrationProgress.FAILED);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                progress.postValue(RegistrationProgress.FAILED);
            }
        });
    }

    public String getUrl() {
        return mUrl;
    }
}
