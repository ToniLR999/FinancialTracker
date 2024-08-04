package com.tonilr.FinancialTracker.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonilr.FinancialTracker.Entities.User;
import com.tonilr.FinancialTracker.exceptions.UserNotFoundException;
import com.tonilr.FinancialTracker.repos.UserRepo;

@Service
public class UserServices {

	@Autowired
	private final UserRepo userRepo;

	public UserServices(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	public User addUser(User usuario) {
		return userRepo.save(usuario);
	}

	public List<User> findAllUsers() {
		return userRepo.findAll();
	}

	public User updateUser(User usuario) {
		return userRepo.save(usuario);
	}

	public User findUserById(Long id) {
		return userRepo.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));

	}

	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	}
}
