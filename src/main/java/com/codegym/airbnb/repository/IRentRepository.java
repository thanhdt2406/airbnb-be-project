package com.codegym.airbnb.repository;

import com.codegym.airbnb.model.Rent;
import com.codegym.airbnb.model.TotalIncome;
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

    @Transactional
    @Query(value = "select a.user_id, month(end_date) as Month, year(end_date) as Year, sum(datediff(end_date, start_date) * a.value) as TotalIncome from rent join apartment a on a.id = rent.apartment_id and end_date < now() group by a.user_id, month(end_date), year((end_date));", nativeQuery = true )
    Iterable<TotalIncome> getTotalIncomeByUserId(Long id);

}
