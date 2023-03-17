package com.project.blogapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidUsernameException extends RuntimeException{
    String username;
    public InvalidUsernameException(String username){
        super(String.format("Username with %s not found !", username));
        this.username = username;
    }
}
