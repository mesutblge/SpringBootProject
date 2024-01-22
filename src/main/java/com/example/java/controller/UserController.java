package com.example.java.controller;

import com.example.java.entity.User;
import com.example.java.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
    public List<User>getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/newUser")
    public User createUser(@RequestBody User newUser){
        return userService.saveUser(newUser);
    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId){
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId,
                              @RequestBody User newUser){
       return userService.updateUser(userId,newUser);

    }
    @DeleteMapping("/deleteUser/{userId}")
    public void deleteOneUser(@PathVariable Long userId){
        userService.deleteById(userId);
    }
}
