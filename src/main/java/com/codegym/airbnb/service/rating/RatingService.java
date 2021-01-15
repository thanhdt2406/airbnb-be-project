package com.codegym.airbnb.service.rating;

import com.codegym.airbnb.model.Rating;
import com.codegym.airbnb.repository.IRatingRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class RatingService implements IRatingService {
    @Autowired
    private IRatingRepo ratingRepo;

    @Override
    public Iterable<Rating> findAll() {
        return ratingRepo.findAll();
    }

    @Override
    public Optional<Rating> findById(Long id) {
        return ratingRepo.findById(id);
    }

    @Override
    public Rating save(Rating rating) {
        return ratingRepo.save(rating);
    }

    @Override
    public void delete(Long id) {
        ratingRepo.deleteById(id);
    }
}
