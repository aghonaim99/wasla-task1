package com.example.waslatask1.PostBuilders;

import com.example.waslatask1.Models.Post;
import com.example.waslatask1.Models.PostDBEntity;

public class EnglishPostBuilder implements PostBuilder{
    private Long id;
    private String title;
    private String body;
    private String imgUrl;

    public Post buildPost(PostDBEntity dbPost)
    {
        this.title = dbPost.getTitle_en();
        this.body = dbPost.getBody_en();
        this.imgUrl = dbPost.getImg_url();

        return new Post(this.id, this.title, this.body, this.imgUrl);
    }
}
