package com.example.miami.network;

import com.example.miami.models.feed.UserFeed;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface MatchRequestApi {

    class Msg {
        public int user_id;
        public int chat_id;
        public String message;
        public String time_delivery;
    }

    class ChatData {
        public int id;
        public UserFeed partner;
        public Msg[] messages;
        public String filter;
    }

    class MatchBody {
        public ChatData[] chatData;

        public MatchBody(int id, String t) {
            chatData[0].id = id;
            chatData[0].filter = t;
        }
    }

    @GET("/api/v1/chats")
    Call<ResponseBody> match(@Body MatchRequestApi.MatchBody matchBody);
}
