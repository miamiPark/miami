package com.example.miami.models.feed;

import com.example.miami.network.MatchRequestApi;

import java.util.List;

public class ChatModel {

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

    public List<ChatData> data;
}
