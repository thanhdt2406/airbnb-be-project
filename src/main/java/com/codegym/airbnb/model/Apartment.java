package com.codegym.airbnb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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

    private boolean vipRoom;

    private boolean luxuryRoom;

    private boolean singleRoom;

    private boolean coupleRoom;

    private boolean presidentRoom;

    @ManyToOne
    private User user;

    @ManyToOne
    private Ward ward;
}
