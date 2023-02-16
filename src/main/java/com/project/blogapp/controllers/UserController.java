package com.project.blogapp.controllers;

import com.project.blogapp.payloads.*;
import com.project.blogapp.serviceImpl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/auth")
public class UserController {
    private UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> createUser(
            @Valid
            @RequestBody CreateUserDto request
    ){
        var createdUser = userServiceImpl.createUser(request);
        return ResponseEntity.created(URI.create("/users/"+ createdUser.getId())).body(createdUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("user/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@AuthenticationPrincipal UserResponseDto user, @PathVariable("id") int id){
//        int id = user.getId();
        userServiceImpl.deleteUserById(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> verifyUser(
            @Valid
            @RequestBody LoginUserDto request
    ){
        var verifiedUser = userServiceImpl.verifyUser(request);
        return ResponseEntity.ok(verifiedUser);
    }
}
