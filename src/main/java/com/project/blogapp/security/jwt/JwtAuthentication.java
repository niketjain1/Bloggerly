package com.project.blogapp.security.jwt;

import com.project.blogapp.entities.Role;
import com.project.blogapp.payloads.RoleDto;
import com.project.blogapp.payloads.UserResponseDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JwtAuthentication implements Authentication {

    private String jwtString;
    private UserResponseDto user;
    public JwtAuthentication(String jwtString) {
        this.jwtString = jwtString;
    }

    void setUser(UserResponseDto user){
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO: not needed for now, unless we have different resources accessible to different roles
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (RoleDto role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getCredentials() {
        // This is where we return the String/token that is used for authentication
        return jwtString;
    }

    @Override
    public Object getDetails() {
        // TODO: not need for now
        return null;
    }

    @Override
    public UserResponseDto getPrincipal() {
        // This is where we return the client/user who is getting authenticated
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return user != null;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
