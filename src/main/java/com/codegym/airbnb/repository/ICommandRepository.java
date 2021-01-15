package com.codegym.airbnb.repository;

import com.codegym.airbnb.model.Apartment;
import com.codegym.airbnb.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommandRepository extends JpaRepository<Comment,Long> {
    Iterable<Comment> findAllByApartment_Id(Long apartmentID);
}
