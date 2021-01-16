package com.codegym.airbnb.service.rating;

import com.codegym.airbnb.model.Rating;
import com.codegym.airbnb.repository.IRatingRepository;
import com.codegym.airbnb.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingServiceImpl implements IRatingService {
    @Autowired
    private IRatingRepository ratingRepository;

    @Override
    public Iterable<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public Optional<Rating> findById(Long id) {
        return ratingRepository.findById(id);
    }

    @Override
    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public void delete(Long id) {
        ratingRepository.deleteById(id);
    }

    @Override
    public Iterable<Rating> findByApartment_Id(Long id) {
        return ratingRepository.findByApartment_Id(id);
    }
}
