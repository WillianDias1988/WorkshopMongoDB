package com.example.workshopmongo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.workshopmongo.Domain.User;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

}
