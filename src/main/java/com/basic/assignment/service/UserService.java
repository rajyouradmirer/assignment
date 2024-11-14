package com.basic.assignment.service;

import java.util.List;

import com.basic.assignment.entity.User;

public interface UserService {

	User getUserByid(Long id);

	User create(User user);
	
	

}
