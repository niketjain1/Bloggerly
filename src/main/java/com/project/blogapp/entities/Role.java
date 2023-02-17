package com.project.blogapp.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Role")
@Getter
@Setter
public class Role {
    @Id
    private int id;

    private String name;
}
