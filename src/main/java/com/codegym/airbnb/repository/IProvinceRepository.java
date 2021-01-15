package com.codegym.airbnb.repository;

import com.codegym.airbnb.model.Province;
import org.springframework.data.repository.CrudRepository;

public interface IProvinceRepository extends CrudRepository<Province, Long> {
}
