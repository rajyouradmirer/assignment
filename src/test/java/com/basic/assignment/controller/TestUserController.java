package com.basic.assignment.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.basic.assignment.Exception.UserNotFoundException;
import com.basic.assignment.entity.User;
import com.basic.assignment.service.UserService;

public class TestUserController {
	
	private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testGetUserById_Success() throws Exception {
        // Arrange
        Long userId = 1L;
        User mockUser = new User(userId, "John Doe", "john.doe@example.com"); // Example User object
        
        when(userService.getUserByid(userId)).thenReturn(mockUser); // Mock the service method

        // Act & Assert
        mockMvc.perform(get("/find/{id}", userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // Expect HTTP 200
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    public void testGetUserById_UserNotFound() throws Exception {
        // Arrange
        Long userId = 1L;
        String errorMessage = "User not found";
        
        when(userService.getUserByid(userId)).thenThrow(new UserNotFoundException(errorMessage)); // Mock exception

        // Act & Assert
        mockMvc.perform(get("/find/{id}", userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()) // Expect HTTP 400
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.message").value(errorMessage))
                .andExpect(jsonPath("$.data").isEmpty());  // Ensure data is null
    }
}
