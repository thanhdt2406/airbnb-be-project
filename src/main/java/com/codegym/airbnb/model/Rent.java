package com.codegym.airbnb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date startDate;

    private Date endDate;

    @ManyToOne
    private User user;

    @ManyToOne
    private Apartment apartment;
}
