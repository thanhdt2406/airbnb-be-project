package com.codegym.airbnb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @ManyToOne
    private User user;

    @ManyToOne
    private Apartment apartment;

    private int status;

    public Rent(User user, Apartment apartment, Date startDate, Date endDate){
        this.user = user;
        this.apartment = apartment;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
