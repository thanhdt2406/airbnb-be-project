package com.codegym.airbnb.repository;

import com.codegym.airbnb.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface IRentRepository extends JpaRepository<Rent, Long> {
    Iterable<Rent> findAllByApartment_Id(Long id);
    @Transactional
    @Modifying
    @Query(value = "delete from rent where apartment_id = ?1 and user_id = ?2 and start_date>now();",nativeQuery = true)
    void cancelBooking(Long apartmentId, Long userId);

    @Query(value = "select * from rent where user_id = ?1 and end_date>now();",nativeQuery = true)
    Iterable<Rent> getAllBookingApartmentByUser(Long userId);

    @Transactional
    @Modifying
    @Query(value = "select * from rent where user_id = ?1 and end_date<now();", nativeQuery = true)
    Iterable<Rent> getAllRented(Long userId);

    @Query(value = "select * from rent where user_id = ?1 and apartment_id = ?2 and end_date>now();",nativeQuery = true)
    Optional<Rent> getBookingApartmentByUserIdAndApartment(Long userId, Long apartmentId);
}
