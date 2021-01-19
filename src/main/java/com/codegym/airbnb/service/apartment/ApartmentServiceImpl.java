package com.codegym.airbnb.service.apartment;

import com.codegym.airbnb.model.Apartment;
import com.codegym.airbnb.model.Rent;
import com.codegym.airbnb.model.User;
import com.codegym.airbnb.repository.IApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ApartmentServiceImpl implements IApartmentService{
    @Autowired
    private IApartmentRepository apartmentRepository;

    @Override
    public Iterable<Apartment> findAll() {
        return apartmentRepository.findAll();
    }

    @Override
    public Apartment save(Apartment apartment) {
        return apartmentRepository.save(apartment);
    }

    @Override
    public Optional<Apartment> findById(Long id) {
        return apartmentRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        apartmentRepository.deleteById(id);
    }

    @Override
    public void stopSelling(Long id) {
        Apartment apartmentById = apartmentRepository.findById(id).get();
        apartmentById.setStatus(3);
        apartmentRepository.save(apartmentById);
    }

    @Override
    public void repairSelling(Long id) {
        Apartment apartmentById = apartmentRepository.findById(id).get();
        apartmentById.setStatus(2);
        apartmentRepository.save(apartmentById);
    }

    @Override
    public void rentAgainApartment(Long id) {
        Apartment apartmentById = apartmentRepository.findById(id).get();
        apartmentById.setStatus(0);
        apartmentRepository.save(apartmentById);
    }

    @Override
    public void rentingApartment(Long id) {
        Apartment apartmentById = apartmentRepository.findById(id).get();
        apartmentById.setStatus(1);
        apartmentRepository.save(apartmentById);
    }

    @Override
    public Iterable<Apartment> findSevenApartment() {
        return apartmentRepository.findSevenApartment();
    }

    @Override
    public Iterable<Apartment> findAllByUser(User user) {
        return apartmentRepository.findAllByUser(user);
    }

    @Override
    public Iterable<Apartment> findAllByApartment_Id(Long id) {
        return apartmentRepository.findAllRentApartmentByUserId(id);
    }
}
