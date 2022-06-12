package com.example.waslatask1.PostBuilders;

import com.example.waslatask1.Models.Post;
import com.example.waslatask1.Models.PostDBEntity;

public interface PostBuilder {
//    void setPostBody(String body);
//    void setPostTitle(String title);
//    void setPostImgURL(String imgUrl);
    Post buildPost(PostDBEntity dbPost);
}
