package com.example.projectSM.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.projectSM.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

	
}
