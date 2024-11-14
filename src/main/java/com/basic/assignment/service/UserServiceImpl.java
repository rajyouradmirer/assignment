package com.basic.assignment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.assignment.Exception.EmailAlreadyInUseException;
import com.basic.assignment.Exception.UserNotFoundException;
import com.basic.assignment.entity.User;
import com.basic.assignment.repositary.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;

	@Override
	public User getUserByid(Long id) {
		Optional<User> byId = userRepo.findById(id);
		if (byId.isPresent()) {

			return byId.get();
		} else {
			throw new UserNotFoundException("user not found");
		}

	}

	@Override
	public User create(User user) {

		if (isEmailUnique(user.getEmail())) {
			throw new EmailAlreadyInUseException(user.getEmail()); // Throw exception if email is already in use
		} else {
			User save = userRepo.save(user);
			return save;
		}
	}

	public boolean isEmailUnique(String email) {
		return userRepo.findByEmail(email).isPresent(); // return true
	}

}
