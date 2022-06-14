package com.example.waslatask1.Services;

import com.example.waslatask1.Models.Category;
import com.example.waslatask1.Models.NewPost;
import com.example.waslatask1.Models.PostDBEntity;
import com.example.waslatask1.Repositories.PostRepo;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class PostServiceTests {

    @InjectMocks
    PostService postService = new PostService();

    @Mock
    PostRepo postRepo;
//    @Mock
//    CategoryRepo categoryRepo;

    // CATEGORIES INITIALIZATIONS
    private Category initFirstCategory(){
        Category c = new Category();
        c.setId(1L);
        c.setTitle_en("title1");
        c.setTitle_ar("عنوان");

        return c;
    }
    private Category initSecondCategory(){
        Category c = new Category();
        c.setId(2L);
        c.setTitle_en("title2");
        c.setTitle_ar("عنوان");

        return c;
    }

    // POSTS INITIALIZATIONS
    private PostDBEntity initFirstPost(Category c){
        Date date = Date.valueOf(LocalDate.of(2022, Month.JUNE, 3));

        PostDBEntity p = new PostDBEntity();
        p.setId(16L);
        p.setTitle_en("title1");
        p.setTitle_ar("عنوان");
        p.setBody_en("content of first post");
        p.setBody_ar("محتوى المقال الأول");
        p.setCreated_at(date);
        p.setMODIFIED_at(null);
        p.setType(1);
        p.setImg_url(null);
        p.setCategory(c);

        return p;
    }

    private PostDBEntity initSecondPost(Category c){
        Date date = Date.valueOf(LocalDate.of(2022, Month.JUNE, 3));

        PostDBEntity p = new PostDBEntity();
        p.setId(2L);
        p.setTitle_en("title2");
        p.setTitle_ar("عنوان");
        p.setBody_en("content of second post");
        p.setBody_ar("محتوى المقال الثاني");
        p.setCreated_at(date);
        p.setMODIFIED_at(null);
        p.setType(1);
        p.setImg_url(null);
        p.setCategory(c);

        return p;
    }


//    @Test
//    public void getOnePostInFirstPage()
//    {
//        Category expectedCateg = initFirstCategory();
//        PostDBEntity expectedPost = initFirstPost(expectedCateg);
//
//        List<PostDBEntity> expectedResult = new ArrayList<>();
//        expectedResult.add(expectedPost);
//
//        Page<PostDBEntity> expectedPage = new PageImpl<>(expectedResult);
//
//        // mocking repo
//        PageRequest p = PageRequest.of(0, 1, Sort.by("id"));
//        when(postRepo.findAll(p)).thenReturn(expectedPage);
//
//        List<PostDBEntity> actualResult = postService.getPostsPageable(0, 1);
//
//        assertEquals(expectedResult, actualResult);
//    }

//    @Test
//    public void get2PostsInFirstPage(){
//        Category c1 = initFirstCategory();
//        Category c2 = initSecondCategory();
//        PostDBEntity p1 = initFirstPost(c1);
//        PostDBEntity p2 = initSecondPost(c2);
//
//        List<PostDBEntity> expectedResult = new ArrayList<>();
//        expectedResult.add(p1);
//        expectedResult.add(p2);
//
//        // mocking repo
//        Page<PostDBEntity> expectedPage = new PageImpl<>(expectedResult);
//        PageRequest p = PageRequest.of(0, 2, Sort.by("id"));
//        when(postRepo.findAll(p)).thenReturn(expectedPage);
//
//        List<PostDBEntity> actualResult = postService.getPostsPageable(0, 2);
//
//        assertEquals(expectedResult, actualResult);
//    }

//    @Test
//    public void getOnePostInSecondPage()
//    {
//        Category expectedCateg = initSecondCategory();
//        Post expectedPost = initSecondPost(expectedCateg);
//
//        List<Post> expectedResult = new ArrayList<>();
//        expectedResult.add(expectedPost);
//
//        Page<Post> expectedPage = new PageImpl<>(expectedResult);
//
//        // mocking repo
//        PageRequest p = PageRequest.of(1, 1, Sort.by("id"));
//        when(postRepo.findAll(p)).thenReturn(expectedPage);
//
//        List<Post> actualResult = postService.getPostsPageable(0, 1);
//
//        assertEquals(expectedResult, actualResult);
//    }

//    @Test
//    public void get2PostsInSecondPage(){
//        Category c1 = initFirstCategory();
//        Category c2 = initSecondCategory();
//        Post p1 = initFirstPost(c1);
//        Post p2 = initSecondPost(c2);
//
//        List<Post> expectedResult = new ArrayList<>();
//        expectedResult.add(p1);
//        expectedResult.add(p2);
//
//        // mocking repo
//        Page<Post> expectedPage = new PageImpl<>(expectedResult);
//        PageRequest p = PageRequest.of(1, 2, Sort.by("id"));
//        when(postRepo.findAll(p)).thenReturn(expectedPage);
//
//        List<Post> actualResult = postService.getPostsPageable(0, 2);
//
//        assertEquals(expectedResult, actualResult);
//    }

//    @Test
//    public void createPost(){
//        Category c = initFirstCategory();
//        PostDBEntity expected = initFirstPost(c);
//
//        when(postRepo.save(expected)).thenReturn(expected);
//
//        PostDBEntity actual = postService.createPost(expected);
//
//        assertEquals(expected, actual);
//    }

//    @Test
//    @Ignore
//    public void updateCategoryOfExistingPost(){
//        Category c = initFirstCategory();
//        Category newCateg = initSecondCategory();
//
//        PostDBEntity p = initFirstPost(c);
//        NewPost expectedNewPost = initFirstPost(newCateg);
//
//
//
//        when(postRepo.save(expectedNewPost)).thenReturn(expectedNewPost);
//
//        String actual = postService.updatePost(expectedNewPost);
//
//        assertEquals(expectedNewPost, actual);
//    }

//    @Test
//    @Ignore
//    public void Update_Not_Existing_Post_Creates_New_One(){
//        Category c = initSecondCategory();
//        PostDBEntity expectedPost = initFirstPost(c);
//
//        // make it a new post
//        expectedPost.setId(900L);
//
//        when(postRepo.save(expectedPost)).thenReturn(expectedPost);
//
//        PostDBEntity actual = postService.updatePost(expectedPost);
//
//        assertEquals(expectedPost, actual);
//    }

//    @Test
//    public void Delete_An_Existing_Post(){
//        Category c = initFirstCategory();
//        Post p = initFirstPost(c);
//
//        assertEquals(c, p);
//
//    }
}
