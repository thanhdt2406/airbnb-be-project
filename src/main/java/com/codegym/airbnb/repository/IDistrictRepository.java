package com.codegym.airbnb.repository;

import com.codegym.airbnb.model.District;
import com.codegym.airbnb.model.Province;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDistrictRepository extends CrudRepository<District, Long> {
    Iterable<District> findAllByProvince(Province province);
}
