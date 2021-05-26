package com.example.miami.network;




import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UsersApi {
    class User {
        public int id;
        public String name;
        public int date_birth;
        public String job;
        public String education;
        public String aboutMe;
        public String[] linkImages;
        public Boolean is_superlike;
        public String filter;
    }

    class Users {
        public List<User> user_feed;

//        public List<User> getUsers_feed() {
//            return users_feed;
//        }
//        public void setUsers_feed(List<User> users) {
//            this.users_feed = users;
//        }
    }

    @GET("/api/v1/feed")
    Call<Users> getFeed();

    @GET("/api/v1/me")
    Call<User> getMe();
}
