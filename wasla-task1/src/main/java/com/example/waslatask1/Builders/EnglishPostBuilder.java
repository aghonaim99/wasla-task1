package com.example.waslatask1.Builders;

import com.example.waslatask1.Models.Post;
import com.example.waslatask1.Models.PostDBEntity;

public class EnglishPostBuilder implements PostBuilder{
    private String title;
    private String body;
    private String imgUrl;


//    @Override
//    public void setPostBody(String body) {
//        this.body = body;
//    }
//
//    @Override
//    public void setPostTitle(String title) {
//        this.title = title;
//    }
//
//    @Override
//    public void setPostImgURL(String imgUrl) {
//        this.imgUrl = imgUrl;
//    }
//
//    @Override
//    public Post buildPost() {
//        return new Post(this.title, this.body, this.imgUrl);
//    }

    public Post buildPost(PostDBEntity dbPost)
    {
        this.title = dbPost.getTitle_en();
        this.body = dbPost.getBody_en();
        this.imgUrl = dbPost.getImg_url();

        return new Post(this.title, this.body, this.imgUrl);
    }
}
