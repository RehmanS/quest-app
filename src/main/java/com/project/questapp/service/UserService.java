package com.project.questapp.service;

import com.project.questapp.entities.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User createUser(User user);

    User getUserById(long id);

    void delete(long id);

    User updateUser(User user, long id);

}
