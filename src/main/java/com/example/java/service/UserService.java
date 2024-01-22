package com.example.java.service;

import com.example.java.entity.User;
import com.example.java.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User saveUser(User newUser){
        return userRepository.save(newUser);
    }
    public User getUserById(Long userId){
        return userRepository.findById(userId).orElse(null);
    }
    public User updateUser(Long userId,User newUser){
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            User foundUser=user.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());
            userRepository.save(foundUser);
            return foundUser;
        }
        else{
            return null;
        }
    }
    public void deleteById(Long userId){
        userRepository.deleteById(userId);
    }

}
