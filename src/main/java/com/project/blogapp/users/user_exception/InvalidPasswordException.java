package com.project.blogapp.users.user_exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException(){
        super("Invalid Password");
    }
}
