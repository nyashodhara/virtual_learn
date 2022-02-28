package com.example.virtuallearn.UserAuthorisation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class GetUser {
    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    public String getId(HttpServletRequest httpServletRequest){
        String authorization =httpServletRequest.getHeader("Authorization");
        String token = null;
        String userName =null;

        if(null != authorization && authorization.startsWith("Bearer ")){
            token = authorization.substring(7);
            userName = jwtUtility.getUsernameFromToken(token);
        }
        return userName;
    }
}
