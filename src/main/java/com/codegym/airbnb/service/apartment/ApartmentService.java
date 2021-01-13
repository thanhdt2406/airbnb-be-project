package com.codegym.airbnb.service.apartment;

import com.codegym.airbnb.model.Apartment;
import com.codegym.airbnb.repository.IApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ApartmentService implements IApartmentService{
    @Autowired
    private IApartmentRepository iApartmentRepository;
    @Override
    public Iterable<Apartment> findAll() {
        return iApartmentRepository.findAll();
    }

    @Override
    public Apartment save(Apartment apartment) {
        return iApartmentRepository.save(apartment);
    }

    @Override
    public Optional<Apartment> findById(Long id) {
        return iApartmentRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        iApartmentRepository.deleteById(id);
    }

    public void stopSelling(Long id) {
        Apartment apartmentById = iApartmentRepository.findById(id).get();
        apartmentById.setStatus(4);
    }
}