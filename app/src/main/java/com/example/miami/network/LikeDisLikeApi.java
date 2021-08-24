package com.example.miami.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LikeDisLikeApi {

    class Like {
        public Integer user_id2;
    }

    @POST("/api/v1/like")
    Call<ResponseBody> like(@Body Like like);

    @POST("/api/v1/dislike")
    Call<ResponseBody> dislike(@Body Like dislike);
}
