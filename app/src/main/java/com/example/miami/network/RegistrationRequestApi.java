package com.example.miami.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegistrationRequestApi {

    class RegistrationBody {
        public String telephone;
        public String password;
        public String name;
        public String day;
        public String month;
        public String year;
        public String sex;
        public String job;
        public String education;
        public String aboutMe;
        public String[] linkImages;

        public RegistrationBody(String t, String p, String n, String d,
                                String m, String y, String s, String j,
                                String e, String a, String[] l) {
            telephone = t;
            password = p;
            name = n;
            day = d;
            month = m;
            year = y;
            sex = s;
            job = j;
            education = e;
            aboutMe = a;
            linkImages = l;
        }
    }

    @POST("/api/v1/signup")
    Call<ResponseBody> registration(@Body RegistrationBody registrationBody);
}
