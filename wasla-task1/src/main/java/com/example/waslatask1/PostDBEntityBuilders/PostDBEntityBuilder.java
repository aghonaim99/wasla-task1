package com.example.waslatask1.PostDBEntityBuilders;

import com.example.waslatask1.Models.Category;
import com.example.waslatask1.Models.NewPost;
import com.example.waslatask1.Models.PostDBEntity;

import java.util.List;

public interface PostDBEntityBuilder<T> {
    List<PostDBEntity> buildEntity(T document, Category category);
}
