package com.example.miami.models.authorization;

import android.text.TextUtils;
import android.util.Log;

import java.util.Objects;

public class LoginData {
    private final String mTelephone;
    private final String mPassword;

    public LoginData(String telephone, String password) {
        mTelephone = telephone;
        mPassword = password;
    }

    public String getLogin() {
        return mTelephone;
    }

    public String getPassword() {
        return mPassword;
    }


    public boolean isValid() {
        Log.w("TELEPHONE", mTelephone);
        Log.w("PASSWORD", mPassword);
        return !TextUtils.isEmpty(mTelephone) && !TextUtils.isEmpty(mPassword);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginData loginData = (LoginData) o;
        return Objects.equals(mTelephone, loginData.mTelephone) &&
                Objects.equals(mPassword, loginData.mPassword);
    }
}

