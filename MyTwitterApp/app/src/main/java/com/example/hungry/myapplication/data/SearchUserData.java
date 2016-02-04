package com.example.hungry.myapplication.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hungry on 2/3/2016.
 */
public class SearchUserData {
    private List<Status> statuses = new ArrayList<>();
    public List<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }
}
