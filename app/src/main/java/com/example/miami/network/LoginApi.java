package com.example.miami.network;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginApi {

    class UserLogin {
        public String telephone;
        public String password;

        public UserLogin(String phone, String pass) {
            telephone = phone;
            password = pass;
        }
    }

    @POST("login")
    Call<Response> login(@Body UserLogin userLogin);
}
