package com.codegym.airbnb.repository;

import com.codegym.airbnb.model.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRatingRepository extends CrudRepository<Rating, Long> {
    Iterable<Rating> findByApartment_Id(Long id);

    Optional<Rating> findByApartment_IdAndUser_Id(Long apartmentId, Long userId);
}
