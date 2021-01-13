package com.codegym.airbnb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private Long id;

    private String token;

    private String type = "Bearer";

    private String username;

    public JwtResponse(String token, String username) {
        this.token = token;
        this.username = username;
    }
}
