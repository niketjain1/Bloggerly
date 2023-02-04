package com.project.blogapp.repositories;

import com.project.blogapp.entities.CategoryEntity;
import com.project.blogapp.entities.PostEntity;
import com.project.blogapp.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {
    List<PostEntity> findByUser(UserEntity user);

    List<PostEntity> findByCategory(CategoryEntity category);
}
