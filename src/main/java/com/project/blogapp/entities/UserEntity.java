package com.project.blogapp.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    private int id;

    @Column(name ="Username", nullable = false, unique = true)
    private String username;
    @Column(name = "Email", nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String password;

    @Column(name = "About", nullable = false)
    private String about;
    @OneToMany(targetEntity = PostEntity.class, mappedBy = "user", cascade = CascadeType.ALL)
    private List<PostEntity> posts = new ArrayList<>();
}
