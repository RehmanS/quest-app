package com.project.questapp.repos;

import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

}
