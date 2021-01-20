package com.codegym.airbnb.service.rating;

import com.codegym.airbnb.model.Rating;
import com.codegym.airbnb.service.IGeneralService;

import java.util.Optional;

public interface IRatingService extends IGeneralService<Rating> {
    Iterable<Rating> findByApartment_Id(Long id);

    Optional<Rating> findByApartment_IdAndUser_Id(Long apartmentId, Long userId);
}
