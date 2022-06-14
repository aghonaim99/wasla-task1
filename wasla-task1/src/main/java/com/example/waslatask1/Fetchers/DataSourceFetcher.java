package com.example.waslatask1.Fetchers;

import com.example.waslatask1.Models.Category;
import com.example.waslatask1.Models.PostDBEntity;

import java.util.List;

public interface DataSourceFetcher<T> {
    T getDatasource(String path);
}
