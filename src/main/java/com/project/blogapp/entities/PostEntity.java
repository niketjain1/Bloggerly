package com.project.blogapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;
    @Column(name = "Title", nullable = false)
    private String title;
    @Column(name = "content", nullable = false, length = 10000)
    private String content;

    @Column(name = "ImageURL", nullable = false)
    private String imageName;
    private Date addedDate;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "User_id")
    private UserEntity user;

    @OneToMany(targetEntity = CommentEntity.class, mappedBy = "post", cascade = CascadeType.ALL)
    private List<CommentEntity> comments = new ArrayList<>();
}
