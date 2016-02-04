package com.example.hungry.myapplication.data;

import java.util.List;

/**
 * Created by Hungry on 2/2/2016.
 */
public class Entities {
    List<HashTags> hashtags;
    List<Media> media;

    public List<Media> getMedia() {
        return media;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }

    public List<HashTags> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<HashTags> hashtags) {
        this.hashtags = hashtags;
    }
}