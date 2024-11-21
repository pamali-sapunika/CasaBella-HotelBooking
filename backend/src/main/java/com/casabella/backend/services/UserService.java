package com.casabella.backend.services;

import java.util.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.casabella.backend.model.User;
import com.casabella.backend.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    
    //add user
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    //find by username
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
    
    //BOTH username AND PASSWORD
    public User findByUsernameAndPassword(String username, String password) {
        User user = userRepo.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user; 
        }
        return null; 
    }

    // Get All Tour Agents
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // Get Tour Agent by ID
    public User getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    public User updateUser(Long id, User user) {
        Optional<User> optionalUser = userRepo.findById(id);
        
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setNic(user.getNic());
            existingUser.setAddress(user.getAddress());
            existingUser.setContactNo(user.getContactNo());
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());

            return userRepo.save(existingUser);
        }
        return null; // Optionally, throw an exception or return an error message
    }


    public void deleteUser(Long id) {
        if(!userRepo.existsById(id)) {
            throw new EntityNotFoundException("User with ID " + id + " is not found");
        }
    
        userRepo.deleteById(id);
    }
}
