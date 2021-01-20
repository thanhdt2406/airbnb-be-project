package com.codegym.airbnb.repository;

import com.codegym.airbnb.model.District;
import com.codegym.airbnb.model.Ward;
import org.springframework.data.repository.CrudRepository;

public interface IWardRepository extends CrudRepository<Ward, Long> {
    Iterable<Ward> findAllByDistrict(District district);

    Iterable<Ward> findByDistrict_Id(Long id);

    Iterable<Ward> findByDistrict_ProvinceId(Long id);

}
