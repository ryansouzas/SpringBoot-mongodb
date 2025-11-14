package com.example.projectSM.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.projectSM.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{


	
}
