package com.codegym.airbnb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String avatar;

    private String address;

    private int bedroom;

    private int bathroom;

    private String description;

    private int value;

    private int status;

    private Date createDate;

    @ManyToOne
    private User user;

    @ManyToOne
    private Ward ward;
}
