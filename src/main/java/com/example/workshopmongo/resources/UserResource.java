package com.example.workshopmongo.Resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.workshopmongo.DTOs.UserDTO;
import com.example.workshopmongo.Domain.Post;
import com.example.workshopmongo.Domain.User;
import com.example.workshopmongo.Services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = userService.FindAll();
		List<UserDTO> listUserDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listUserDTO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User user = userService.FindById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> Insert(@RequestBody UserDTO userDTO) {
		User user = userService.FromDTO(userDTO);
		user = userService.Insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> Delete(@PathVariable String id) {
		userService.Delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> Update(@RequestBody UserDTO userDTO, @PathVariable String id ) {
		User user = userService.FromDTO(userDTO);
		user.setId(id);
		user = userService.Update(user);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}/posts", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
		User user = userService.FindById(id);
		return ResponseEntity.ok().body(user.getPosts());
	}
}