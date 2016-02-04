package com.example.hungry.myapplication.data;

/**
 * Created by Hungry on 2/2/2016.
 */
public class User {
String name;
String screen_name;
String location;
    String profile_image_url;

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
