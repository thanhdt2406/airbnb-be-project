package com.codegym.airbnb.service.rent;

import com.codegym.airbnb.model.Apartment;
import com.codegym.airbnb.model.Rent;
import com.codegym.airbnb.model.User;
import com.codegym.airbnb.repository.IRentRepo;
import com.codegym.airbnb.service.apartment.ApartmentService;
import com.codegym.airbnb.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class RentService implements IRentService {
    @Autowired
    private IRentRepo rentRepo;
    @Autowired
    private ApartmentService apartmentService;
    @Autowired
    private IUserService userService;

    @Override
    public Iterable<Rent> findAll() {
        return rentRepo.findAll();
    }

    @Override
    public Optional<Rent> findById(Long id) {
        return rentRepo.findById(id);
    }

    @Override
    public Rent save(Rent rent) {
        return rentRepo.save(rent);
    }

    @Override
    public void delete(Long id) {
        rentRepo.deleteById(id);
    }

    @Override
    public Rent saveByApartmentID(Long id,Date startDate, Date endDate) {
        Apartment apartment = apartmentService.findById(id).get();
        apartment.setStatus(2);
        User currentUser = userService.getCurrentUser();
        Rent rent = new Rent(currentUser,apartment,startDate,endDate);
        return rentRepo.save(rent);
    }

    @Override
    public Iterable<Rent> findAllByApartmentID(Long id) {
        return rentRepo.findAllByApartment_Id(id);
    }

}
