package com.project.blogapp.users;

import com.project.blogapp.security.jwt.JwtService;
import com.project.blogapp.users.dtos.CreateUserDto;
import com.project.blogapp.users.dtos.LoginUserDto;
import com.project.blogapp.users.dtos.UserResponseDto;
import com.project.blogapp.users.user_exception.InvalidPasswordException;
import com.project.blogapp.users.user_exception.InvalidUsernameException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    public UserResponseDto createUser(CreateUserDto request) {
        var user = modelMapper.map(request, UserEntity.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        var savedUser = userRepository.save(user);
        var response = modelMapper.map(savedUser, UserResponseDto.class);

        return response;
    }

    public UserResponseDto verifyUser(LoginUserDto request) {
        UserEntity user = userRepository.findByUsername(request.getUsername());
        if (user == null && request.getUsername() != user.getUsername()) {
            throw new InvalidUsernameException();
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException();
        }

        var response = modelMapper.map(user, UserResponseDto.class);
        response.setToken(jwtService.createJwt(response.getUsername()));
        return response;
    }

    public UserResponseDto findByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username);
        var response = modelMapper.map(user, UserResponseDto.class);
        return response;
    }

    public void deleteUserById(String username) {
        var user = userRepository.findByUsername(username);
        userRepository.delete(user);
    }
}