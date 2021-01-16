package com.codegym.airbnb.repository;

import com.codegym.airbnb.model.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRatingRepository extends CrudRepository<Rating, Long> {
    Iterable<Rating> findByApartment_Id(Long id);
}
