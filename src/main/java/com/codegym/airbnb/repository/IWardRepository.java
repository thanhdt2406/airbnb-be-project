package com.codegym.airbnb.repository;

import com.codegym.airbnb.model.District;
import com.codegym.airbnb.model.Ward;
import org.springframework.data.repository.CrudRepository;

public interface IWardRepository extends CrudRepository<Ward, Long> {
    Iterable<Ward> findAllByDistrict(District district);
}
