package com.project.blogapp.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    public static final String SECRET = "askdjfls2342jwhbr2jh3br2h3";
    Algorithm algorithm = Algorithm.HMAC256(SECRET);
    public String createJwt(String username){
        if(username == null) {
            throw new IllegalArgumentException("Cannot create JWT token for a null user");
        }
            return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    public String getUsernameForJwt(String token){
        return JWT.require(algorithm)
                .build()
                .verify(token)
                .getSubject();
    }
}
