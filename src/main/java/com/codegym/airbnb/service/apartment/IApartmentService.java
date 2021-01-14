package com.codegym.airbnb.service.apartment;

import com.codegym.airbnb.model.Apartment;
import com.codegym.airbnb.model.User;
import com.codegym.airbnb.service.IGeneralService;

public interface IApartmentService extends IGeneralService<Apartment> {
    void stopSelling(Long id);
    Iterable<Apartment> findSevenApartment();
    Iterable<Apartment> findAllByUser(User user);
}
