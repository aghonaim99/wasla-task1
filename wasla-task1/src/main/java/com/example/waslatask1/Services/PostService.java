package com.example.waslatask1.Services;

import com.example.waslatask1.PostBuilders.ArabicPostBuilder;
import com.example.waslatask1.PostBuilders.EnglishPostBuilder;
import com.example.waslatask1.PostBuilders.PostBuilder;
import com.example.waslatask1.Exceptions.InvalidCategoryIDException;
import com.example.waslatask1.Exceptions.InvalidPostIDException;
import com.example.waslatask1.Models.Category;
import com.example.waslatask1.Models.NewPost;
import com.example.waslatask1.Models.Post;
import com.example.waslatask1.Models.PostDBEntity;
import com.example.waslatask1.Repositories.CategoryRepo;
import com.example.waslatask1.Repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostService {
    @Autowired
    PostRepo postRepo;
    @Autowired
    CategoryRepo categoryRepo;


//    private Post getLangBasedPost(PostDBEntity dbPost, String lang)
//    {
//        Post post = new Post();
//        if(dbPost.getImg_url() != null)
//            post.setImgUrl(dbPost.getImg_url());
//
//        switch (lang){
//            case "en":
//                post.setTitle(dbPost.getTitle_en());
//                post.setBody(dbPost.getBody_en());
//                break;
//
//            case "ar":
//                post.setTitle(dbPost.getTitle_ar());
//                post.setBody(dbPost.getBody_ar());
//                break;
//            default:
//                throw new InvalidLanguageException();
//        }
//
//        return post;
//    }

    private PostBuilder initBuilder(String lang)
    {
        return lang.equals("en") ? new EnglishPostBuilder() : new ArabicPostBuilder();
    }

    public List<Post> getAllPosts(String lang)
    {
        List<PostDBEntity> dbPosts = postRepo.findAll();
        List<Post> posts = new ArrayList<>();
        PostBuilder postBuilder = initBuilder(lang);

        Post p;
        for(int i=0; i<dbPosts.size(); i++)
        {
            p = postBuilder.buildPost(dbPosts.get(i));
            posts.add(p);
        }

//        postBuilder = lang.equals("en") ? new EnglishPostBuilder() : new ArabicPostBuilder();   // What if more languages are added?
//        List<PostDBEntity> DBEntity = postRepo.findAll();
//        List<Post> posts = new ArrayList<>();
//        Post post;
//
//        for (PostDBEntity postDBEntity : DBEntity) {
//            post = getLangBasedPost(postDBEntity, lang);
//            posts.add(post);
//        }
//
//        return posts;
        return posts;
    }

    public Map<String, Object> getPostsPageable(int pageNum, int rowsPerPage, String lang)
    {
        PageRequest p = PageRequest.of(pageNum, rowsPerPage, Sort.by("id"));
        Page<PostDBEntity> retrieved = postRepo.findAll(p);
        List<PostDBEntity> dbPosts = retrieved.getContent();
        PostBuilder postBuilder = initBuilder(lang);
        List<Post> posts = new ArrayList<>();

        Post post;
        for(int i=0; i<dbPosts.size(); i++)
        {
            post = postBuilder.buildPost(dbPosts.get(i));
            posts.add(post);
        }


        Map<String, Object> PageOfPosts = new LinkedHashMap<>();
        PageOfPosts.put("TotalPages", retrieved.getTotalPages());
        PageOfPosts.put("TotalElements", retrieved.getTotalElements());
        PageOfPosts.put("PageContent", posts);



        return PageOfPosts;
    }

    public void createPost(NewPost post) {
        if(!categoryRepo.existsById(post.getCategoryId()))
            throw new InvalidCategoryIDException();
        else {
            // type catid imgurl
            PostDBEntity postDBEntity = new PostDBEntity();
            postDBEntity.setTitle_en(post.getTitle_en());
            postDBEntity.setTitle_ar(post.getTitle_ar());
            postDBEntity.setBody_en(post.getBody_en());
            postDBEntity.setBody_ar(post.getBody_ar());
            postDBEntity.setType(post.getType());
            postDBEntity.setImg_url(post.getImgUrl());

            Category c = categoryRepo.findById(post.getCategoryId()).orElseThrow(InvalidCategoryIDException::new);

            postDBEntity.setCategory(c);

            postRepo.save(postDBEntity);

            System.out.println("Post created successfully");

//            return postRepo.save(postDBEntity);
        }

//        if(categoryRepo.existsById(post.getCategory().getId()) && !postRepo.existsById(post.getId()))
//            postRepo.save(post);
//        else if(!categoryRepo.existsById(post.getCategory().getId()))
//            throw new InvalidCategoryIDException();
//        else if(postRepo.existsById(post.getId()))
//            throw new PostAlreadyExistsException();
//
//        return post;
    }

    public PostDBEntity updatePost(PostDBEntity post) {
        if(postRepo.existsById(post.getId()))
            return postRepo.save(post);
        else
            throw new InvalidPostIDException();
    }

    public String deletePost(PostDBEntity post){
        if(postRepo.existsById(post.getId()))
            postRepo.deleteById(post.getId());
        else
            throw new InvalidPostIDException();

        return "Post deleted successfully";
    }

    public Post getPost(Long id, String lang) {
        PostBuilder postBuilder = null;
        if(lang.equals("en"))
            postBuilder = new EnglishPostBuilder();
        else if (lang.equals("ar"))
            postBuilder = new ArabicPostBuilder();


        Post post = postBuilder.buildPost(postRepo.findById(id).orElseThrow(InvalidPostIDException::new));

        return post;
    }
}
