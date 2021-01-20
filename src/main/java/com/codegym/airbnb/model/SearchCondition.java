package com.codegym.airbnb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCondition {
    private Long province;
    private Long district;
    private Long ward;
    private Long bathroom;
    private Long bedroom;
    private Long minPrice;
    private Long maxPrice;
    private Long vipRoom;
    private Long luxuryRoom;
    private Long singleRoom;
    private Long coupleRoom;
    private Long presidentRoom;
    private Date checkIn;
    private Date checkOut;
}
