package com.myProject.myProject.controller;

import com.myProject.myProject.model.User;
import com.myProject.myProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) {
        Optional<User> existingUser = userService.findUserByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }

        userService.addUser(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}
