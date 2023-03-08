package com.project.blogapp.payloads;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PostRequestDto {
    private String title;
    private String content;

    private String imageName;

    private Date addedDate;

}
