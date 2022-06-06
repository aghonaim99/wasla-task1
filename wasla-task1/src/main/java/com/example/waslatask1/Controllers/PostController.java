package com.example.waslatask1.Controllers;

import com.example.waslatask1.Models.Post;
import com.example.waslatask1.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.CacheRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Post")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("/getAllPosts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();

        System.out.println(posts.get(0).getCreated_at());

        return new ResponseEntity<List<Post>>(posts, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/getPageOfPosts")
    public ResponseEntity<List<Post>> getPostsPageable(@RequestParam(name="page", required = true) int pageNum,
                                                       @RequestParam(name="size", defaultValue = "2") int rowsPerPage) {
        List<Post> posts = postService.getPostsPageable(pageNum,rowsPerPage);

        return new ResponseEntity<List<Post>>(posts, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/findPostById")
    public ResponseEntity<Post> findPostByID(@RequestParam(name="postID", required = true) Long postID) {
        Post post = postService.getPost(postID);

        return new ResponseEntity<>(post, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/createPost")
    public ResponseEntity<Post> createUser(@RequestBody Post post) {
        Post p = postService.createPost(post);
        return new ResponseEntity<Post>(p, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PostMapping("/updatePost")
    public ResponseEntity<Post> updatePost(@RequestBody Post post){
        Post p = postService.updatePost(post);
        return new ResponseEntity<Post>(p, new HttpHeaders(), HttpStatus.OK);
    }

//    @DeleteMapping("/deletePost")
//    public ResponseEntity<String> deletePost(@RequestBody Post post){
//        PostService.d
//    }
}
