package com.example.miami.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginApi {

    class UserLogin {
        public String telephone;
        public String password;
        public Boolean is_logged_in;

        public UserLogin(String phone, String pass) {
            telephone = phone;
            password = pass;
            is_logged_in = true;
        }
    }

    @POST("/api/v1/login")
    Call<ResponseBody> login(@Body UserLogin userLogin);
}
