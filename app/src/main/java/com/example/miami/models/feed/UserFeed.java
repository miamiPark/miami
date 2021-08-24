package com.example.miami.models.feed;

import android.net.IpSecManager;

public class UserFeed {
    public int id;
    public String name;
    public int date_birth;
    public String job;
    public String education;
    public String aboutMe;
    public String[] linkImages;
    public Boolean is_superlike;
    public String filter;

    public UserFeed(int id, String name, int date_birth, String job, String education,  String aboutMe,
                    String[] links, Boolean is_superlike, String filter) {
        this.id = id;
        this.name = name;
        this.date_birth = date_birth;
        this.job = job;
        this.education = education;
        this.aboutMe = aboutMe;
        this.linkImages = links;
        this.is_superlike = is_superlike;
        this.filter = filter;
    }

    public UserFeed() {

    }
}