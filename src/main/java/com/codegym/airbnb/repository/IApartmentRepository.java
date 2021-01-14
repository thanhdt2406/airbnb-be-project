package com.codegym.airbnb.repository;

import com.codegym.airbnb.model.Apartment;
import com.codegym.airbnb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IApartmentRepository extends JpaRepository<Apartment, Long> {
    @Query(value = "select * from airbnb.apartment limit 7;", nativeQuery = true)
    Iterable<Apartment> findSevenApartment();

    Iterable<Apartment> findAllByUser(User user);
}
