package com.example.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.workshopmongo.Domain.User;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAll(){
		User maria = new User("1", "Maria dos Anjos", "maria@gmail.com");
		User fabiana = new User("2", "Fabiana Santna", "fabiana@gmail.com");
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(maria,fabiana));
		return ResponseEntity.ok().body(list);
	}
	
}
