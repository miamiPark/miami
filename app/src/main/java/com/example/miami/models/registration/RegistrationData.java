package com.example.miami.models.registration;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RegistrationData {
    private String mTelephone;
    private String mPassword;
    private String mRepeatPassword;
    private String mName;
    private int mDay;
    private int mMonth;
    private int mYear;
    private String mSex;
    private String mJob;
    private String mEducation;
    private String mAboutMe;
    private String[] mLinkImages;

    public RegistrationData() {}

//    public RegistrationData(String telephone, String password, String repeatPassword, String name,
//                            int day, int month, int year, String sex, String job, String education,
//                            String aboutMe, String[] linkImages) {
//        mTelephone = telephone;
//        mPassword = password;
//        mRepeatPassword = repeatPassword;
//        mName = name;
//        mDay = day;
//        mMonth = month;
//        mYear = year;
//        mSex = sex;
//        mJob = job;
//        mEducation = education;
//        mAboutMe = aboutMe;
//        mLinkImages = linkImages;
//    }

    public String getTelephone() {
        return mTelephone;
    }

    public String getPassword() {
        return mPassword;
    }

    public String getRepeatPassword() {
        return mRepeatPassword;
    }

    public String getName() {
        return mName;
    }

    public int getDay() {
        return mDay;
    }

    public int getMonth() {
        return mMonth;
    }

    public int getYear() {
        return mYear;
    }

    public String getSex() {
        return mSex;
    }

    public String getJob() {
        return mJob;
    }

    public String getEducation() {
        return mEducation;
    }

    public String getAboutMe() {
        return mAboutMe;
    }

    public String[] getLinkImages() {
        return mLinkImages;
    }

    public void setIdentity(String telephone, String password, String repeatPassword) {
        mTelephone = telephone;
        mPassword = password;
        mRepeatPassword = repeatPassword;
    }

    public boolean isValidIdentity() {
        if (!mPassword.equals(mRepeatPassword)) {
            return false;
        }

        return !TextUtils.isEmpty(mTelephone) &&
                !TextUtils.isEmpty(mPassword) &&
                !TextUtils.isEmpty(mRepeatPassword);
    }

    public void setName(String name) {
        mName = name;
    }

    public boolean isValidName() {
        return !TextUtils.isEmpty(mName);
    }

    public void setDate(int day, int month, int year) {
        mDay = day;
        mMonth = month;
        mYear = year;
    }

    public boolean isValidDate() {
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        String[] values = dateText.split("\\.");
        int day = Integer.parseInt(values[0]);
        int month = Integer.parseInt(values[1]);
        int year = Integer.parseInt(values[2]);

        if (mYear > year) {
            return false;
        } else if (mYear == year && mMonth > month) {
            return false;
        } else {
            return mYear != year || month != mMonth || mDay <= day;
        }
    }

    public void setSex(String sex) {
        mSex = sex;
    }
}
