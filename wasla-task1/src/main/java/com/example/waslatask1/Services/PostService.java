package com.example.waslatask1.Services;

import com.example.waslatask1.Models.Post;
import com.example.waslatask1.Repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    PostRepo postRepo;

    public List<Post> getAllPosts()
    {
        return postRepo.findAll();
    }

    public List<Post> getPostsPageable(int pageNum, int rowsPerPage)
    {
        return new ArrayList<Post>();
    }
}
