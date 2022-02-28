package com.example.virtuallearn.UserAuthorisation;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JWTRequest {
    private final String username;
    private final String password;
}
