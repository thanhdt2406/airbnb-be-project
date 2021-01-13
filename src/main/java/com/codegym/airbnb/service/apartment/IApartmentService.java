package com.codegym.airbnb.service.apartment;

import com.codegym.airbnb.model.Apartment;
import com.codegym.airbnb.service.IGeneralService;

public interface IApartmentService extends IGeneralService<Apartment> {
    public void stopSelling(Long id);
    Iterable<Apartment> findSevenApartment();
}
