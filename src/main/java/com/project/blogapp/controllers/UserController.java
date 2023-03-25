package com.project.blogapp.controllers;

import com.project.blogapp.payloads.*;
import com.project.blogapp.repositories.UserRepository;
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
    private final UserRepository userRepository;

    public UserController(UserServiceImpl userServiceImpl,
                          UserRepository userRepository) {
        this.userServiceImpl = userServiceImpl;
        this.userRepository = userRepository;
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
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") int id){
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

    @GetMapping("user/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Integer userId){
       UserResponseDto user = userServiceImpl.getUserById(userId);

        return new ResponseEntity<UserResponseDto>(user, HttpStatus.OK);
    }
    @PutMapping("/user/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Integer userId,
            @Valid@RequestBody CreateUserDto request
    ){
        UserResponseDto updatedUser = userServiceImpl.updateUser(request, userId);
        return new ResponseEntity<UserResponseDto>(updatedUser, HttpStatus.CREATED);
    }
}
