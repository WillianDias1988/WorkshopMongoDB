package com.example.workshopmongo.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.workshopmongo.Domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	@Query("{'title':{$regex: ?0, $options:'i'}}")
	List<Post> findTitle(String text);

	List<Post> findByTitleContainingIgnoreCase(String text);
}
