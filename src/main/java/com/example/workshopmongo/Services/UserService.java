package com.example.workshopmongo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.workshopmongo.DTOs.UserDTO;
import com.example.workshopmongo.Domain.User;
import com.example.workshopmongo.Repository.UserRepository;
import com.example.workshopmongo.Services.Exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> FindAll() {
		return userRepository.findAll();
	}

	public User FindById(String id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User Insert(User obj) {
		return userRepository.insert(obj);
	}

	public void Delete(String id) {
		User user = FindById(id);
		userRepository.deleteById(user.getId());
	}
	
	public User Update(User obj) {
		
		User user = FindById(obj.getId());
		UpdateData(user , obj);
		return userRepository.save(user);
	}

	private void UpdateData(User user, User obj) {
		user.setName(obj.getName());
		user.setEmail(obj.getEmail());
	}

	public User FromDTO(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
	}

}
