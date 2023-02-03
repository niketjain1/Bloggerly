package com.project.blogapp.security.jwt;

import com.project.blogapp.serviceImpl.UserServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationManager implements AuthenticationManager {

    private JwtService jwtService;
    private UserServiceImpl userServiceImpl;

    public JwtAuthenticationManager(JwtService jwtService, UserServiceImpl userServiceImpl) {
        this.jwtService = jwtService;
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication instanceof JwtAuthentication){
            JwtAuthentication jwtAuthentication = (JwtAuthentication) authentication;

            var jwtString = jwtAuthentication.getCredentials();
            var username = jwtService.getUsernameForJwt(jwtString);
            // TODO: crypto failure on jwt verification
            // TODO: check if jwt is expired

            var user = userServiceImpl.findByUsername(username);
            jwtAuthentication.setUser(user);
            return jwtAuthentication;
        }
        return null;
    }
}
