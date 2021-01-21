package com.codegym.airbnb.repository;

import com.codegym.airbnb.model.Apartment;
import com.codegym.airbnb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IApartmentRepository extends JpaRepository<Apartment, Long> {
    @Query(value = "select * from apartment order by id desc limit 7", nativeQuery = true)
    Iterable<Apartment> findSevenApartment();

    Iterable<Apartment> findAllByUser(User user);

    @Query(value = "select apartment.* from apartment join rent r on apartment.id = r.apartment_id join user u on r.user_id = u.id and apartment.id = r.apartment_id and r.user_id = ?;", nativeQuery = true)
    Iterable<Apartment> findAllRentApartmentByUserId(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE apartment SET avatar =?1 WHERE id = ?2", nativeQuery = true)
    void setAvatarURL(String avatar, Long id);
}
