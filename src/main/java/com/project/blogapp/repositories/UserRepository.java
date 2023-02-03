package com.project.blogapp.repositories;

import com.project.blogapp.entities.UserEntity;
import com.project.blogapp.exceptions.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
   public UserEntity findByUsername(String username);
}
