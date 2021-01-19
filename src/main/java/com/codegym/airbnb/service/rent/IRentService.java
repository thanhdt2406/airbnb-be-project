package com.codegym.airbnb.service.rent;

import com.codegym.airbnb.model.Rent;
import com.codegym.airbnb.service.IGeneralService;

import java.util.Date;
import java.util.Optional;

public interface IRentService extends IGeneralService<Rent> {
    Rent saveByApartmentID(Long id,Date startDate, Date endDate);

    Iterable<Rent> findAllByApartmentID(Long id);

    void cancelBooking(Long apartmentId, Long userId);

    Iterable<Rent> getAllBookingApartmentByUser(Long userId);

    Iterable<Rent> getAllRented(Long userId);
}
