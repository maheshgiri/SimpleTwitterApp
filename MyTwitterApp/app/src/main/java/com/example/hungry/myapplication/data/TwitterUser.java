package com.example.hungry.myapplication.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Hungry on 2/2/2016.
 */
public class TwitterUser  {
String text;
User user;
Entities entities;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Entities getEntities() {
        return entities;
    }

    public void setEntities(Entities entities) {
        this.entities = entities;
    }



}
