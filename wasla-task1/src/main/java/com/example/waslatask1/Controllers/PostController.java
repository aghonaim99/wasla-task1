package com.example.waslatask1.Controllers;

import com.example.waslatask1.Models.NewPost;
import com.example.waslatask1.Models.Post;
import com.example.waslatask1.Models.PostDBEntity;
import com.example.waslatask1.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.crypto.dsig.XMLObject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Post")
public class PostController {
    @Autowired
    PostService postService;



    @GetMapping("/youm7rss")
    public Document testrss(){
        String url = "http://localhost:8089/ds/youm7rss";
        RestTemplate restTemplate = new RestTemplate();
        String str =restTemplate.getForObject(url, String.class);
//        Document docObj = convertStringToDocument(str);
//        System.out.println(docObj);
        System.out.println(str);
//        docObj.getElementsByTagName("")

        return null;
    }

    @GetMapping("/getAllPosts")
    public ResponseEntity<List<Post>> getAllPosts(@RequestParam(name="lang", required = true) String lang) {
        List<Post> posts = postService.getAllPosts(lang);

        return new ResponseEntity<>(posts, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/getPageOfPosts")
    public ResponseEntity<Map<String, Object>> getPostsPageable(@RequestParam(name="page", required = true) int pageNum,
                                                                @RequestParam(name="size", defaultValue = "2") int rowsPerPage,
                                                                @RequestParam(name="lang", required = true) String lang) {
        Map<String, Object> posts = postService.getPostsPageable(pageNum,rowsPerPage, lang);

        return new ResponseEntity<Map<String, Object>>(posts, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/findPostById")
    public ResponseEntity<Post> findPostByID(@RequestParam(name="postID", required = true) Long postID,
                                                     @RequestParam(name="lang", required = true) String lang) {
        Post post = postService.getPost(postID, lang);

        return new ResponseEntity<>(post, new HttpHeaders(), HttpStatus.FOUND);
    }

    @PostMapping("/createPost")
    public ResponseEntity<String> createPost(@RequestBody NewPost post) {
        postService.createPost(post);
        return new ResponseEntity<>("p", new HttpHeaders(), HttpStatus.CREATED);
    }

    @PostMapping("/updatePost")
    public ResponseEntity<PostDBEntity> updatePost(@RequestBody PostDBEntity post){
        PostDBEntity p = postService.updatePost(post);
        return new ResponseEntity<PostDBEntity>(p, new HttpHeaders(), HttpStatus.OK);
    }

//    @DeleteMapping("/deletePost")
//    public ResponseEntity<String> deletePost(@RequestBody Post post){
//        PostService.d
//    }
}
