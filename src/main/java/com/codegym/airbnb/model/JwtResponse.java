package com.codegym.airbnb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private Long id;

    private String username;

    private String password;

    private String token;

    private String name;

    private String avatar;

    private String phoneNumber;

    private String address;

    private String email;

    private String type = "Bearer";

    public JwtResponse(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public JwtResponse(Long id, String username, String password, String token, String name, String avatar, String phoneNumber, String address, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.token = token;
        this.name = name;
        this.avatar = avatar;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }
}
