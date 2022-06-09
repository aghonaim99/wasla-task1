package com.example.waslatask1.Repositories;

import com.example.waslatask1.Models.PostDBEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<PostDBEntity, Long> {

}

