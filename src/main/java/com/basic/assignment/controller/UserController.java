package com.basic.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basic.assignment.Exception.ApiResponseException;
import com.basic.assignment.Exception.EmailAlreadyInUseException;
import com.basic.assignment.Exception.UserNotFoundException;
import com.basic.assignment.entity.User;
import com.basic.assignment.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	 @Autowired
	private UserService userService;
	
	@GetMapping("/main")
	public String health_check(){
		return "working fine main!!!!!!";
	}
	
	@GetMapping("/find/{id}")
    public ResponseEntity<?> getUserByEmail(@PathVariable Long id) {
        try{User user = userService.getUserByid(id);
        return ResponseEntity.ok(user);
        }catch (UserNotFoundException ex) {
        	ApiResponseException responseException=new ApiResponseException(400, ex.getMessage());
            return ResponseEntity.badRequest().body(responseException);
		}
    }
	
	@PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
		try {
           User createdUser = userService.create(user); // Return the created user in the response
            return ResponseEntity.ok(createdUser);
        } catch (EmailAlreadyInUseException ex) {
            // Return a 400 Bad Request response if email is already in use
        	ApiResponseException responseException=new ApiResponseException(400, ex.getMessage());
            return ResponseEntity.badRequest().body(responseException);
        }
		
		
    }
	
	
	
	
	
	
	
}
