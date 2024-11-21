//Modified again Backend - Usercontroller
package com.casabella.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.casabella.backend.model.User;
import com.casabella.backend.security.AuthRequest;
import com.casabella.backend.security.JwtUtil;
import com.casabella.backend.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {
    
    @Autowired
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    // Add User - Register
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {  
        User createdUser = userService.registerUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }



    //login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        User user = userService.findByUsernameAndPassword(authRequest.getUsername(), authRequest.getPassword());
        if (user != null) {
            String token = jwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    
    // Get All Users
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllTourAgents() {
        List<User> tourAgents = userService.getAllUsers();
        return ResponseEntity.ok(tourAgents);
    }

    //user bu username
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username); // Call service method to get user by username
        if (user != null) {
            return ResponseEntity.ok(user); // Return user details if found
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 if user is not found
        }
    }

    // Get one User by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getTourAgentById(@PathVariable Long id) {
        User tourAgent = userService.getUserById(id);
        if (tourAgent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tourAgent);
    }

    // Update User
    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User tourAgent) {
        User updatedTourAgent = userService.updateUser(id, tourAgent);
        if (updatedTourAgent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedTourAgent);
    }
}
