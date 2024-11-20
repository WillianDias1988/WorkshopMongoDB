package com.example.workshopmongo.Resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.workshopmongo.DTOs.UserDTO;
import com.example.workshopmongo.Domain.User;
import com.example.workshopmongo.Services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = userService.findAll();
		List<UserDTO> listUserDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listUserDTO);
	}

}