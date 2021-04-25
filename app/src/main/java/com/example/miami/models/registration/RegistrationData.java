package com.example.miami.models.registration;

import android.text.TextUtils;

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
}
