package com.project.blogapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameExistException extends RuntimeException{
    String username;
    public UsernameExistException(String username){
        super(String.format("Username with %s already exist, please set a different username !", username));
        this.username = username;
    }
}
