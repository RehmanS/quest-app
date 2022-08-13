package com.project.questapp.controllers;

import com.project.questapp.entities.User;
import com.project.questapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{user-id}")
    public User getUserById(@PathVariable("user-id") long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody User user, @PathVariable("id") long id) {
         userService.updateUser(user,id);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
         userService.delete(id);
    }
}
