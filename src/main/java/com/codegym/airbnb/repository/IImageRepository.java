package com.codegym.airbnb.repository;

import com.codegym.airbnb.model.Apartment;
import com.codegym.airbnb.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepository extends JpaRepository<Image, Long> {
    Iterable<Image> getAllByApartment(Apartment apartment);
}
