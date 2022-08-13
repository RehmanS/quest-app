package com.project.questapp.serviceimpl;

import com.project.questapp.entities.User;
import com.project.questapp.repos.UserRepository;
import com.project.questapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(User user, long id) {
        Optional<User> user1 = userRepository.findById(id);
        if (user1.isPresent()) {
            User foundUser = user1.get();
            foundUser.setUsername(user.getUsername());
            foundUser.setPassword(user.getPassword());
            userRepository.save(foundUser);
            return foundUser;
        }
        else{
         return null;
        }
    }
}
