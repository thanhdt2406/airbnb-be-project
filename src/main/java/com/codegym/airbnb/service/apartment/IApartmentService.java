package com.codegym.airbnb.service.apartment;

import com.codegym.airbnb.model.Apartment;
import com.codegym.airbnb.model.Rent;
import com.codegym.airbnb.model.SearchCondition;
import com.codegym.airbnb.model.User;
import com.codegym.airbnb.service.IGeneralService;

import java.util.ArrayList;

public interface IApartmentService extends IGeneralService<Apartment> {
    void stopSelling(Long id);

    void repairSelling(Long id);

    void rentAgainApartment(Long id);

    void rentingApartment(Long id);

    void setAvtURL(String avatar,Long id);

    Iterable<Apartment> findSevenApartment();

    Iterable<Apartment> findAllByUser(User user);

    Iterable<Apartment> findAllByApartment_Id(Long id);

    ArrayList<Apartment> findApartmentByAllCondition(SearchCondition searchCondition);
}
