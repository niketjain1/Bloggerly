package com.project.blogapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Articles")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


}
