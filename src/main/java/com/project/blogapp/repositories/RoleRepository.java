package com.project.blogapp.repositories;

import com.project.blogapp.entities.PostEntity;
import com.project.blogapp.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
}
