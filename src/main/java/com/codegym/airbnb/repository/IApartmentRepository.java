package com.codegym.airbnb.repository;

import com.codegym.airbnb.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IApartmentRepository extends JpaRepository<Apartment, Long> {
}
