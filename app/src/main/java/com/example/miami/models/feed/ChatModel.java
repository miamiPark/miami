package com.example.miami.models.feed;

import com.example.miami.network.MatchRequestApi;

import java.util.ArrayList;
import java.util.List;

public class ChatModel {

    class Msg {
        public int user_id;
        public int chat_id;
        public String message;
        public String time_delivery;
    }

    public static class ChatData {
        public int id;
        public UserFeed partner;
        public Msg[] messages;
        public String filter;
    }

    public ArrayList<ChatData> data;
}
