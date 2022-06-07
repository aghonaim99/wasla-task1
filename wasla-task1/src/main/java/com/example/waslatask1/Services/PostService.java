package com.example.waslatask1.Services;

import com.example.waslatask1.Exceptions.InvalidPostIDException;
import com.example.waslatask1.Models.Post;
import com.example.waslatask1.Repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        PageRequest p = PageRequest.of(pageNum, rowsPerPage, Sort.by("id"));
        Page<Post> retrieved = postRepo.findAll(p);

        return retrieved.getContent();
    }

    public Post createPost(Post post) {
        postRepo.save(post);

        return post;
    }

    public Post updatePost(Post post) {
        if(postRepo.existsById(post.getId()))
            return postRepo.save(post);
        else
            throw new InvalidPostIDException();
    }

    public String deletePost(Post post){
        if(postRepo.existsById(post.getId()))
            postRepo.deleteById(post.getId());
        else
            return "Post not found";

        return "Post deleted successfully";
    }

    public Post getPost(Long id) {
        return postRepo.findById(id).orElseThrow(() -> new InvalidPostIDException());
    }
}
