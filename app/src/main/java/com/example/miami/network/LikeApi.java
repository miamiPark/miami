package com.example.miami.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LikeApi {

    class UserLike {
        public int Uid1;
        public int Uid2;
        public String Target;

        public UserLike(int uid1, int uid2) {
            Uid1 = uid1;
            Uid2 = uid2;
            Target = "love";
        }

    }

    @POST("/api/v1/like")
    Call<ResponseBody> like(@Body UserLike userLike);
}
