package com.example.waslatask1.Builders;

import com.example.waslatask1.Models.Post;
import com.example.waslatask1.Models.PostDBEntity;

public class ArabicPostBuilder implements PostBuilder{
    private String title;
    private String body;
    private String imgUrl;

    @Override
    public Post buildPost(PostDBEntity dbPost) {
        this.title = dbPost.getTitle_ar();
        this.body = dbPost.getBody_ar();
        this.imgUrl = dbPost.getImg_url();

        return new Post(this.title, this.body, this.imgUrl);
    }
}
