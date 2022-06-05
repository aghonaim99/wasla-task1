package com.example.waslatask1.Controllers;

import com.example.waslatask1.Models.Post;
import com.example.waslatask1.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.CacheRequest;
import java.util.ArrayList;
import java.util.List;

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

    public ResponseEntity<List<Post>> getPostsPageable(int pagenum, int rowsPerPage) {

        return null;
    }
}
