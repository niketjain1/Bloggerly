package com.project.blogapp.serviceImpl;

import com.project.blogapp.config.AppConstants;
import com.project.blogapp.entities.Role;
import com.project.blogapp.entities.UserEntity;
import com.project.blogapp.payloads.LoginResponseDto;
import com.project.blogapp.repositories.RoleRepository;
import com.project.blogapp.repositories.UserRepository;
import com.project.blogapp.security.jwt.JwtService;
import com.project.blogapp.payloads.CreateUserDto;
import com.project.blogapp.payloads.LoginUserDto;
import com.project.blogapp.payloads.UserResponseDto;
import com.project.blogapp.exceptions.InvalidPasswordException;
import com.project.blogapp.exceptions.InvalidUsernameException;
import com.project.blogapp.exceptions.ResourceNotFoundException;
import com.project.blogapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, JwtService jwtService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserResponseDto createUser(CreateUserDto request) {
        var user = modelMapper.map(request, UserEntity.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Role role = roleRepository.findById(AppConstants.NORMAL_USER).get();
        user.getRoles().add(role);
        var savedUser = userRepository.save(user);
        var response = modelMapper.map(savedUser, UserResponseDto.class);

        return response;
    }

    @Override
    public LoginResponseDto verifyUser(LoginUserDto request) throws  ResourceNotFoundException {
        UserEntity user = userRepository.findByUsername(request.getUsername());
        System.out.println(user);
        if (user == null) {
            throw new InvalidUsernameException(request.getUsername());
        }
        else if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException();
        }

        var response = modelMapper.map(user, LoginResponseDto.class);
        response.setToken(jwtService.createJwt(response.getUsername()));
        return response;
    }

    public UserResponseDto getUserById(int userId) {
        UserEntity user = userRepository.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        var response = modelMapper.map(user, UserResponseDto.class);
        return response;
    }

    @Override
    public void deleteUserById(int userId) {

        var user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        userRepository.delete(user);
    }

    public UserResponseDto updateUser(CreateUserDto userDto, int userID){
        UserEntity user = userRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User", "id", userID));

        user.setUsername(userDto.getUsername());
        user.setPassword(user.getPassword());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());

        UserEntity updatedUser = userRepository.save(user);
        var response = modelMapper.map(updatedUser, UserResponseDto.class);
        return response;
    }

    public UserResponseDto findByUsername(String username){
        UserEntity user = userRepository.findByUsername(username);
        var response = modelMapper.map(user, UserResponseDto.class);
        return response;
    }
}