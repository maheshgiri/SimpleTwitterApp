package com.example.hungry.myapplication.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hungry on 2/3/2016.
 */
    public class Status {

        private String createdAt;
        private String id;
        private String idStr;
        private String text;
        private String source;
        private Boolean truncated;
        private User user;
        private Entities entities;

    public String getCreatedAt() {
        return createdAt;
    }

    public String getId() {
        return id;
    }

    public String getIdStr() {
        return idStr;
    }

    public String getText() {
        return text;
    }

    public String getSource() {
        return source;
    }

    public Boolean getTruncated() {
        return truncated;
    }

    public User getUser() {
        return user;
    }

    public Entities getEntities() {
        return entities;
    }
}
