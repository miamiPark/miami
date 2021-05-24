package com.example.miami.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface MatchRequestApi {
    class MatchBody {
        public int uid1;
        public int uid2;
        public String lastMsg;
        public String target;

        public MatchBody(int id1, int id2, String msg, String t) {
            uid1 = id1;
            uid2 = id2;
            lastMsg = msg;
            target = t;
        }
    }

    @GET("/api/v1/chats")
    Call<ResponseBody> match(@Body MatchRequestApi.MatchBody matchBody);
}
