package com.tonilr.FinancialTracker.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonilr.FinancialTracker.Entities.Users;
import com.tonilr.FinancialTracker.exceptions.UserNotFoundException;
import com.tonilr.FinancialTracker.repos.UsersRepo;

@Service
public class UsersServices {

	@Autowired
	private final UsersRepo userRepo;

	public UsersServices(UsersRepo userRepo) {
		this.userRepo = userRepo;
	}

	public Users addUser(Users usuario) {
		return userRepo.save(usuario);
	}

	public List<Users> findAllUsers() {
		return userRepo.findAll();
	}

	public Users updateUser(Users usuario) {
		return userRepo.save(usuario);
	}

	public Users findUserById(Long id) {
		return userRepo.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));

	}

	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	}
}
