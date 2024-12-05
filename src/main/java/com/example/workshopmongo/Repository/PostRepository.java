package com.example.workshopmongo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.workshopmongo.Domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post,String> {}
