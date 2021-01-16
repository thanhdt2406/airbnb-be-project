package com.codegym.airbnb.service.apartment;

import com.codegym.airbnb.model.Apartment;
import com.codegym.airbnb.model.AppUser;
import com.codegym.airbnb.service.IGeneralService;

public interface IApartmentService extends IGeneralService<Apartment> {
    void stopSelling(Long id);
    void repairSelling(Long id);
    void rentAgainApartment(Long id);
    Iterable<Apartment> findSevenApartment();
    Iterable<Apartment> findAllByUser(AppUser appUser);
}
