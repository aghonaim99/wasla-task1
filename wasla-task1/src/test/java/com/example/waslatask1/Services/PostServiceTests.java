package com.example.waslatask1.Services;

import com.example.waslatask1.Models.Category;
import com.example.waslatask1.Models.Post;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;

public class PostServiceTests {
    @Test
    public void getOnePostInPageOne()
    {
        PostService postService = new PostService();

        Post expectedPost = new Post();
        expectedPost.setId(16l);
        expectedPost.setTitle_en("title1");
        expectedPost.setTitle_ar("عنوان");
        expectedPost.setBody_en("content of first post");
        expectedPost.setBody_ar("محتوى المقال الأول");

        Date date = Date.valueOf(LocalDate.of(2022, Month.JUNE, 3));

        expectedPost.setCreated_at(date);
        expectedPost.setMODIFIED_at(null);
        expectedPost.setType(1);
        expectedPost.setImg_url(null);

        Category c = new Category();
        c.setId(1l);
        c.setTitle_en("title1");
        c.setTitle_ar("عنوان");

        expectedPost.setCategory(c);

        List<Post> expectedList = new ArrayList<Post>();

        expectedList.add(expectedPost);

        List<Post> actual = postService.getPostsPageable(1,1);

//        Assert.assertTrue(Arrays.equals(expectedList.toArray(), actual.toArray()));
        Assert.assertTrue(expectedList.equals(actual));
    }
}
