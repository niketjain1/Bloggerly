package com.project.blogapp.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Categories")
@Getter
@Setter
@NoArgsConstructor
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Title", nullable = false)
    private String categoryTitle;
    @Column(name = "Description", nullable = false)
    private String categoryDescription;

    @OneToMany(targetEntity = PostEntity.class, mappedBy = "category", cascade = CascadeType.ALL)
    private List<PostEntity> posts = new ArrayList<>();
}
