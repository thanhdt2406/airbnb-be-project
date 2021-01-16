package com.codegym.airbnb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 6, max = 20)
    private String username;

    @NotNull
    @Size(min = 6)
    @Column(columnDefinition = "longtext")
    private String password;

    @Column(columnDefinition = "nvarchar(255) default 'Anonymous'")
    private String name;

    private String avatar;

    private String phoneNumber;

    private String address;

    private String email;

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
