package com.codegym.airbnb.repository;

import com.codegym.airbnb.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRentRepo extends JpaRepository<Rent, Long> {
    Iterable<Rent> findAllByApartment_Id(Long id);
}
