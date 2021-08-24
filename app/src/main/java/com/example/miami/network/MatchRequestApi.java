package com.example.miami.network;

import com.example.miami.models.feed.UserFeed;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
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

    class ChatModel {
        public List<ChatData> data;
    }

    @GET("/api/v1/chats")
    Call<ChatModel> match();
}
