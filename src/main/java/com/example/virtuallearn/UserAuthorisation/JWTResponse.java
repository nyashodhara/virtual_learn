package com.example.virtuallearn.UserAuthorisation;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JWTResponse {
    private final String jwtToken;
}
