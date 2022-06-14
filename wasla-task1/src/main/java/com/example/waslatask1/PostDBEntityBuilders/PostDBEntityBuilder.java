package com.example.waslatask1.PostDBEntityBuilders;

import com.example.waslatask1.Models.Category;
import com.example.waslatask1.Models.PostDBEntity;

import java.util.List;

public interface PostDBEntityBuilder<T> {
    T getDocument(String path);
    List<PostDBEntity> buildDBEntities(T document, Category category);
}
