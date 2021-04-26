package com.example.miami.network;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import okhttp3.ResponseBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UploadAvatarApi {
    @Multipart
    @POST("/api/v1/upload")
    Call<ResponseBody> uploadAvatar(@Part MultipartBody.Part file);
}
