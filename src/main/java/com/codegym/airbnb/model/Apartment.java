package com.codegym.airbnb.model;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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

    @NotNull
    private String name;

    private String address;

    private int bedroom;

    private int bathroom;

    @Column(columnDefinition = "longtext")
    private String description;

    private int value;

    @Min(0)
    @Max(4)
    private int status;

    @UpdateTimestamp
    private Date createDate;

    private int vipRoom;

    private int luxuryRoom;

    private int singleRoom;

    private int coupleRoom;

    private int presidentRoom;

    @ManyToOne
    private User user;

    @ManyToOne
    private Ward ward;
}
